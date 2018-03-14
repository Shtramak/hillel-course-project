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
import java.util.Optional;

import com.courses.tellus.autosalon.config.ConnectionFactory;
import com.courses.tellus.autosalon.model.Auto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AutoDaoMockTest {

    private static ConnectionFactory connectionFactory;
    private PreparedStatement mockStatement;
    private ResultSet mockResultSet;
    private static AutoDao autoDao;

    private static final Auto AUTO = new Auto(1L, "BMW", "X5", 2017, "Germany", new BigDecimal(50000));

    @BeforeAll
    public static void init() {
        connectionFactory = mock(ConnectionFactory.class);
        autoDao = new AutoDao(connectionFactory);
    }

    @BeforeEach
    public void reInitAutodao() throws SQLException {
        Connection mockConnection = mock(Connection.class);
        mockStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);
        when(connectionFactory.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
    }

    @Test
    public void testAddAutoWhenResultTrue() throws SQLException {
        when(mockStatement.executeUpdate()).thenReturn(1);
        int result = autoDao.insert(AUTO);
        Assertions.assertTrue(result == 1);
    }

    @Test
    public void testAddAutoWhenResultFalse() throws SQLException {
        when(mockStatement.executeUpdate()).thenThrow(new SQLException());
        int result = autoDao.insert(AUTO);
        Assertions.assertTrue(result == 0);
    }

    @Test
    public void testUpdateAutoWhenResultTrue() throws SQLException {
        when(mockStatement.executeUpdate()).thenReturn(1);
        int result = autoDao.update(AUTO);
        Assertions.assertTrue(result == 1);
    }

    @Test
    public void testUpdateAutoWhenResultFalse() throws SQLException {
        when(mockStatement.executeUpdate()).thenThrow(new SQLException());
        int result = autoDao.update(AUTO);
        Assertions.assertTrue(result == 0);
    }

    @Test
    public void testRemoveAutoWhenResultTrue() throws SQLException {
        when(mockStatement.executeUpdate()).thenReturn(1);
        int rezult = autoDao.delete(2L);
        Assertions.assertTrue(rezult == 1);
    }

    @Test
    public void testRemoveAutoWhenResultFalse() throws SQLException {
        when(mockStatement.executeUpdate()).thenThrow(new SQLException());
        int result = autoDao.delete(2L);
        Assertions.assertTrue(result == 0);
    }

    @Test
    public void testGetAutoByIdWhenResultTrue() throws SQLException {
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        putAutoIntoResultSetMock();
        Assertions.assertEquals(Optional.of(AUTO), autoDao.getById(1L));
    }

    @Test
    public void testGetAutoByIdWhenResultFalse() throws SQLException {
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);
        Assertions.assertNotEquals(AUTO, autoDao.getById(2L));
    }

    @Test
    public void testQueryAutoWhenResultFalse() throws SQLException {
        List<Auto> autoList = new ArrayList<>();
        when(mockStatement.executeQuery()).thenThrow(new SQLException());
        when(mockResultSet.next()).thenReturn(false);
        Assertions.assertEquals(autoList.size(), autoDao.getAll().size());
    }

    @Test
    public void testQueryAutoWhenResultTrue() throws SQLException {
        List<Auto> autoList = new ArrayList<>();
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        autoList.add(AUTO);
        Assertions.assertEquals(autoList.size(), autoDao.getAll().size());
    }

    private void putAutoIntoResultSetMock() throws SQLException {
        when(mockResultSet.getLong("ID")).thenReturn(AUTO.getId());
        when(mockResultSet.getString("AUTO_BRAND")).thenReturn(AUTO.getBrand());
        when(mockResultSet.getString("AUTO_MODEL")).thenReturn(AUTO.getModel());
        when(mockResultSet.getInt("MANUFACT_YEAR")).thenReturn(AUTO.getManufactYear());
        when(mockResultSet.getString("COUNTRY")).thenReturn(AUTO.getProducerCountry());
        when(mockResultSet.getBigDecimal("PRICE")).thenReturn(AUTO.getPrice());
    }
}
