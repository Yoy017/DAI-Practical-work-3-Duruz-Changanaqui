package ch.heigvd.dai.controller;

import ch.heigvd.dai.database.PostgresDatabaseConnection;
import ch.heigvd.dai.model.entity.Inventory;
import ch.heigvd.dai.model.entity.Item;
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

            // Set new cookie
            if (newName != null && newName != cookie) {
                System.out.println("Setting new cookie for player: " + newName);
                ctx.cookie("player", newName, 3600);
            }

            getInventoryFromPlayer(ctx);
        });

//        app.put(URL, ctx -> {
//            String itemName = ctx.formParam("itemName");
//            updateItemInInventory(ctx, itemName);
//            getInventoryFromPlayer(ctx);
//        });

        app.post("/armory", ctx -> {
            String method = ctx.formParam("_method");

            if ("delete".equalsIgnoreCase(method)) {
                String itemName = ctx.formParam("itemName");
                System.out.println("Here in delete method with itemName " + itemName);
                int itemId = armoryRepository.getIdFromObjectName(itemName);
                deleteItemFromInventory(ctx, itemId);
            }
            getInventoryFromPlayer(ctx);
        });

//        app.delete(URL, ctx -> {
//            String itemName = ctx.formParam("itemName");
//            System.out.println("Here in delete method with itemName " + itemName);
//            int itemId = armoryRepository.getIdFromObjectName(itemName);
//            deleteItemFromInventory(ctx, itemId);
//            getInventoryFromPlayer(ctx);
//        });
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

    // NOT SUPPOSED TO BE IMPLEMENTED HERE, CAUSE ITEMS IN GAMES ARE TO BE UPDATE BY ADMIN
//    public void updateItemInInventory(Context ctx, String itemName) {
//        String player = ctx.cookie("player");
//        if (player == null) {
//            ctx.status(400).result("Player name is required");
//            return;
//        }
//
//        if (itemName == null) {
//            ctx.status(400).result("Item name is required");
//            return;
//        }
//
//        if (!armoryRepository.updateInventoryFromPlayer(itemName, player)) {
//            ctx.status(400).result("Failed to update inventory for player: " + player);
//            return;
//        }
//
//        getInventoryFromPlayer(ctx);
//    }

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

        getInventoryFromPlayer(ctx);
    }
}