package com.courses.tellus.dbconnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.h2.jdbcx.JdbcDataSource;

public final class ConnectionFactory {

    private static final String DB_PROPERTIES = "db.properties";
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class);
    private static ConnectionFactory connFactory;
    private static JdbcDataSource dataSource;

    /**
     * Method to produce singleton connection factory.
     *
     * @return connection factory
     */
    public static ConnectionFactory getInstance() {
        if (connFactory != null) {
            return connFactory;
        }
            synchronized (ConnectionFactory.class) {
                connFactory = new ConnectionFactory();
                final Properties properties = new Properties();
                try {
                    properties.load(ClassLoader.getSystemResourceAsStream(DB_PROPERTIES));
                } catch (IOException except) {
                    LOGGER.error(except.getMessage());
                }
                dataSource = new JdbcDataSource();
                dataSource.setUrl(properties.getProperty("h2.url"));
                dataSource.setUser(properties.getProperty("h2.user"));
                dataSource.setPassword(properties.getProperty("h2.password"));
            }
        return connFactory;
    }

    /**
     * Return connection.
     *
     * @return java.sql.Connection
     * @throws SQLException exception
     */
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
