package ch.heigvd.dai.model.repository;

import ch.heigvd.dai.database.PostgresDatabaseConnection;
import ch.heigvd.dai.model.entity.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.LinkedList;

public class ItemsRepository {
    private final PostgresDatabaseConnection databaseProvider;

    public ItemsRepository(PostgresDatabaseConnection databaseProvider) {
        this.databaseProvider = databaseProvider;
    }

    // TODO: ADD STATISTIC TO ITEM and PLAYER ------------------------------------------------------------
    public LinkedList<Item> getAllItems() {
        LinkedList<Item> items = new LinkedList<>();
        String sql = "SELECT * FROM objet";
        try (Connection conn = databaseProvider.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);) {
                try (var rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        items.add(new Item(
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
}
