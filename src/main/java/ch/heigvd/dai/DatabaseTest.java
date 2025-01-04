package ch.heigvd.dai;

import ch.heigvd.dai.database.PostgresDatabaseConnection;
import ch.heigvd.dai.database.repository.PlayerRepository;
import ch.heigvd.dai.database.service.CharacterService;

public class DatabaseTest {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/";
        String user = "bdr";
        String pwd = "bdr";

        // Initialisation de la connexion à la base de données
        PostgresDatabaseConnection postgresDatabaseConnection = new PostgresDatabaseConnection(url, user, pwd);

        // Initialisation des services
        PlayerRepository playerRepository = new PlayerRepository(postgresDatabaseConnection);
        CharacterService playerService = new CharacterService(playerRepository);

        // Affichage des joueurs
        playerService.showPlayers();
    }
}
