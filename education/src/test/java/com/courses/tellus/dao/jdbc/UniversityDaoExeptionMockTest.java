package com.courses.tellus.dao.jdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.SQLException;

import com.courses.tellus.dao.jdbc.UniversityDao;
import com.courses.tellus.exception.jdbc.DatabaseConnectionException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.courses.tellus.connection.jdbc.ConnectionFactory;
import com.courses.tellus.entity.University;

public class UniversityDaoExeptionMockTest {
    private static ConnectionFactory connectionFactory;
    private University university;
    private static UniversityDao universityDao;

    @BeforeAll
    static void init() {
        connectionFactory = mock(ConnectionFactory.class);
        universityDao = new UniversityDao(connectionFactory);
    }

    @BeforeEach
    void initMocks() throws Exception {
        university =new University(1L,"KPI",
                "pr.Peremohy","Technical");
        Connection mockConnection = mock(Connection.class);
        when(connectionFactory.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenThrow(new SQLException());
    }

    @Test
    void testGetByIdException() throws Exception {
        assertThrows(DatabaseConnectionException.class, () -> universityDao.getById(1L));
    }

    @Test
    void testGetAllUniversitiesExeption() throws Exception {
        assertThrows(DatabaseConnectionException.class, () -> universityDao.getAll());
    }

    @Test
    void testInsertUniversityExeption() throws Exception {
        assertThrows(DatabaseConnectionException.class, () -> universityDao.insert(university));
    }

    @Test
    void testUpdateUniversityException() throws Exception {
        assertThrows(DatabaseConnectionException.class, () -> universityDao.update(university));
    }

    @Test
    void testDeleteUniversityException() throws Exception {
        assertThrows(DatabaseConnectionException.class, () -> universityDao.delete(1L));
    }
}