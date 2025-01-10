package ch.heigvd.dai.controller;

import ch.heigvd.dai.model.entity.Player;
import ch.heigvd.dai.model.service.HomeService;
import io.javalin.http.Context;

import java.util.LinkedList;
import java.util.Map;

public class HomeController {
    private final HomeService playerService;

    public HomeController(HomeService playerService) {
        this.playerService = playerService;
    }

    // Afficher tous les joueurs
    public void getAllPlayers(Context ctx) {
        LinkedList<Player> players = playerService.getAllPlayers();
        ctx.render("home.jte", Map.of("players", players));
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