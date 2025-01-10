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

    public Player findById(int id) {
        String sql = "SELECT * FROM joueur WHERE id = ?";
        try (Connection conn = databaseProvider.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Player(
                            rs.getString("name"),
                            rs.getDouble("experience"),
                            rs.getInt("balance"),
                            rs.getString("champion")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch player with id " + id, e);
        }
        return null;
    }

        public LinkedList<Player> getPlayersByLevel() {
        String sql = "SELECT nom, experience\n" +
                "FROM joueur\n" +
                "    INNER JOIN statistique\n" +
                "    ON joueur.id_statistique = statistique.id\n" +
                "GROUP BY joueur.nom, experience\n" +
                "ORDER BY experience DESC;";
        LinkedList<Player> players = null;
        try (Connection conn = databaseProvider.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            int columnCount = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                players.add(new Player(
                        rs.getString("nom"),
                        rs.getDouble("experience"),
                        0,
                        ""
                ));
            }
            return players;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch players by level", e);
        }
    }
}