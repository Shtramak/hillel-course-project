package com.courses.airport.mysqlconnection;

import java.sql.*;

public class MainConnection {
    private static MainConnection instance;
    public static MainConnection getInstance(){
        if (instance == null) {
            instance = new MainConnection();
        }
        return instance;
    }

    private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/airport_tickets?useSSL=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "2439134";
    private Connection connection;

    public Connection connect() {
        try {
            Class.forName(DATABASE_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
