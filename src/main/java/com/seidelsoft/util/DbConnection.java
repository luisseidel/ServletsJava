package com.seidelsoft.util;

import java.sql.*;

public class DbConnection {

    private static final String driver = "jdbc:postgresql";
    private static final String host = "localhost";
    private static final String port = "5432";
    private static final String database = "testeServlet";
    private static final String user = "teste";
    private static final String password = "teste";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(getURL(), getUser(), getPassword());
    }

    private static String getURL() {
        return getDriver() + "://" + getHost() + ":" + getPort() + "/" + getDatabase();
    }

    public static String getDriver() {
        return driver;
    }

    public static String getHost() {
        return host;
    }

    public static String getPort() {
        return port;
    }

    public static String getDatabase() {
        return database;
    }

    public static String getUser() {
        return user;
    }

    public static String getPassword() {
        return password;
    }
}
