package ch.heigvd.dai.database.repository;

import ch.heigvd.dai.database.DatabaseProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerRepository {
    private final DatabaseProvider databaseProvider;

    public PlayerRepository(DatabaseProvider databaseProvider) {
        this.databaseProvider = databaseProvider;
    }

    public void createPlayer(String name, String password) {
        String sql = "INSERT INTO ...";
        try (Connection conn = databaseProvider.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, password);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create player", e);
        }
    }

    public void deletePlayer(String name) {
        String sql = "DELETE FROM joueur WHERE nom = ?";
        try (Connection conn = databaseProvider.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete player", e);
        }
    }

    public void updatePlayer(String name, String password) {
        String sql = "UPDATE joueur SET ?... ?";
        try (Connection conn = databaseProvider.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, password);
            stmt.setString(2, name);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update player", e);
        }
    }

    public String getPlayers() {
        String sql = "SELECT * FROM joueur";
        StringBuilder result = new StringBuilder();
        try (Connection conn = databaseProvider.getConnection(); // Créer une nouvelle connexion avec fermerture implicite
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            int columnCount = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    result.append(rs.getString(i)).append("\t");
                }
                result.append("\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch players", e);
        }
        return result.toString();
    }
}