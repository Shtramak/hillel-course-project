package com.courses.tellus.dbconnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.h2.jdbcx.JdbcDataSource;

public final class ConnectionFactory {

    static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class);

    private static ConnectionFactory connectionFactory;
    private static JdbcDataSource dataSource;

    /**
     * Method to produce singleton connection factory.
     *
     * @return connection factory
     */
    public static ConnectionFactory getInstance() {
        if (connectionFactory != null) {
            return connectionFactory;
        }
            synchronized (ConnectionFactory.class) {
                connectionFactory = new ConnectionFactory();
                final Properties props = new Properties();
                try {
                    props.load(ClassLoader.getSystemResourceAsStream("db.properties"));
                } catch (IOException except) {
                    LOGGER.error(except.getMessage());
                }
                dataSource = new JdbcDataSource();
                dataSource.setUrl(props.getProperty("h2.url"));
                dataSource.setUser(props.getProperty("h2.user"));
                dataSource.setPassword("h2.password");
            }
        return connectionFactory;
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
