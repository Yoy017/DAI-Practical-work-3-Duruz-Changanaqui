package ch.heigvd.dai.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgresDatabaseConnection implements DatabaseProvider {
    private final String url, user, pwd;

    public PostgresDatabaseConnection(String url, String user, String pwd) {
        this.url = url;
        this.user = user;
        this.pwd = pwd;
    }

    public Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, pwd);
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("SET search_path TO mmo_game");
        }
        return conn;
    }
}