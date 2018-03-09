package com.courses.tellus.dbconnection;

import java.io.IOException;
<<<<<<< HEAD
=======
import java.io.InputStream;
>>>>>>> TEL-3-prometey
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.h2.jdbcx.JdbcDataSource;

<<<<<<< HEAD
public class ConnectionFactory {
    private static final String DB_PROPERTIES = "data-base.properties";
    private static ConnectionFactory connFactory;
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class);
    private static JdbcDataSource dataSource;

    /**
=======
@SuppressWarnings("PMD.ClassWithOnlyPrivateConstructorsShouldBeFinal")
public class ConnectionFactory {

    private static final String DB_PROPERTIES = "db.properties";
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class);
    private static ConnectionFactory connFactory;
    private static JdbcDataSource dataSource;

    /**
     * Simple empty constructor.
     */
    private ConnectionFactory() {
    }

    /**
>>>>>>> TEL-3-prometey
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
<<<<<<< HEAD
=======
            loadDatabaseProperties();
>>>>>>> TEL-3-prometey
        }
        return connFactory;
    }

    /**
     * Initializing new data source with properties from file.
<<<<<<< HEAD
     *
     * @param propFileName path to file with properties of database
     */
    private void loadDatabaseProperties(final String propFileName) {
        final Properties properties = new Properties();
        try {
            properties.load(ClassLoader.getSystemResourceAsStream(propFileName));
        } catch (IOException e) {
           LOGGER.error(e);
=======
     */
    private static void loadDatabaseProperties() {
        final Properties properties = new Properties();
        try {
            final InputStream inStream = ClassLoader.getSystemResourceAsStream(DB_PROPERTIES);
            properties.load(inStream);
        } catch (IOException except) {
            LOGGER.error(except);
>>>>>>> TEL-3-prometey
        }
        dataSource = new JdbcDataSource();
        dataSource.setUrl(properties.getProperty("h2.url"));
        dataSource.setUser(properties.getProperty("h2.user"));
        dataSource.setPassword(properties.getProperty("h2.password"));
    }

    /**
     * Return connection.
     *
     * @return java.sql.Connection
     * @throws SQLException exception
     */
    public Connection getConnection() throws SQLException {
<<<<<<< HEAD
        loadDatabaseProperties(DB_PROPERTIES);
        return dataSource.getConnection();
    }
}

=======
        return dataSource.getConnection();
    }
}
>>>>>>> TEL-3-prometey
