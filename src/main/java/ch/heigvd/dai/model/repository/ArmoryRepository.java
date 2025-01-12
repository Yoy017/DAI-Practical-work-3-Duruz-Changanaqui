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
        String sql = "SELECT o.* FROM slot AS s\n" +
                "    INNER JOIN inventaire\n" +
                "    ON s.id_inventaire = inventaire.id\n" +
                "    INNER JOIN objet AS o\n" +
                "    ON s.id_objet = o.id\n" +
                "    INNER JOIN statistique\n" +
                "    ON o.id_statistique = statistique.id\n" +
                "WHERE nom_joueur = ?;";
        try (Connection conn = databaseProvider.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, name);
            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    inventory.addSlot(
                            new Slot(rs.getString("nom_type"),
                            new Item(
                                    rs.getInt("id"),
                                    rs.getString("nom"),
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
                "        SELECT id_inventaire FROM joueur WHERE nom = ?\n" +
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

    // TODO: ADD STATISTIC TO ITEM and PLAYER ------------------------------------------------------------
    public LinkedList<Item> getAllNewItems(String name) {
        LinkedList<Item> items = new LinkedList<>();
        String sql = "SELECT * FROM objet\n" +
                "WHERE objet.id NOT IN (\n" +
                "        SELECT o.id FROM slot AS s\n" +
                "                INNER JOIN inventaire\n" +
                "                ON s.id_inventaire = inventaire.id\n" +
                "                INNER JOIN objet AS o\n" +
                "                ON s.id_objet = o.id\n" +
                "                INNER JOIN statistique\n" +
                "                ON o.id_statistique = statistique.id\n" +
                "                WHERE nom_joueur = ?\n" +
                "    );";
        try (Connection conn = databaseProvider.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, name);
            try (var rs = stmt.executeQuery()) {
                while (rs.next()) {
                    items.add(new Item(
                            rs.getInt("id"),
                            rs.getString("nom"),
                            rs.getString("description"),
                            rs.getString("nom_type"),
                            rs.getString("nom_rarete"),
                            rs.getDouble("niveaurequis")
                    ));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch items", e);
        }
        return items;
    }

    public boolean addItemToSlot(String playerName, long itemId) {
        String sql = "INSERT INTO slot (id_inventaire, id_objet)\n" +
                "SELECT j.id_inventaire, o.id\n" +
                "FROM joueur j\n" +
                "JOIN objet o ON o.id = ?\n" +
                "WHERE j.nom = ?;";
        try (Connection conn = databaseProvider.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setLong(1, itemId);
            stmt.setString(2, playerName);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to add item to inventory", e);
        }
    }
}
