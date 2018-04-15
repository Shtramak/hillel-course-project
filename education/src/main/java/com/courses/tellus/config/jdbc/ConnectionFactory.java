package com.courses.tellus.config.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.RunScript;

@SuppressWarnings("PMD.ClassWithOnlyPrivateConstructorsShouldBeFinal")
public class ConnectionFactory {

    private static final Path DB_INITIALS = Paths.get("database/h2/init_h2.sql");
    private static final Path DB_PROPERTIES = Paths.get("properties/db.properties");
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
            firstInitDatabase();
        }
        return connFactory;
    }

    /**
     * Initializing new data source with properties from file.
     */
    private static void loadDatabaseProperties() {
        final Properties properties = new Properties();
        try {
        final InputStream inStream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(DB_PROPERTIES.toString());
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
     * First initialise database schema and tables if it not exists.
     */
    private static void firstInitDatabase() {
        try {
        final InputStream resourceAsStream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(DB_INITIALS.toString());
            RunScript.execute(ConnectionFactory.getInstance().getConnection(), new InputStreamReader(resourceAsStream));
        } catch (SQLException except) {
            LOGGER.error(except);
        }
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