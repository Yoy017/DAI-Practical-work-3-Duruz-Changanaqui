package ch.heigvd.dai.controller;

import ch.heigvd.dai.database.PostgresDatabaseConnection;
import ch.heigvd.dai.model.entity.Player;
import ch.heigvd.dai.model.repository.HomeRepository;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.LinkedList;
import java.util.Map;

public class HomeController {
    private final HomeRepository homeRepository;
    private final String PAGE = "home.jte", URL = "/home";

    public HomeController(Javalin app, PostgresDatabaseConnection databaseProvider) {
        this.homeRepository = new HomeRepository(databaseProvider);
        app.get(URL, this::getAllPlayers);
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
}