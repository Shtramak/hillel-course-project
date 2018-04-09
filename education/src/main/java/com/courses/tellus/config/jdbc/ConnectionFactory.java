package com.courses.tellus.config.jdbc;

import java.io.IOException;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.h2.jdbcx.JdbcDataSource;

@SuppressWarnings("PMD.ClassWithOnlyPrivateConstructorsShouldBeFinal")
public class ConnectionFactory {

    private static final String DB_PROPERTIES = "properties/db.properties";
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class);
    private static ConnectionFactory connFactory;
    private static JdbcDataSource dataSource;

    /**
     * Simple empty constructor.
     */
    private ConnectionFactory() {
    }

    /**
     * Method to produce singleton config factory.
     *
     * @return config factory
     */
    public static ConnectionFactory getInstance() {
        if (connFactory != null) {
            return connFactory;
        }
        synchronized (ConnectionFactory.class) {
            connFactory = new ConnectionFactory();
            loadDatabaseProperties();
        }
        return connFactory;
    }

    /**
     * Initializing new data source with properties from file.
     */
    private static void loadDatabaseProperties() {
        final Properties properties = new Properties();
        try {
            final InputStream inStream = ClassLoader.getSystemResourceAsStream(DB_PROPERTIES);
            properties.load(inStream);
        } catch (IOException except) {
            LOGGER.error(except);
        }
        dataSource = new JdbcDataSource();
        dataSource.setUrl(properties.getProperty("h2.url"));
        dataSource.setUser(properties.getProperty("h2.user"));
        dataSource.setPassword(properties.getProperty("h2.password"));
    }

    /**
     * Return config.
     *
     * @return java.sql.Connection
     * @throws SQLException exception
     */
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}