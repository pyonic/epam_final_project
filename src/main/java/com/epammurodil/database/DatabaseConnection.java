package com.epammurodil.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private Connection connection = null;

    public Connection getConn() {
        return connection;
    }

    public DatabaseConnection(String url, String username, String password) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", username);
        properties.setProperty("password", password);
        this.connection = DriverManager.getConnection(url, properties);
    }
}
