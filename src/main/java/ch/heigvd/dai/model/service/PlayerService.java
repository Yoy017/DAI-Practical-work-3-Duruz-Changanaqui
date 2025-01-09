package ch.heigvd.dai.model.service;

import ch.heigvd.dai.model.entity.Player;
import ch.heigvd.dai.model.repository.PlayerRepository;

import java.util.LinkedList;
import java.util.List;

public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
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