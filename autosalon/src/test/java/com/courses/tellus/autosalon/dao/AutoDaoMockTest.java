package com.courses.tellus.autosalon.dao;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
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
    private Auto auto;

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

        auto = new Auto();
        auto.setId(1L);
        auto.setBrand("BMW");
        auto.setModel("X5");
        auto.setManufactYear(1027);
        auto.setProducerCountry("Germany");
        auto.setPrice(new BigDecimal(500000));
    }

    @Test
    public void testAddAutoWhenResultTrue() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(1);
        int result = autoDao.addAuto(auto);
        Assertions.assertTrue(result == 1);
    }

    @Test
    public void testAddAutoWhenResultFalse() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenThrow(new SQLException());
        Assertions.assertEquals(0, autoDao.addAuto(auto));
    }

    @Test
    public void testUpdateAutoWhenResultTrue() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(1);
        int result = autoDao.updateAuto(auto);
        Assertions.assertTrue(result == 1);
    }

    @Test
    public void testUpdateAutoWhenResultFalse() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenThrow(new SQLException());
        int result = autoDao.updateAuto(auto);
        Assertions.assertTrue(result == 0);
    }

    @Test
    public void testRemoveAutoWhenResultTrue() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(1);
        int rezult = autoDao.removeAutoById(2L);
        Assertions.assertTrue(rezult == 1);
    }

    @Test
    public void testRemoveAutoWhenResultFalse() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenThrow(new SQLException());
        int result = autoDao.removeAutoById(2L);
        Assertions.assertTrue(result == 0);
    }

    @Test
    public void testGetAutoByIdWhenResultTrue() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getLong("ID")).thenReturn(auto.getId());
        when(mockResultSet.getString("AUTO_BRAND")).thenReturn(auto.getBrand());
        when(mockResultSet.getString("AUTO_MODEL")).thenReturn(auto.getModel());
        when(mockResultSet.getInt("MANUFACT_YEAR")).thenReturn(auto.getManufactYear());
        when(mockResultSet.getString("COUNTRY")).thenReturn(auto.getProducerCountry());
        when(mockResultSet.getBigDecimal("PRICE")).thenReturn(auto.getPrice());
        Assertions.assertEquals(auto, autoDao.getAutoById(1L));
    }

    @Test
    public void testGetAutoByIdWhenResultFalse() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);
        when(mockResultSet.getLong("ID")).thenReturn(auto.getId());
        when(mockResultSet.getString("AUTO_BRAND")).thenReturn(auto.getBrand());
        when(mockResultSet.getString("AUTO_MODEL")).thenReturn(auto.getModel());
        when(mockResultSet.getInt("MANUFACT_YEAR")).thenReturn(auto.getManufactYear());
        when(mockResultSet.getString("COUNTRY")).thenReturn(auto.getProducerCountry());
        when(mockResultSet.getBigDecimal("PRICE")).thenReturn(auto.getPrice());
        Assertions.assertNotEquals(auto, autoDao.getAutoById(2L));
    }

    @Test
    public void testQueryAutoWhenResultFalse() throws SQLException {
        List<Auto> autoList = new ArrayList<>();
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenThrow(new SQLException());;
        when(mockResultSet.next()).thenReturn(false);
        Assertions.assertEquals(autoList.size(), autoDao.queryAuto().size());
    }

    @Test
    public void testQueryAutoWhenResultTrue() throws SQLException {
        List<Auto> autoList = new ArrayList<>();
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        auto.setId(mockResultSet.getLong("ID"));
        auto.setBrand(mockResultSet.getString("AUTO_BRAND"));
        auto.setModel(mockResultSet.getString("AUTO_MODEL"));
        auto.setManufactYear(mockResultSet.getInt("MANUFACT_YEAR"));
        auto.setProducerCountry(mockResultSet.getString("COUNTRY"));
        auto.setPrice(mockResultSet.getBigDecimal("PRICE"));
        autoList.add(auto);
        Assertions.assertEquals(autoList.size(), autoDao.queryAuto().size());
    }
}