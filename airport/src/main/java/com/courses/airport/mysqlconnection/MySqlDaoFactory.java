package com.courses.airport.mysqlconnection;


import com.courses.airport.dao.AirDao;
import com.courses.airport.dao.DaoFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDaoFactory implements DaoFactory {

    private String user = "root";
    private String password = "admin";
    private String url = "jdbc:mysql://localhost:3306/airport";//URL адрес
    private String driver = "com.mysql.jdbc.Driver";//Имя драйвера


    @Override
    public Connection getConnection() throws SQLException {
        return  DriverManager.getConnection(url, user, password);
    }

    @Override
    public AirDao getGroupDao(Connection connection) {
        return new MySqlAirDao(connection);
    }

    public MySqlDaoFactory() {
        try {
            Class.forName(driver);//Регистрируем драйвер
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
