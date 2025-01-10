package ch.heigvd.dai.model.service;

import ch.heigvd.dai.model.entity.Inventory;
import ch.heigvd.dai.model.entity.Item;
import ch.heigvd.dai.model.repository.ArmoryRepository;

import java.util.LinkedList;

public class ArmoryService {
    private final ArmoryRepository armoryRepository;

    public ArmoryService(ArmoryRepository armoryRepository) {
        this.armoryRepository = armoryRepository;
    }

    public Inventory getInventoryFromPlayer(String name) {
        try {
            return armoryRepository.getInventoryFromPlayer(name);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch inventory", e);
        }
    }
}
