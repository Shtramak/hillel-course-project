package com.courses.tellus.autosalon.dao;

import com.courses.tellus.autosalon.config.ConnectionFactory;
import com.courses.tellus.autosalon.model.Autosalon;
import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class AutosalonDaoMock {

    private static AutosalonDao autosalonDao;
    private static Autosalon autosalon;
    private static ConnectionFactory connectionFactory;
    private Connection mockConnection;
    private PreparedStatement mockStatement;
    private ResultSet mockResultset;

    @BeforeAll
    public static void init() {
        connectionFactory = mock(ConnectionFactory.class);
        autosalonDao = new AutosalonDao(connectionFactory);
        autosalon = new Autosalon();
    }

    @BeforeEach
    public void reInitDepartmentDao() throws SQLException {
        mockConnection = mock(Connection.class);
        mockStatement = mock(PreparedStatement.class);
        mockResultset = mock (ResultSet.class);
        when(connectionFactory.getConnection()).thenReturn(mockConnection);
    }

    @Test
    public void testAddAutosalonDao() throws Exception {
        autosalon.setTelophone("5656565");
        autosalon.setAddress("China");
        autosalon.setName("Geely");
        autosalon.setId(1L);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(1);
        int result = autosalonDao.addAutosalon(autosalon);
        Assertions.assertTrue(result == 1);
    }

    @Test
    public void testGetAutoSalonById() throws Exception {
        autosalon.setId(0L);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.getResultSet()).thenReturn(mockResultset);
        Long number = mockResultset.getLong("id");
        TestCase.assertEquals(number, autosalon.getId());

    }

    @Test
    public void testFindAllAutosalon() throws SQLException {
        List<Autosalon> list = new ArrayList<Autosalon>();
        List<Autosalon> spy = spy(list);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.getResultSet()).thenReturn(mockResultset);
        while(mockResultset.next()) {
            autosalon.setId(mockResultset.getLong("id"));
            autosalon.setName(mockResultset.getString("name"));
            autosalon.setAddress(mockResultset.getString("address"));
            autosalon.setTelophone(mockResultset.getString("telophone"));
            spy.add(autosalon);
        }
        TestCase.assertEquals(mockResultset.getType(), spy.size());
    }

    @Test
    public void testRemoveAutoSalonById() throws Exception {
        autosalon.setTelophone("5656565");
        autosalon.setAddress("China");
        autosalon.setName("Geely");
        autosalon.setId(1L);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(1);
        int result = autosalonDao.addAutosalon(autosalon);
        Assertions.assertTrue(result == 1);

    }
}
