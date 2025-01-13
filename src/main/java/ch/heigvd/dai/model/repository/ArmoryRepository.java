package ch.heigvd.dai.model.repository;

import ch.heigvd.dai.database.PostgresDatabaseConnection;
import ch.heigvd.dai.model.entity.Inventory;
import ch.heigvd.dai.model.entity.Item;
import ch.heigvd.dai.model.entity.Slot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class ArmoryRepository {
    private final PostgresDatabaseConnection databaseProvider;

    public ArmoryRepository(PostgresDatabaseConnection databaseProvider) {
        this.databaseProvider = databaseProvider;
    }

    public Inventory getInventoryFromPlayer(String name) {
        Inventory inventory = new Inventory();
        String sql = "SELECT * FROM vw_joueurs_inventaire WHERE joueur_nom = ?;";
        try (Connection conn = databaseProvider.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, name);
            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    inventory.addSlot(
                            new Slot(rs.getString("nom_type"),
                            new Item(
                                    rs.getInt("inventaire_id"),
                                    rs.getString("objet_nom"),
                                    rs.getString("description"),
                                    rs.getString("nom_type"),
                                    rs.getString("nom_rarete"),
                                    rs.getDouble("niveaurequis")))
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch players", e);
        }
        return inventory;
    }

    public boolean deleteInventoryFromPlayer(int itemId, String player) {
        String sql = "DELETE FROM slot\n" +
                "    WHERE id_objet = ?\n" +
                "    AND id_inventaire = (\n" +
                "        SELECT inventaire_id FROM joueur WHERE nom = ?\n" +
                "        );";
        try (Connection conn = databaseProvider.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, itemId);
            stmt.setString(2, player);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete inventory", e);
        }
    }

    public int getIdFromObjectName(String name) {
        String sql = "SELECT id FROM objet WHERE nom = ?;";
        try (Connection conn = databaseProvider.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch item id", e);
        }
        return -1;
    }

    public boolean equipItem(int itemId, String player) {
        String sql = "UPDATE slot\n" +
                "    SET id_inventaire = (\n" +
                "        SELECT id_inventaire FROM joueur WHERE nom = ?\n" +
                "        )\n" +
                "    WHERE id_objet = ?;";
        try (Connection conn = databaseProvider.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, player);
            stmt.setInt(2, itemId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to equip item", e);
        }
    }
}
