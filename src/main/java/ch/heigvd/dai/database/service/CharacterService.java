package ch.heigvd.dai.database.service;

import ch.heigvd.dai.database.repository.PlayerRepository;

public class CharacterService {
    private final PlayerRepository playerRepository;

    public CharacterService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public void showPlayers() {
        System.out.println("Fetching players...");
        String players = playerRepository.getPlayers();
        System.out.println(players);
    }
}