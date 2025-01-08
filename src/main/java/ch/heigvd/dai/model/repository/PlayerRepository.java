package ch.heigvd.dai.model.repository;

import ch.heigvd.dai.model.entity.Player;
import ch.heigvd.dai.database.PostgresDatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PlayerRepository {
    private final PostgresDatabaseConnection databaseProvider;

    public PlayerRepository(PostgresDatabaseConnection databaseProvider) {
        this.databaseProvider = databaseProvider;
    }

    public List<Player> findAll() {
        List<Player> players = new ArrayList<>();
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

//package ch.heigvd.dai.model.repository;
//
//import ch.heigvd.dai.database.DatabaseProvider;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.LinkedList;
//
//public class PlayerRepository {
//    private DatabaseProvider databaseProvider;
//
//    public PlayerRepository(DatabaseProvider databaseProvider) {
//        this.databaseProvider = databaseProvider;
//    }
//
//    public void createPlayer(String name, String password) {
//        String sql = "INSERT INTO ...";
//        try (Connection conn = databaseProvider.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, name);
//            stmt.setString(2, password);
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException("Failed to create player", e);
//        }
//    }
//
//    public void deletePlayer(String name) {
//        String sql = "DELETE FROM joueur WHERE nom = ?";
//        try (Connection conn = databaseProvider.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, name);
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException("Failed to delete player", e);
//        }
//    }
//
//    public void updatePlayer(String name, String password) {
//        String sql = "UPDATE joueur SET ?... ?";
//        try (Connection conn = databaseProvider.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, password);
//            stmt.setString(2, name);
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException("Failed to update player", e);
//        }
//    }
//
//    public String getPlayers() {
//        String sql = "SELECT * FROM joueur";
//        StringBuilder result = new StringBuilder();
//        try (Connection conn = databaseProvider.getConnection(); // Créer une nouvelle connexion avec fermerture implicite
//             PreparedStatement stmt = conn.prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            int columnCount = rs.getMetaData().getColumnCount();
//            while (rs.next()) {
//                for (int i = 1; i <= columnCount; i++) {
//                    result.append(rs.getString(i)).append("\t");
//                }
//                result.append("\n");
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException("Failed to fetch players", e);
//        }
//        return result.toString();
//    }
//
//    public LinkedList<String> getPlayersByLevel() {
//        String sql = "SELECT nom, experience\n" +
//                "FROM joueur\n" +
//                "    INNER JOIN statistique\n" +
//                "    ON joueur.id_statistique = statistique.id\n" +
//                "GROUP BY joueur.nom, experience\n" +
//                "ORDER BY experience DESC;";
//        LinkedList<String> result = null;
//        try (Connection conn = databaseProvider.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            int columnCount = rs.getMetaData().getColumnCount();
//            while (rs.next()) {
//                StringBuilder row = new StringBuilder();
//                for (int i = 1; i <= columnCount; i++) {
//                    row.append(rs.getString(i)).append("\t");
//                }
//                result.add(row.toString());
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException("Failed to fetch players by level", e);
//        }
//        return result;
//    }
//}