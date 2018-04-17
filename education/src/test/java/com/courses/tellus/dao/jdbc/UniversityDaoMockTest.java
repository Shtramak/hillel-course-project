package com.courses.tellus.dao.jdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

import com.courses.tellus.config.jdbc.ConnectionFactory;
import com.courses.tellus.model.jdbc.University;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UniversityDaoMockTest {

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
    void testGetUniversityById() throws Exception {
        mockPreState.setLong(1, 1);
        when(mockPreState.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        setUniversityInResultSetMock();
        assertTrue((universityDao.getById(university.getUniId())).isPresent());
    }

    @Test
    void testGetByIdWhenReturnFalse() throws Exception {
        mockPreState.setLong(1, 1);
        when(mockPreState.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);
        setUniversityInResultSetMock();
        assertFalse((universityDao.getById(university.getUniId())).isPresent());
    }

    @Test
    void testGetAllUniversities() throws Exception {
        List<University> universities = new ArrayList<>();
        List<University> spy = spy(universities);
        when(mockPreState.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        setUniversityInResultSetMock();
        spy.add(university);
        assertEquals(1, universityDao.getAll().size());
    }

    @Test
    void testInsertUniversity() throws Exception {
        when(mockPreState.executeUpdate()).thenReturn(1);
        assertEquals(1, universityDao.insert(university));
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

