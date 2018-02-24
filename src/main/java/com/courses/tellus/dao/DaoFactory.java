package main.java.com.courses.tellus.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface DaoFactory {
    Connection getConnection() throws SQLException;

    AirDao getGroupDao(Connection connection);
}
