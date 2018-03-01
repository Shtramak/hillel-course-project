package com.courses.tellus.autosalon.config;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.h2.jdbcx.JdbcDataSource;

public final class ConnectionFactory {

    private static ConnectionFactory connFactory;
    private static JdbcDataSource dataSource;

    private ConnectionFactory() {
    }

    /**
     * Method to produce singleton config factory.
     *
     * @return ConnectionFactory instance.
     */
    public static ConnectionFactory getInstance() throws IOException {
        synchronized (ConnectionFactory.class) {
            if (connFactory == null) {
                connFactory = new ConnectionFactory();
                final Properties dbProperties = new Properties();
                dbProperties.load(ClassLoader.getSystemResourceAsStream("config.properties"));
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
     * @throws SQLException exception.
     */
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
