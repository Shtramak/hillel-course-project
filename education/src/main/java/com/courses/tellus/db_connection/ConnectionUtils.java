package com.courses.tellus.db_connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtils {

    public static Connection getConnection() {

        Connection conn = null;

        try {
            conn = MySqlConnection.getMySQLConnection();
            System.out.println("Connection OK!");
        } catch (IOException except) {
            System.err.println("Wrong properties");
        }  catch (ClassNotFoundException e) {
            System.err.println("Can't find JDBC Driver");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public static void closeQuietly(Connection conn) {
        try {
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void rollbackQuietly(Connection conn) {
        try {
            conn.rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
