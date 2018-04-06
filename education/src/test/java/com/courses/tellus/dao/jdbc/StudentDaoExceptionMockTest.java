package com.courses.tellus.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.dao.jdbc.StudentDao;
import com.courses.tellus.connection.jdbc.ConnectionFactory;
import com.courses.tellus.entity.Student;
import com.courses.tellus.exception.jdbc.DatabaseConnectionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentDaoExceptionMockTest {

    private static ConnectionFactory connectionFactory;
    private static StudentDao studentDao;
    private Student student;

    @BeforeAll
    static void init() {
        connectionFactory = mock(ConnectionFactory.class);
        studentDao = new StudentDao(connectionFactory);
    }

    @BeforeEach
    void reInitDepartmentDao() throws SQLException {
        student = new Student();
        Connection mockConnection = mock(Connection.class);
        when(connectionFactory.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenThrow(new SQLException());
    }

    @Test
    void testGetAllEntity() throws Exception {
        assertThrows(DatabaseConnectionException.class, () -> studentDao.getAll());
    }

    @Test
    void testGetEntityById() throws Exception {
        assertThrows(DatabaseConnectionException.class, () -> studentDao.getById(1L));
    }

    @Test
    void testUpdateSubject() throws Exception {
        assertThrows(DatabaseConnectionException.class, () -> studentDao.update(student));
    }

    @Test
    void testDeleteSubject() throws Exception {
        assertThrows(DatabaseConnectionException.class, () -> studentDao.delete(1L));
    }

    @Test
    void testInsertSubject() throws Exception {
        assertThrows(DatabaseConnectionException.class, () -> studentDao.insert(student));
    }
}