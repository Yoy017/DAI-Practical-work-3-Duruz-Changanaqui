package ch.heigvd.dai.model.repository;

import ch.heigvd.dai.database.PostgresDatabaseConnection;
import ch.heigvd.dai.model.entity.Inventory;
import ch.heigvd.dai.model.entity.Item;
import ch.heigvd.dai.model.entity.Slot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
