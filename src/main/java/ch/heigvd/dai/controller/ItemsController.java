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
        app.get(URL, this::showItems);
        app.get(URL + "?name=:name", this::showItems);
    }

    public void showItems(Context ctx) {
        LinkedList<Item> items = itemsRepository.getAllItems();
        ctx.render(PAGE, Map.of("items", items));
    }
}
