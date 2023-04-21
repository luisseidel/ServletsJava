package com.seidelsoft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static DbConfig configs;

    public static Connection getConnection() throws SQLException {
        configs = new DbConfig();
        return DriverManager.getConnection(configs.getURL(), configs.getUser(), configs.getPassword());
    }
}
