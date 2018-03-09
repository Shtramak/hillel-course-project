package com.courses.tellus.dao;


import com.courses.tellus.dbconnection.ConnectionFactory;
import com.courses.tellus.entity.University;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class UniversityDaoMockTest {

    private static ConnectionFactory connectionFactory;
    private Connection mockConnection;
    private University university;
    private PreparedStatement mockPreState;
    private ResultSet mockResultSet;
    private static UniversityDao universityDao;

    @BeforeAll
    static void init() {
        connectionFactory = mock(ConnectionFactory.class);
        universityDao = new UniversityDao(connectionFactory);
    }

    @BeforeEach
    void initMocks() throws Exception {
        mockConnection = mock(Connection.class);
        mockPreState = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);
        when(connectionFactory.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreState);
        university = new University(1L,"KPI","pr.Peremohy","Technical");
    }

    @Test
    void testCreateUniversityExeption() throws Exception {
        when(mockConnection.prepareStatement(anyString())).thenThrow(SQLException.class);
        assertFalse(universityDao.insert(university));
    }

    @Test
    void testCreateUniversity() throws Exception {
        when(mockPreState.executeUpdate()).thenReturn(0);
        assertTrue(universityDao.insert(university));
    }

    @Test
    void testGetUniversityById() throws Exception {
        mockPreState.setInt(1, 1);
        when(mockPreState.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        setUniversityInResultSetMock();
        assertEquals(university, universityDao.getEntityById(university.getUniId()));
    }
    @Test
    void testGetEntityByIdWhenReturnNull() throws Exception {
        when(mockPreState.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);
        Assertions.assertNull(universityDao.getEntityById(15L));
    }

    @Test
    void testGetByIdExeption() throws Exception {
        when(mockConnection.prepareStatement(anyString())).thenThrow(SQLException.class);
        assertNull(universityDao.getEntityById(1L));
    }

    @Test
    void testGetAllUniversities() throws Exception {
        List<University> universities = new ArrayList<>();
        List<University> spy = spy(universities);
        when(mockPreState.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        setUniversityInResultSetMock();
        when(spy.add(university)).thenReturn(false);
        Assertions.assertEquals(spy.size(), universityDao.getAllEntity().size());
    }

    @Test
    void testGetAllUniversitiesExeption() throws Exception {
        when(mockConnection.prepareStatement(anyString())).thenThrow(SQLException.class);
        assertNull(universityDao.getAllEntity());
    }


    @Test
    void testUpdateUniversity() throws Exception {
        when(mockPreState.executeUpdate()).thenReturn(0);
        assertTrue(universityDao.update(university));
    }

    @Test
    void testUptadeUniversityConnectionExeption() throws Exception {
    when(mockConnection.prepareStatement(anyString())).thenThrow(SQLException.class);
    assertFalse(universityDao.update(university));
    }


    @Test
    void testDeleteUniversity() throws Exception {
        when(mockPreState.executeUpdate()).thenReturn(0);
        Assertions.assertTrue(universityDao.delete(1L));
    }

    @Test
    void testDeleteUniversityExeption() throws Exception {
        when(mockConnection.prepareStatement(anyString())).thenThrow(SQLException.class);
        assertFalse(universityDao.delete(university.getUniId()));
    }

    private void setUniversityInResultSetMock() throws SQLException {
        when(mockResultSet.getLong("id")).thenReturn(university.getUniId());
        when(mockResultSet.getString("nameOfUniversity")).thenReturn(university.getNameOfUniversity());
        when(mockResultSet.getString("address")).thenReturn(university.getAddress());
        when(mockResultSet.getString("specialization")).thenReturn(university.getSpecialization());
    }

}

