package ch.heigvd.dai.controller;

import ch.heigvd.dai.database.PostgresDatabaseConnection;
import ch.heigvd.dai.model.entity.Player;
import ch.heigvd.dai.model.repository.HomeRepository;
import io.javalin.Javalin;
import io.javalin.http.Context;
import java.sql.*;

import java.util.LinkedList;
import java.util.Map;

public class HomeController {
    private final PostgresDatabaseConnection database;
    private final HomeRepository homeRepository;
    private final String PAGE = "home.jte", URL = "/home";

    public HomeController(Javalin app, PostgresDatabaseConnection databaseProvider) {
        this.database = databaseProvider;
        this.homeRepository = new HomeRepository(databaseProvider);
        app.get(URL, ctx -> getAllPlayers(ctx));
        app.post("/delete-player", this::deletePlayer);
        app.delete("/delete-player/{name}", this::deletePlayer);
    }

    // Afficher tous les joueurs
    public void getAllPlayers(Context ctx) {
        LinkedList<Player> players = homeRepository.getPlayersByLevel();
        try {
            ctx.render("home.jte", Map.of("players", players));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePlayer(Context ctx) {
        // Récupérer le nom du joueur à supprimer à partir des paramètres de la requête POST
        String playerName = ctx.formParam("playerName");
        if(playerName == null || playerName.isEmpty()) {
            playerName = ctx.pathParam("name");
        }

        if (playerName == null || playerName.isEmpty()) {// Vérifier que le nom du joueur est présent
            ctx.status(400).result("Player name is required.");
            return;
        }

        // Préparer la requête SQL pour supprimer le joueur
        String deletePlayerQuery = "DELETE FROM Joueur WHERE nom = ?";

        try (Connection connection = database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(deletePlayerQuery)) {
            // Passer le nom du joueur dans la requête
            stmt.setString(1, playerName);

            // Exécuter la suppression
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                // Suppression réussie
                ctx.status(200).result("Player deleted successfully.");
                ctx.redirect("/home");
            } else {
                // Aucun joueur correspondant trouvé
                ctx.status(404).result("Player not found.");
            }
        } catch (SQLException e) {
            // Gestion des erreurs
            e.printStackTrace();
            ctx.status(500).result("An error occurred while deleting the player.");
        }
    }
}