package com.courses.tellus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.courses.tellus.dbconnection.ConnectionFactory;

import org.h2.jdbc.JdbcSQLException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SubjectDaoExceptionMockTest {

    private static ConnectionFactory connectionFactory;
    private Connection mockConnection;
    private PreparedStatement mockStatement;
    private SubjectDao subjectDao;
    private SQLException mockSQL;

    @BeforeAll
    static void init() {
        connectionFactory = mock(ConnectionFactory.class);

    }

    @BeforeEach
    void reInitDepartmentDao() throws SQLException {
        subjectDao = new SubjectDao(connectionFactory);
        mockConnection = mock(Connection.class);
        mockStatement = mock(PreparedStatement.class);
        when(connectionFactory.getConnection()).thenThrow(new SQLException());
    }

    @Test
    void testCRUDExceptionThrows() {
        assertThrows(SQLException.class, () -> subjectDao.delete(1));
    }
}
