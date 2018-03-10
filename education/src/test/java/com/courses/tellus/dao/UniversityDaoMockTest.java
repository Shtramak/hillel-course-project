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
        Connection mockConnection = mock(Connection.class);
        mockPreState = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);
        when(connectionFactory.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreState);
        university = new University(1L,"KPI","pr.Peremohy","Technical");
    }

    @Test
    void testInsertUniversity() throws Exception {
        when(mockPreState.executeUpdate()).thenReturn(1);
        assertEquals(1, universityDao.insert(university));
    }

    @Test
    void testGetUniversityById() throws Exception {
        mockPreState.setLong(1, 1);
        when(mockPreState.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        setUniversityInResultSetMock();
        assertEquals(university, universityDao.getById(university.getUniId()));
    }

    @Test
    void testGetByIdWhenReturnNull() throws Exception {
        mockPreState.setLong(1, 1);
        when(mockPreState.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);
        setUniversityInResultSetMock();
        assertNull(universityDao.getById(university.getUniId()));
    }

    @Test
    void testGetAllUniversities() throws Exception {
        List<University> universities = new ArrayList<>();
        List<University> spy = spy(universities);
        when(mockPreState.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        setUniversityInResultSetMock();
        when(spy.add(university)).thenReturn(false);
        Assertions.assertEquals(spy.size(), universityDao.getAll().size());
    }

    @Test
    void testUpdateUniversity() throws Exception {
        when(mockPreState.executeUpdate()).thenReturn(1);
        assertEquals(1,universityDao.update(university));
    }

    @Test
    void testDeleteUniversity() throws Exception {
        when(mockPreState.executeUpdate()).thenReturn(1);
        assertEquals(1,universityDao.delete(1L));
    }

    private void setUniversityInResultSetMock() throws SQLException {
        when(mockResultSet.getLong("univer_id")).thenReturn(university.getUniId());
        when(mockResultSet.getString("name_of_university")).thenReturn(university.getNameOfUniversity());
        when(mockResultSet.getString("address")).thenReturn(university.getAddress());
        when(mockResultSet.getString("specialization")).thenReturn(university.getSpecialization());
    }

}

