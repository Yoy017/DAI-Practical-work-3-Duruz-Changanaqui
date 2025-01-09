package ch.heigvd.dai.controller;

import ch.heigvd.dai.model.entity.Player;
import ch.heigvd.dai.model.service.PlayerService;
import io.javalin.http.Context;

import java.util.List;
import java.util.Map;

public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    // Afficher tous les joueurs
    public void getAllPlayers(Context ctx) {
        try {
            List<Player> players = playerService.getAllPlayers();
            ctx.render("home.jte", Map.of("players", players));
        } catch (Exception e) {
            ctx.status(500).result("Internal Server Error: " + e.getMessage());
        }
    }

    // Afficher un joueur par ID
    public void getPlayerById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Player player = playerService.getPlayerById(id);
        if (player != null) {
            ctx.render("player_details.jte", Map.of("player", player)); // Affichage dans un fichier de détails par exemple
        } else {
            ctx.status(404).result("Player not found");
        }
    }
}