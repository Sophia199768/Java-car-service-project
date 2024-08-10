package org.example.service.repository;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


@Getter
public class DataBaseConfig {
    private final String url;
    private final String username;
    private final String password;


    public DataBaseConfig() throws IOException {
        Properties properties = new Properties();

        try (InputStream input = getClass().getClassLoader().getResourceAsStream("app.properties")) {
            if (input == null) {
                throw new IOException("Unable to find app.properties");
            }
            properties.load(input);
        }

        this.url = properties.getProperty("url");
        this.username = properties.getProperty("username");
        this.password = properties.getProperty("password");

    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        return DriverManager.getConnection(url, username, password);
    }
}