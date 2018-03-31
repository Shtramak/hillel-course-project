package com.courses.tellus.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.dao.jdbc.SubjectDao;
import com.courses.tellus.connection.jdbc.ConnectionFactory;

import com.courses.tellus.entity.Subject;
import com.courses.tellus.exception.jdbc.DatabaseConnectionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SubjectDaoExceptionMockTest {

    private static ConnectionFactory connectionFactory;
    private static SubjectDao subjectDao;
    private Subject subject;

    @BeforeAll
    static void init() {
        connectionFactory = mock(ConnectionFactory.class);
        subjectDao = new SubjectDao(connectionFactory);
    }

    @BeforeEach
    void reInitDepartmentDao() throws SQLException {
        subject = new Subject(1L, "Math", "Teach how to calculate numbers", true,
                new GregorianCalendar(1996,5,12));
        Connection mockConnection = mock(Connection.class);
        when(connectionFactory.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenThrow(new SQLException());
    }

    @Test
    void testGetAllEntity() throws Exception {
        assertThrows(DatabaseConnectionException.class, () -> subjectDao.getAll());
    }

    @Test
    void testGetEntityById() throws Exception {
        assertThrows(DatabaseConnectionException.class, () -> subjectDao.getById(1L));
    }

    @Test
    void testUpdateSubject() throws Exception {
        assertThrows(DatabaseConnectionException.class, () -> subjectDao.update(subject));
    }

    @Test
    void testDeleteSubject() throws Exception {
        assertThrows(DatabaseConnectionException.class, () -> subjectDao.delete(1L));
    }

    @Test
    void testInsertSubject() throws Exception {
        assertThrows(DatabaseConnectionException.class, () -> subjectDao.insert(subject));
    }
}