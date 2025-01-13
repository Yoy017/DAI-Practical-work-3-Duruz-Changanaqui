package ch.heigvd.dai.controller;

import ch.heigvd.dai.model.repository.ItemsRepository;

import ch.heigvd.dai.database.PostgresDatabaseConnection;
import ch.heigvd.dai.model.entity.Item;
import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.LinkedList;
import java.util.Map;

public class ItemsController {
    private final ItemsRepository itemsRepository;
    private final String PAGE = "items.jte", URL = "/items";

    public ItemsController(Javalin app, PostgresDatabaseConnection databaseProvider) {
        this.itemsRepository = new ItemsRepository(databaseProvider);
        app.get(URL, this::showNewItems);
        app.post(URL, this::addItemToInventory);
    }

    private void showNewItems(Context ctx) {
        String name = ctx.cookie("player");
        if (name == null) {
            ctx.status(400).result("Player name is required");
            return;
        }

        LinkedList<Item> items = itemsRepository.getAllNewItems(name);
        ctx.render(PAGE, Map.of("items", items));
    }

    private void addItemToInventory(Context ctx) {
        String player = ctx.cookie("player");

        int itemId = Integer.parseInt(ctx.formParam("itemId"));
        if (!itemsRepository.addItemToSlot(player, itemId)) {
            ctx.status(400).result("Failed to add item to inventory");
            return;
        }

        showNewItems(ctx);
    }
}
