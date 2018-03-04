package com.courses.tellus.autosalon.config;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.h2.jdbcx.JdbcDataSource;

public final class ConnectionFactory {

    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class);
    private static ConnectionFactory connFactory;
    private static JdbcDataSource dataSource;
    private static Properties dbProperties;

    private ConnectionFactory() {
        try {
            dbProperties = new Properties();
            dbProperties.load(ClassLoader.getSystemResourceAsStream("config.properties"));
        } catch (IOException e) {
            LOGGER.debug(e.getMessage());
        }
    }

    /**
     * Method to produce singleton config factory.
     *
     * @return ConnectionFactory instance.
     */
    public static ConnectionFactory getInstance() {
        synchronized (ConnectionFactory.class) {
            if (connFactory == null) {
                connFactory = new ConnectionFactory();
                dataSource = new JdbcDataSource();
                dataSource.setURL(dbProperties.getProperty("jdbc.url"));
                dataSource.setUser(dbProperties.getProperty("jdbc.user"));
                dataSource.setPassword(dbProperties.getProperty("jdbc.pass"));
            }
        }
        return connFactory;
    }

    /**
     *Returns new config.
     *
     * @return Connection.
     */
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.debug(e.getMessage());
        }
        return null;
    }
}