package com.courses.tellus.dao.jdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.courses.tellus.config.jdbc.ConnectionFactory;
import com.courses.tellus.model.University;

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
        assertFalse((universityDao.getById(1L)).isPresent());
    }

    @Test
    void testGetAllUniversitiesExeption() throws Exception {
        assertEquals(0, universityDao.getAll().size());
    }

    @Test
    void testInsertUniversityExeption() throws Exception {
        assertEquals(0, universityDao.insert(university));
    }

    @Test
    void testUpdateUniversityException() throws Exception {
        assertEquals(0,universityDao.update(university));
    }

    @Test
    void testDeleteUniversityException() throws Exception {
        assertEquals(0,universityDao.delete(university.getUniId()));
    }
}