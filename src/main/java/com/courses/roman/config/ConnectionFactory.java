package com.courses.roman.config;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.h2.jdbcx.JdbcDataSource;

public class ConnectionFactory {

	private static ConnectionFactory connectionFactory = null;
	private static JdbcDataSource dataSource;

	private ConnectionFactory() {
	}

	public static ConnectionFactory getInstance() {
		if(connectionFactory == null) {
			connectionFactory = new ConnectionFactory();
			Properties dataBaseProperties = new Properties();
			try {
				dataBaseProperties.load(ClassLoader.getSystemResourceAsStream("data-base.properties"));
			}
			catch (IOException e) {
				System.err.print("Error In reading file");
			}
			dataSource = new JdbcDataSource();
			dataSource.setPassword(dataBaseProperties.getProperty("jdbc.pass"));
			dataSource.setUser(dataBaseProperties.getProperty("jdbc.user"));
			dataSource.setURL(dataBaseProperties.getProperty("jdbc.url"));
		}
		return connectionFactory;
	}

	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

}
