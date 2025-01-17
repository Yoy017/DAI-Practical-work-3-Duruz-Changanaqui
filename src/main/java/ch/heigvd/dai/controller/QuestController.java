package ch.heigvd.dai.controller;

import ch.heigvd.dai.database.PostgresDatabaseConnection;
import ch.heigvd.dai.model.entity.Player;
import ch.heigvd.dai.model.entity.Quest;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestController {

    private final PostgresDatabaseConnection databaseProvider;

    public QuestController(Javalin app, PostgresDatabaseConnection databaseProvider) {
        this.databaseProvider = databaseProvider;

        // Configure routes
        app.get("/quest", this::renderQuestPage);
        app.post("/quest/accept", this::acceptQuest);

        app.post("/quest/abandon", this::abandonQuest); // Utilisation de DELETE ici
        app.post("/quest/complete", this::completeQuest);
        app.post("/quest/delete", this::deleteCompletedQuest);
    }

    /**
     * Render the quest.jte template with followed and available quests for a player.
     */
    private void renderQuestPage(Context ctx) {
        String playerName = ctx.cookie("player");
        Player player = getPlayer(playerName);
        if (player == null) {
            ctx.status(400).result("Player name is required");
            return;
        }

        List<Quest> followedQuests = getQuestsForPlayer(playerName);
        List<Quest> availableQuests = getAvailableQuestsForPlayer(playerName);
        List<Quest> completedQuests = getCompletedQuests(playerName);
        ctx.render("quest.jte", Map.of(
                "player", player,
                "followedQuests", followedQuests,
                "availableQuests", availableQuests,
                "completedQuests", completedQuests
        ));
    }

    /**
     * Handle the acceptance of a quest by a player.
     */
    private void acceptQuest(Context ctx) {
        String playerName = ctx.cookie("player");
        if (playerName == null) {
            ctx.redirect("/home");
            return;
        }
        String questName = ctx.formParam("questName");
        if (questName == null || questName.isEmpty()) {
            ctx.status(400).result("Quest name is required.");
            return;
        }
        try (Connection connection = databaseProvider.getConnection()) {
            String sql = "INSERT INTO Accepte (complete, nom_quete, nom_joueur) " +
                    "VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setBoolean(1, false);
                statement.setString(2, questName);
                statement.setString(3, playerName);
                statement.execute();
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to accept quest: " + questName + " for player: " + playerName, e);
        }
        ctx.redirect("/quest");
    }

    /**
     * Get quests followed by a player.
     */
    private List<Quest> getQuestsForPlayer(String playerName) {
        String sql = """
            SELECT *
            FROM Quete q
            JOIN Accepte a ON q.nom = a.nom_quete AND a.complete = false
            WHERE a.nom_joueur = ?;
            """;

        try (Connection connection = databaseProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, playerName);
            ResultSet rs = statement.executeQuery();
            return mapResultSetToQuests(rs);

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch quests for player: " + playerName, e);
        }
    }

    public List<Quest> getAvailableQuestsForPlayer(String playerName) {
        Player player = getPlayer(playerName);
        double playerExperience = player.getExperience();

        String sql = "SELECT * FROM get_available_quests_for_player(?, ?)";  // Appel de la fonction stockée

        List<Quest> availableQuests = new ArrayList<>();

        try (Connection connection = databaseProvider.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Définir les paramètres de la fonction
            stmt.setString(1, playerName);               // Nom du joueur
            stmt.setBigDecimal(2, BigDecimal.valueOf(playerExperience));  // Expérience du joueur

            // Exécuter la fonction stockée
            try (ResultSet rs = stmt.executeQuery()) {  // Obtenir un ResultSet de la fonction
                while (rs.next()) {
                    availableQuests.add(getQuestWithDependency(rs));
                }
            } catch (Exception e) {
                throw new RuntimeException("Failed to fetch quests for player: " + playerName, e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while retrieving available quests for player: " + playerName, e);
        }

        return availableQuests;
    }

    /**
     * Fetch player details.
     */
    private Player getPlayer(String playerName) {
        String sql = "SELECT * FROM Joueur WHERE nom = ?";
        try (Connection connection = databaseProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, playerName);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Player player = new Player(
                        rs.getString("nom"),
                        rs.getDouble("experience"),
                        rs.getInt("solde"),
                        rs.getString("classe"));
                return player;
            } else {
                throw new RuntimeException("Player not found: " + playerName);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch player details: " + playerName, e);
        }
    }

    /**
     * Map ResultSet to List of Quest objects.
     */
    private List<Quest> mapResultSetToQuests(ResultSet rs) throws Exception {
        List<Quest> quests = new ArrayList<>();
        while (rs.next()) {
            quests.add(getQuestWithDependency(rs));
        }
        return quests;
    }

    private Quest getQuestWithDependency(ResultSet rs) throws Exception {
        Quest quest = new Quest(rs);
        String dependency = rs.getString("nom_quete_requise");
        if (dependency != null) {
            Quest requiredQuest = getQuestByName(dependency);
            quest.setRequiredQuest(requiredQuest);
        }
        return quest;
    }

    private Quest getQuestByName(String questName) {
        String sql = "SELECT * FROM Quete WHERE nom = ?";
        try (Connection connection = databaseProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, questName);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return getQuestWithDependency(rs);
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch quest by name: " + questName, e);
        }
    }

    // Route pour abandonner une quête
    public void abandonQuest(Context ctx) {
        String playerName = ctx.cookie("player");
        if (playerName == null) {
            ctx.redirect("/home");
            return;
        }

        String questName = ctx.formParam("questName");
        if (questName == null || questName.isEmpty()) {
            ctx.status(400).result("Quest name is required.");
            return;
        }

        try (Connection connection = databaseProvider.getConnection()) {
            String sql = "DELETE FROM Accepte WHERE nom_quete = ? AND nom_joueur = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, questName);
                statement.setString(2, playerName);
                statement.executeUpdate();
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to abandon quest: " + questName, e);
        }

        ctx.redirect("/quest"); // Rediriger vers la page des quêtes
    }

    // Terminer une quête
    private void completeQuest(Context ctx) {
        String playerName = ctx.cookie("player");
        if (playerName == null) {
            ctx.redirect("/home");
            return;
        }

        String questName = ctx.formParam("questName");
        if (questName == null || questName.isEmpty()) {
            ctx.status(400).result("Quest name is required.");
            return;
        }

        try (Connection connection = databaseProvider.getConnection()) {
            // Mettre à jour le statut de la quête dans la table Accepte
            String sql = "UPDATE Accepte SET complete = true WHERE nom_quete = ? AND nom_joueur = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, questName);
                statement.setString(2, playerName);
                statement.executeUpdate();
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to complete quest: " + questName, e);
        }

        ctx.redirect("/quest"); // Rediriger vers la page des quêtes
    }

    private List<Quest> getCompletedQuests(String playerName) {
        List<Quest> completedQuests = new ArrayList<>();
        try (Connection connection = databaseProvider.getConnection()) {
            String sql = "SELECT q.* FROM Quete q " +
                    "JOIN Accepte a ON a.nom_quete = q.nom " +
                    "WHERE a.nom_joueur = ? AND a.complete = true";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, playerName);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    completedQuests.add(getQuestWithDependency(resultSet));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch completed quests for player: " + playerName, e);
        }
        return completedQuests;
    }

    // Méthode pour supprimer une quête complétée
    private void deleteCompletedQuest(Context ctx) {
        String playerName = ctx.cookie("player");
        if (playerName == null) {
            ctx.redirect("/home");
            return;
        }

        String questName = ctx.formParam("questName");
        if (questName == null || questName.isEmpty()) {
            ctx.status(400).result("Quest name is required.");
            return;
        }

        try (Connection connection = databaseProvider.getConnection()) {
            String sql = "DELETE FROM Accepte WHERE nom_quete = ? AND nom_joueur = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, questName);
                statement.setString(2, playerName);
                statement.execute();
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete quest: " + questName + " for player: " + playerName, e);
        }

        ctx.redirect("/quest");
    }
}
