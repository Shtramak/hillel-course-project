package com.courses.airport.mysqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainConnection {
    private Connection con = null;
    private String urlDb = "";
    private String userNameDb ="";
    private String pswDb ="";
    private String databaseName ="";


    // Method that returnes connection to remote mySQL database
    public Connection getConnection () {

        //JDBC driver registration and autentification to database
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(urlDb, userNameDb, pswDb);
        } catch (ClassNotFoundException e) {
            System.out.println("Error while connectiong JDBC driver.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error while autentification. Check urlDb, userNameDb or pswDb.");
            e.printStackTrace();
        }
        return con;
    }


    public void closeConnection () {
        try {
            con.close();
        } catch (SQLException e) {
            System.out.println("Error while closing 'con' connection");
            e.printStackTrace();
        }
    }
}
