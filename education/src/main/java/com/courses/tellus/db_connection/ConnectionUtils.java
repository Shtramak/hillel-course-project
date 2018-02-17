package com.courses.tellus.db_connection;



import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtils {

    public static Connection getConnection() {

        Connection conn = null;

        try {
            conn = MySqlConnection.getMySQLConnection();
        } catch (ClassNotFoundException except) {
            except.printStackTrace();
        } catch (SQLException except) {
            except.printStackTrace();
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
