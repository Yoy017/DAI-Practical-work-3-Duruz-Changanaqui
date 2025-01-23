package ch.heigvd.dai.controller;

import ch.heigvd.dai.database.PostgresDatabaseConnection;
import ch.heigvd.dai.model.entity.Player;
import io.javalin.Javalin;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RegisterController {

    private final PostgresDatabaseConnection databaseProvider;

    public RegisterController(Javalin app, PostgresDatabaseConnection databaseProvider) {
        this.databaseProvider = databaseProvider;
        app.get("/register", this::getAvailableClasses);
        app.post("/register", this::registerNewCharacter);

        app.post("/register/{name}/{profession}", this::registerNewCharacter);
    }

    public void getAvailableClasses(Context ctx) {
        List<String> classes = getEnumValues("class");
        ctx.render("register.jte", Map.of("classes", classes));
    }

    public List<String> getEnumValues(String enumType) {
        List<String> values = new ArrayList<>();
        String query = "SELECT enumlabel FROM pg_enum WHERE enumtypid = (SELECT oid FROM pg_type WHERE typname = ?)";
        try (Connection connection = databaseProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, enumType);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    values.add(resultSet.getString("enumlabel"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return values;
    }

    public void registerNewCharacter(Context ctx) {
        String name = ctx.formParam("name");
        if (name == null) {
            name = ctx.pathParam("name");
        }

        String profession = ctx.formParam("profession");
        if (profession == null) {
            profession = ctx.pathParam("profession");
        }

        if (name == null || profession == null || name.isEmpty() || profession.isEmpty()) {
            ctx.render("register.jte", Map.of("classes", getEnumValues("class"),
                    "errorMessage", "Name and profession are required."));
            return;
        }

        if (isNameTaken(name)) {
            ctx.render("register.jte", Map.of("classes", getEnumValues("class"),
                    "errorMessage", "This name is already taken. Please choose another one."));
            return;
        }

        if (createCharacter(name, profession)) {
            //ctx.render("register.jte", Map.of("classes", getEnumValues("class"), "errorMessage", null));
            ctx.redirect("/home");
        } else {
            ctx.render("register.jte", Map.of("classes", getEnumValues("class"),
                    "errorMessage", "Failed to create character. Please try again later."));
        }
    }

    /**
     * Vérifie si un nom de joueur est déjà utilisé.
     */
    public boolean isNameTaken(String name) {
        String query = "SELECT COUNT(*) FROM joueur WHERE nom = ?";
        try (Connection connection = databaseProvider.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {return rs.getInt(1) > 0;}
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean createCharacter(String name, String profession) {
        String createPlayerQuery = "INSERT INTO Joueur (nom, experience, solde, classe, id_statistique) VALUES (?, ?, ?, CAST(? AS class), ?)";
        String createInventoryQuery = "INSERT INTO Inventaire (nom_joueur) VALUES (?) RETURNING id";
        String createStatQuery = "INSERT INTO Statistique (intelligence, agilite, force, endurance) VALUES (?, ?, ?, ?) RETURNING id";
        String updatePlayerQuery = "UPDATE Joueur SET id_inventaire = ? WHERE nom = ?";

        try (Connection connection = databaseProvider.getConnection()) {
            connection.setAutoCommit(false); // Démarrer une transaction

            // 2. Créer les statistiques
            int statId;
            try (PreparedStatement statStmt = connection.prepareStatement(createStatQuery)) {
                statStmt.setInt(1, 0); // intelligence
                statStmt.setInt(2, 0); // agilite
                statStmt.setInt(3, 0); // force
                statStmt.setInt(4, 0); // endurance
                try (ResultSet rs = statStmt.executeQuery()) {
                    if (rs.next()) {
                        statId = rs.getInt("id");
                    } else {
                        connection.rollback();
                        throw new SQLException("Failed to create statistics");
                    }
                }
            }

            // 1. Créer le joueur
            String playerId = name;
            try (PreparedStatement playerStmt = connection.prepareStatement(createPlayerQuery)) {
                playerStmt.setString(1, name);          // nom
                playerStmt.setDouble(2, 0);           // experience
                playerStmt.setInt(3, 0);                // solde
                playerStmt.setString(4, profession);    // classe
                playerStmt.setInt(5, statId);

                int rowsAffected = playerStmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Player created successfully.");
                    playerId = name;
                } else {
                    connection.rollback();
                    throw new SQLException("Failed to create player: No rows affected");
                }
            }
            // 3. Créer l'inventaire
            int inventoryId;
            try (PreparedStatement invStmt = connection.prepareStatement(createInventoryQuery)) {
                invStmt.setString(1, name); // nom_joueur (nom existe déjà dans la table Joueur)
                try (ResultSet rs = invStmt.executeQuery()) {
                    if (rs.next()) {
                        inventoryId = rs.getInt("id");
                    } else {
                        connection.rollback();
                        throw new SQLException("Failed to create inventory");
                    }
                }
            }

            // 4. Mettre à jour le joueur avec les IDs d'inventaire et de statistiques
            try (PreparedStatement updateStmt = connection.prepareStatement(updatePlayerQuery)) {
                updateStmt.setInt(1, inventoryId); // id_inventaire
                updateStmt.setString(2, playerId);    // id du joueur

                int rowsAffected = updateStmt.executeUpdate();
                if (rowsAffected == 0) {
                    connection.rollback();
                    throw new SQLException("Failed to update player with inventory and statistics");
                }
            }

            // Valider la transaction
            connection.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
