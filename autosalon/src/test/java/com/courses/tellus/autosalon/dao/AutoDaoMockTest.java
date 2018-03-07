package com.courses.tellus.autosalon.dao;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.courses.tellus.autosalon.config.ConnectionFactory;
import com.courses.tellus.autosalon.model.Auto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AutoDaoMockTest {

    private static ConnectionFactory connectionFactory;
    private Connection mockConnection;
    private PreparedStatement mockStatement;
    private ResultSet mockResultSet;
    private static AutoDao autoDao;

    @BeforeAll
    public static void init() {
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
        Assertions.assertTrue(result == 1);
    }

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
        Assertions.assertTrue(result == 1);
    }

    @Test
    public void testRemoveAuto() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(1);
        int rezult = autoDao.removeAutoById(2L);
        Assertions.assertTrue(rezult == 1);
    }

    @Test
    public void testQueryAuto() throws SQLException {
        Auto auto = new Auto();
        List<Auto> list = new ArrayList<Auto>();
        List<Auto> spy = spy(list);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.getResultSet()).thenReturn(mockResultSet);
        while (mockResultSet.next()) {
            auto.setId(mockResultSet.getLong("id"));
            auto.setBrand(mockResultSet.getString("AUTO_BRAND"));
            auto.setModel(mockResultSet.getString("AUTO_MODEL"));
            auto.setManufactYear(mockResultSet.getInt("MANUFACT_YEAR"));
            auto.setProducerCountry(mockResultSet.getString("COUNTRY"));
            auto.setPrice(mockResultSet.getBigDecimal("PRICE"));
            spy.add(auto);
        }
        Assertions.assertEquals(mockResultSet.getType(), spy.size());
    }
}
