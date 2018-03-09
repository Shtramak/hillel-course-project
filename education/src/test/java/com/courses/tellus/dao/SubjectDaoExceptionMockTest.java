package com.courses.tellus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;

import com.courses.tellus.dbconnection.ConnectionFactory;

import com.courses.tellus.entity.Subject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SubjectDaoExceptionMockTest {

    private static ConnectionFactory connectionFactory;
    private static SubjectDao subjectDao;
    private Connection mockConnection;
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
        mockConnection = mock(Connection.class);
        when(connectionFactory.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenThrow(new SQLException());
    }

    @Test
    void testGetAllEntity() throws Exception {
        List<Subject> list = subjectDao.getAllEntity();
        Assertions.assertNull(list);
    }

    @Test
    void testGetEntityById() throws Exception {
        Subject subject = subjectDao.getEntityById(1L);
        Assertions.assertNull(subject);
    }

    @Test
    void testUpdateSubject() throws Exception {
        boolean result = subjectDao.update(subject);
        Assertions.assertFalse(result);
    }

    @Test
    void testDeleteSubject() throws Exception {
        boolean result = subjectDao.delete(1L);
        Assertions.assertFalse(result);
    }

    @Test
    void testInsertSubject() throws Exception {
        Subject subject = new Subject(
                2L, "Math", "Teach how to calculate numbers", true,
                new GregorianCalendar(1996,5,12));
        boolean result = subjectDao.insert(subject);
        Assertions.assertFalse(result);
    }
}
