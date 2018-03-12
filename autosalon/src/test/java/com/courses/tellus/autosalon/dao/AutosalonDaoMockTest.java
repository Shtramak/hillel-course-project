package com.courses.tellus.autosalon.dao;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Optional;

import com.courses.tellus.autosalon.config.ConnectionFactory;
import com.courses.tellus.autosalon.model.Autosalon;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AutosalonDaoMockTest {

    private static AutosalonDao autosalonDao;
    private Autosalon autosalon;
    private static ConnectionFactory connectionFactory;
    private Connection mockConnection;
    private PreparedStatement mockStatement;
    private ResultSet mockResultset;

    @BeforeAll
    public static void init() {
        connectionFactory = mock(ConnectionFactory.class);
        autosalonDao = new AutosalonDao(connectionFactory);
    }

    @BeforeEach
    public void reInitDepartmentDao() throws SQLException {
        mockConnection = mock(Connection.class);
        mockStatement = mock(PreparedStatement.class);
        mockResultset = mock(ResultSet.class);
        when(connectionFactory.getConnection()).thenReturn(mockConnection);
        autosalon = new Autosalon(1L, "Geely", "China", "0000");
    }

    @Test
    public void testInsertAutosalonDao() throws Exception {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(1);
        int result = autosalonDao.insert(autosalon);
        Assertions.assertTrue(result == 1);
    }

    @Test
    public void testInsertAutosalonDaoIfNull() throws Exception {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(0);
        int result = autosalonDao.insert(autosalon);
        Assertions.assertTrue(result == 0);
    }

    @Test
    public void testInsertAutosalonDaoIfSQLException() throws Exception {
        when(mockConnection.prepareStatement(anyString())).thenThrow(SQLException.class);
        MatcherAssert.assertThat(autosalonDao.insert(autosalon), CoreMatchers.is(0));
    }

    @Test
    public void testUpdateAutoWhenResultTrue() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(1);
        int result = autosalonDao.update(autosalon);
        Assertions.assertTrue(result == 1);
    }

    @Test
    public void testUpdateAutoWhenResultFalse() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenThrow(new SQLException());
        int result = autosalonDao.update(autosalon);
        Assertions.assertTrue(result == 0);
    }

    @Test
    public void testGetAutoSalonById() throws Exception {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultset);
        when(mockResultset.next()).thenReturn(true);
        Assertions.assertNotNull(autosalonDao.getById(1L));
    }

    @Test
    public void testGetAutoSalonByIdIfResultsetNull() throws Exception {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultset);
        when(mockResultset.next()).thenReturn(false);
        Assertions.assertTrue(autosalonDao.getById(1L).equals(Optional.empty()));
    }

    @Test
    public void testGetAutoSalonByIdIfSQLException() throws Exception {
        when(mockConnection.prepareStatement(anyString())).thenThrow(SQLException.class);
        Assertions.assertEquals(Optional.empty(), autosalonDao.getById(1L));

    }

    @Test
    public void testAllAutosalonIfResultsetNull() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultset);
        when(mockResultset.next()).thenReturn(false);
        Assertions.assertTrue(autosalonDao.getAll().isEmpty());
    }

    @Test
    public void testAllAutosalonIfResultsetHasNext() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultset);
        autosalon = new Autosalon(1L, "Geely", "China", "0000");
        when(mockResultset.getLong("id")).thenReturn(autosalon.getId());
        when(mockResultset.getString("name")).thenReturn(autosalon.getName());
        when(mockResultset.getString("address")).thenReturn(autosalon.getAddress());
        when(mockResultset.getString("telephone")).thenReturn(autosalon.getTelophone());
        when(mockResultset.next()).thenReturn(true).thenReturn(false);
        Assertions.assertEquals(Collections.singletonList(autosalon).toString(), autosalonDao.getAll().toString());
    }

    @Test
    public void testAllAutosalonIfSQLException() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenThrow(SQLException.class);
        Assertions.assertTrue(autosalonDao.getAll().isEmpty());
    }

    @Test
    public void testDeleteAutoSalonById() throws Exception {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(1);
        int result = autosalonDao.insert(autosalon);
        Assertions.assertTrue(result == 1);
    }

    @Test
    public void testDeleteAutoSalonByIdIfNull() throws Exception {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(0);
        int result = autosalonDao.delete(0L);
        Assertions.assertTrue(result == 0);
    }

    @Test
    public void testDeleteAutoSalonByIdIfSQLException() throws Exception {
        when(mockConnection.prepareStatement(anyString())).thenThrow(SQLException.class);
        MatcherAssert.assertThat(autosalonDao.delete(0L), CoreMatchers.is(0));
    }

    @Test
    public void testGetPreparedStatement() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        Assertions.assertNotNull(autosalonDao.getPreparedStatement(mockStatement, new Autosalon()));
    }

    @Test
    public void testGetPreparedStatementIfNull() throws SQLException {
        when(mockStatement.isClosed()).thenReturn(false);
        Assertions.assertFalse(autosalonDao.getPreparedStatement(mockStatement, new Autosalon()).execute());
    }
}
