package ch.heigvd.dai.controller;

import ch.heigvd.dai.database.PostgresDatabaseConnection;
import ch.heigvd.dai.model.entity.Inventory;
import ch.heigvd.dai.model.repository.ArmoryRepository;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.LinkedList;
import java.util.Map;

public class ArmoryController {
    private final ArmoryRepository armoryRepository;
    private final String PAGE = "armory.jte", URL = "/armory";

    public ArmoryController(Javalin app, PostgresDatabaseConnection databaseProvider) {
        this.armoryRepository = new ArmoryRepository(databaseProvider);
        app.get(URL, ctx -> {
            String cookie = ctx.cookie("player");
            String newName = ctx.queryParam("name");

            if (newName != null && newName != cookie) {
                System.out.println("Setting new cookie for player: " + newName);
                ctx.cookie("player", newName, 3600);
            }

            // If no new name provided, fall back to existing cookie
            getInventoryFromPlayer(ctx);
        });
    }

    public void getInventoryFromPlayer(Context ctx) {
        String player = ctx.cookie("player");
        if (player == null) {
            ctx.status(400).result("Player name is required");
            return;
        }

        Inventory inventory =  armoryRepository.getInventoryFromPlayer(player);
        if (inventory == null) {
            ctx.status(404).result("Inventory not found for player: " + player);
            return;
        }

        ctx.render(PAGE, Map.of("inventory", inventory));
    }
}