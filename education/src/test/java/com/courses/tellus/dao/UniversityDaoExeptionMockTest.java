package com.courses.tellus.dao;

import com.courses.tellus.dbconnection.ConnectionFactory;
import com.courses.tellus.entity.University;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UniversityDaoExeptionMockTest {
    private static ConnectionFactory connectionFactory;
    private University university;
    private Connection mockConnection;
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
        mockConnection = mock(Connection.class);
        when(connectionFactory.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenThrow(SQLException.class);
    }

    @Test
    void testInsertUniversityExeption() throws Exception {
        assertEquals(0, universityDao.insert(university));
    }

    @Test
    void testGetByIdWhenReturnNull() throws Exception {
        Assertions.assertNull(universityDao.getById(15L));
    }

    @Test
    void testGetByIdException() throws Exception {
        assertNull(universityDao.getById(1L));
    }

    @Test
    void testGetAllUniversitiesExeption() throws Exception {
        assertNull(universityDao.getAll());
    }

    @Test
    void testUpdateUniversityConnectionException() throws Exception {
        assertEquals(0,universityDao.update(university));
    }

    @Test
    void testDeleteUniversityException() throws Exception {
        assertEquals(0,universityDao.delete(university.getUniId()));
    }


}

