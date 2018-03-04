package com.courses.tellus.autosalon.dao;

import com.courses.tellus.autosalon.config.ConnectionFactory;
import com.courses.tellus.autosalon.model.Auto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AutoDaoMock {

    private static ConnectionFactory connectionFactory;
    private Connection mockConnection;
    private PreparedStatement mockStatement;
    private ResultSet mockResultSet;
    private static AutoDao autoDao;

    @BeforeAll
    public static void init(){
        connectionFactory = mock(ConnectionFactory.class);
        autoDao = new AutoDao(connectionFactory);
    }

    @BeforeEach
    public void reInitAutodao() throws SQLException {
        mockConnection = mock(Connection.class);
        mockStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);
        when(connectionFactory.getConnection()).thenReturn(mockConnection);
    }

    /**
     * Testing method addAuto when result true.
     *
     * @throws SQLException exception.
     */
    @Test
    public void testAddAuto() throws SQLException {
        Auto auto = new Auto();
        auto.setId(1L);
        auto.setBrand("BMW");
        auto.setModel("X5");
        auto.setManufactYear(2007);
        auto.setProducerCountry("Germany");
        auto.setPrice(new BigDecimal(500000));

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(1);
        int result = autoDao.addAuto(auto);
        Assertions.assertTrue( result == 1);
    }

    /**
     * Testing method updateAuto when result true.
     *
     * @throws SQLException exception.
     */
    @Test
    public void testUpdateAuto() throws SQLException {
        Auto auto = new Auto();
        auto.setId(1L);
        auto.setBrand("BMW");
        auto.setModel("X5");
        auto.setManufactYear(1027);
        auto.setProducerCountry("Germany");
        auto.setPrice(new BigDecimal(500000));

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(1);
        int result = autoDao.updateAuto(auto);
        Assertions.assertTrue( result == 1);
    }

    /**
     * Testing method remove auto from Database by id
     *
     * @throws SQLException exception.
     */
    @Test
    public void testRemoveAuto() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(1);
        int rezult = autoDao.removeAutoById(2L);
        Assertions.assertTrue( rezult == 1);
    }
}
