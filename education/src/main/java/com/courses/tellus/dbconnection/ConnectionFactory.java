package com.courses.tellus.dbconnection;

import org.h2.jdbcx.JdbcDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public final class ConnectionFactory {

    public static final long serialVersionUID = 1L;

    private static ConnectionFactory connectionFactory = null;
    private static JdbcDataSource dataSource;

    /**
     * Method to produce singleton connection factory.
     *
     * @return connection factory
     */
    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
            Properties props = new Properties();
            try {
                props.load(ClassLoader.getSystemResourceAsStream("db.properties"));
            } catch (IOException except) {
                except.printStackTrace();
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
