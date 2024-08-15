package org.example.service.repository;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * A configuration class responsible for loading database connection properties and establishing connections.
 * The properties are loaded from the `app.properties` file located in the classpath.
 */
@Getter
public class DataBaseConfig {
    private final String url;
    private final String username;
    private final String password;

    /**
     * Constructs a {@code DataBaseConfig} object and loads the database connection properties
     * from the `app.properties` file.
     *
     * @throws IOException if the `app.properties` file cannot be found or read.
     */
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

    /**
     * Establishes and returns a connection to the database using the loaded properties.
     *
     * @return a {@code Connection} object representing the connection to the database.
     * @throws SQLException if a database access error occurs.
     * @throws ClassNotFoundException if the database driver class cannot be found.
     */
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        return DriverManager.getConnection(url, username, password);
    }
}
