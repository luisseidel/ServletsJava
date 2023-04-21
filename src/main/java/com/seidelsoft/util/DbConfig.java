package com.seidelsoft.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DbConfig {

    private String driver;
    private String host;
    private String port;
    private String database;
    private String user;
    private String password;

    public DbConfig() {
        start();
    }

    protected String getURL() {
        return getDriver() + "://" + getHost() + ":" + getPort() + "/" + getDatabase();
    }

    protected void start() {
        try {
            InputStream input = DbConfig.class.getClassLoader().getResourceAsStream("db.properties");
            Properties prop = new Properties();

            prop.load(input);

            setDriver(prop.getProperty("db.driver"));
            setHost(prop.getProperty("db.host"));
            setPort(prop.getProperty("db.port"));
            setDatabase(prop.getProperty("db.database"));
            setUser(prop.getProperty("db.user"));
            setPassword(prop.getProperty("db.password"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
