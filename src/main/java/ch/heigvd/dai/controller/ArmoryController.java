package ch.heigvd.dai.controller;

import ch.heigvd.dai.database.PostgresDatabaseConnection;
import ch.heigvd.dai.model.entity.Inventory;
import ch.heigvd.dai.model.repository.ArmoryRepository;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class ArmoryController {
    private final ArmoryRepository armoryRepository;
    private final String PAGE = "armory.jte", URL = "/armory";

    public ArmoryController(Javalin app, PostgresDatabaseConnection databaseProvider) {
        this.armoryRepository = new ArmoryRepository(databaseProvider);

        app.get(URL, ctx -> {
            String playerName = ctx.queryParam("player");
            if (playerName != null) {
                ctx.cookie("player", playerName, 24 * 60 * 60);
            } else {
                playerName = ctx.cookie("player");
            }

            if (playerName == null || playerName.isEmpty()) {
                ctx.status(400).result("Player name is required");
                return;
            }

            getInventoryFromPlayer(ctx, playerName);
        });

        app.post("/armory", ctx -> {
            String method = ctx.formParam("_method");

            if ("delete".equalsIgnoreCase(method)) {
                String itemName = ctx.formParam("itemName");
                int itemId = armoryRepository.getIdFromObjectName(itemName);
                deleteItemFromInventory(ctx, itemId);
            }

            if("equip".equalsIgnoreCase(method)){
                String itemName = ctx.formParam("itemName");
                int itemId = armoryRepository.getIdFromObjectName(itemName);
                equipItem(ctx, itemId);
            }

            getInventoryFromPlayer(ctx, ctx.cookie("player"));
        });
    }

    private void equipItem(@NotNull Context ctx, int itemId) {
        String player = ctx.cookie("player");
        if (player == null) {
            ctx.status(400).result("Player name is required");
            return;
        }

        if (itemId == -1) {
            ctx.status(400).result("Item name is required");
            return;
        }

        if (!armoryRepository.equipItem(itemId, player)) {
            ctx.status(400).result("Failed to equip item for player: " + player);
            return;
        }
    }

    public void getInventoryFromPlayer(Context ctx, String playerName) {
        Inventory inventory = armoryRepository.getInventoryFromPlayer(playerName);
        if (inventory == null) {
            ctx.status(404).result("Inventory not found for player: " + playerName);
            return;
        }

        ctx.render(PAGE, Map.of("inventory", inventory, "playerName", playerName));
    }

    public void deleteItemFromInventory(Context ctx, int itemId) {
        String player = ctx.cookie("player");
        if (player == null) {
            ctx.status(400).result("Player name is required");
            return;
        }

        if (itemId == -1) {
            ctx.status(400).result("Item name is required");
            return;
        }

        if (!armoryRepository.deleteInventoryFromPlayer(itemId, player)) {
            ctx.status(400).result("Failed to delete inventory for player: " + player);
            return;
        }

        getInventoryFromPlayer(ctx, player);
    }
}