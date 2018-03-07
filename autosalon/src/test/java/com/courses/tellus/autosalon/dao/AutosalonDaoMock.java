package com.courses.tellus.autosalon.dao;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.courses.tellus.autosalon.model.Autosalon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AutosalonDaoMock {

    private static AutosalonDao autosalonDao;
    private static Autosalon autosalon;
    private Connection mockConnection;
    private PreparedStatement mockStatement;
    private ResultSet mockResultset;

    @BeforeEach
    public void reInitDepartmentDao() throws SQLException {
        mockConnection = mock(Connection.class);
        mockStatement = mock(PreparedStatement.class);
        mockResultset = mock(ResultSet.class);
        autosalonDao = new AutosalonDao(mockConnection);
        autosalon = new Autosalon(1L, "Geely", "China", "0000");
    }

    @Test
    public void testAddAutosalonDao() throws Exception {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(1);
        int result = autosalonDao.addAutosalon(autosalon);
        Assertions.assertTrue(result == 1);
    }

    @Test
    public void testGetAutoSalonById() throws Exception {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultset);
        when(mockResultset.next()).thenReturn(true);
        Assertions.assertNull(autosalonDao.getAutoSalonById(1));

    }

    @Test
    public void testFindAllAutosalon() throws SQLException {
        List<Autosalon> list = new ArrayList<Autosalon>();
        List<Autosalon> spy = spy(list);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.getResultSet()).thenReturn(mockResultset);
        while (mockResultset.next()) {
            autosalon.setId(mockResultset.getLong("id"));
            autosalon.setName(mockResultset.getString("name"));
            autosalon.setAddress(mockResultset.getString("address"));
            autosalon.setTelophone(mockResultset.getString("telophone"));
            spy.add(autosalon);
        }
        Assertions.assertEquals(mockResultset.getType(), spy.size());
    }

    @Test
    public void testRemoveAutoSalonById() throws Exception {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(1);
        int result = autosalonDao.addAutosalon(autosalon);
        Assertions.assertTrue(result == 1);
    }

    @Test
    public void testSqlExceptionAddAutosalon() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenThrow(SQLException.class);
        Assertions.assertEquals(0, autosalonDao.addAutosalon(autosalon));
    }

    @Test
    public void testSqlExceptionGetAutoSalonById() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenThrow(SQLException.class);
        Assertions.assertEquals(null, autosalonDao.getAutoSalonById(0));
    }

    @Test
    public void testSqlExceptionGetAllAutoSalon() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenThrow(SQLException.class);
        Assertions.assertEquals(null, autosalonDao.findAllAutosalon());
    }

    @Test
    public void testSqlExceptionRemoveAutoSalonId() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenThrow(SQLException.class);
        Assertions.assertEquals(0, autosalonDao.removeAutoSalonId(0));
    }

    @Test
    public void testIfResultSetNullGetAutoSalonId() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultset);
        when(mockResultset.next()).thenReturn(false);
        Assertions.assertNotNull(autosalonDao.getAutoSalonById(1));
    }
}
