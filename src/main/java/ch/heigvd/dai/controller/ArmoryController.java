package ch.heigvd.dai.controller;

import ch.heigvd.dai.model.entity.Inventory;
import ch.heigvd.dai.model.entity.Item;
import ch.heigvd.dai.model.service.ArmoryService;
import io.javalin.http.Context;

import java.util.LinkedList;
import java.util.Map;

public class ArmoryController {
    private final ArmoryService armoryService;
    private final String PAGE = "armory.jte";

    public ArmoryController(ArmoryService armoryService) {
        this.armoryService = armoryService;
    }

    public void getInventoryFromPlayer(Context ctx) {
        String name = ctx.queryParam("name");
        if (name == null || name.isEmpty()) {
            ctx.status(400).result("Player name is required");
            return;
        }

        Inventory inventory = armoryService.getInventoryFromPlayer(name);
        if (inventory == null) {
            ctx.status(404).result("Inventory not found for player: " + name);
            return;
        }

        ctx.render(PAGE, Map.of("inventory", inventory));
    }
}
