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

    public void getAllPlayers(Context ctx) {
        List<Player> players = playerService.getAllPlayers();
        ctx.render("home.jte?", Map.of("players", players));
    }

    public void getPlayerById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Player player = playerService.getPlayerById(id);
        ctx.render("player.jte", Map.of("player", player));
    }
}