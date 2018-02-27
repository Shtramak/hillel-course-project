package com.courses.roman.config;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.h2.jdbcx.JdbcDataSource;

public final class ConnectionFactory {

	private static ConnectionFactory connectionFactory = null;
	private static JdbcDataSource dataSource;

	private ConnectionFactory() {
	}

	/**
	 * Method to produce singleton connection factory.
	 *
	 * @return ConnectionFactory instance
	 */
	public static ConnectionFactory getInstance() {
		if (connectionFactory == null) {
			connectionFactory = new ConnectionFactory();
			Properties dataBaseProperties = new Properties();
			try {
				dataBaseProperties.load(ClassLoader.getSystemResourceAsStream("data-base.properties"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			dataSource = new JdbcDataSource();
			dataSource.setPassword(dataBaseProperties.getProperty("jdbc.pass"));
			dataSource.setUser(dataBaseProperties.getProperty("jdbc.user"));
			dataSource.setURL(dataBaseProperties.getProperty("jdbc.url"));
		}
		return connectionFactory;
	}

	/**
	 * Returns new connection.
	 *
	 * @return java.sql.Connection
	 * @throws SQLException exception
	 */
	public Connection getConnection()
			throws SQLException {
		return dataSource.getConnection();
	}

}
