package ch.heigvd.dai.model.repository;

import ch.heigvd.dai.model.entity.Player;
import ch.heigvd.dai.database.PostgresDatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class HomeRepository {
    private final PostgresDatabaseConnection databaseProvider;

    public HomeRepository(PostgresDatabaseConnection databaseProvider) {
        this.databaseProvider = databaseProvider;
    }

    public LinkedList<Player> findAll() {
        LinkedList<Player> players = new LinkedList<>();
        String sql = "SELECT * FROM joueur";

        try (Connection conn = databaseProvider.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                players.add(new Player(
                        rs.getString("nom"),
                        rs.getDouble("experience"),
                        rs.getInt("solde"),
                        rs.getString("classe")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch players", e);
        }
        return players;
    }

    public LinkedList<Player> getPlayersByLevel() {
        String sql = "SELECT * FROM vw_joueur_classement;";
        LinkedList<Player> players = new LinkedList<>();
        try (Connection conn = databaseProvider.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            int columnCount = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                players.add(new Player(
                        rs.getString("nom"),
                        rs.getDouble("experience"),
                        rs.getInt("solde"),
                        rs.getString("classe")
                ));
            }
            return players;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch players by level", e);
        }
    }
}