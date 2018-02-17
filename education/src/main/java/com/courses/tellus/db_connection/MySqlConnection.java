package com.courses.tellus.db_connection;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {

    public static Connection getMySQLConnection()
            throws ClassNotFoundException, SQLException {

        String hostName = "localhost";
        String dbName = "education";
        String userName = "root";
        String password = "admin";

        return createConnection(hostName, dbName, userName, password);
    }

    public static Connection createConnection(String hostName, String dbName, String userName, String password)
            throws ClassNotFoundException, SQLException  {

        Connection conn;
        Class.forName("com.mysql.jdbc.Driver");
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

        conn = DriverManager.getConnection(connectionURL, userName, password);

        return conn;
    }
}
