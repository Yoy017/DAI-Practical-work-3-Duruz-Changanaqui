package ch.heigvd.dai.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseProvider {
    Connection getConnection() throws SQLException;
}