package ch.heigvd.dai.database.service;

import ch.heigvd.dai.database.repository.PlayerRepository;

public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public void showPlayers() {
        System.out.println("Fetching players...");
        String players = playerRepository.getPlayers();
        System.out.println(players);
    }
}