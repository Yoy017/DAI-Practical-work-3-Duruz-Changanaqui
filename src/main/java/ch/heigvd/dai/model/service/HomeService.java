package ch.heigvd.dai.model.service;

import ch.heigvd.dai.model.entity.Player;
import ch.heigvd.dai.model.repository.HomeRepository;

import java.util.LinkedList;

public class HomeService {
    private final HomeRepository playerRepository;

    public HomeService(HomeRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public LinkedList<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player getPlayerById(int id) {
        Player player = playerRepository.findById(id);
        if (player == null) {
            throw new IllegalArgumentException("Player with id " + id + " not found");
        }
        return player;
    }
}