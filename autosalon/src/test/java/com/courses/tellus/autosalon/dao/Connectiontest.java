package com.courses.tellus.autosalon.dao;

import com.courses.tellus.autosalon.config.ConnectionFactory;
import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Connectiontest {


    private Connection connection;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();;

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

    @Test
    public void getConnectionTestFalse3() {
        expectedException.expect(SQLException.class);
        ConnectionFactory.getInstance();
    }

}
