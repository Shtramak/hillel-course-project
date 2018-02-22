package com.courses.tellus.db_connection;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class MySqlConnection {

    public static Connection getMySQLConnection() throws IOException, ClassNotFoundException, SQLException {

            Properties props = new Properties();

            FileInputStream in = new FileInputStream("tellus/education/src/main/resources/db.properties");
            props.load(in);

            Class.forName(props.getProperty("DB_DRIVER_CLASS"));

            Connection connection = DriverManager.getConnection(
                    props.getProperty("DB_URL"),
                    props.getProperty("DB_USERNAME"),
                    props.getProperty("DB_PASSWORD"));

            return connection;
        }
    }
