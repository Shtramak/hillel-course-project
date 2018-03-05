package com.courses.tellus.autosalon.dao;

import com.courses.tellus.autosalon.config.ConnectionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactoryTest {

    private Connection connection;
    @Test
    public void getConnectionTestTrue() throws SQLException {
        connection= ConnectionFactory.getInstance().getConnection();
        Assertions.assertTrue(connection != null);
    }

    @Test
    public void getConnectionTestFalse() throws SQLException {
        connection= ConnectionFactory.getInstance().getConnection();
        Assertions.assertFalse(connection == null);
    }
}
