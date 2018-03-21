package com.courses.tellus.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.dao.jdbc.SubjectDao;
import com.courses.tellus.connection.jdbc.ConnectionFactory;

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
        Optional<List<Subject>> opt = subjectDao.getAll();
        Assertions.assertFalse(opt.isPresent());
    }

    @Test
    void testGetEntityById() throws Exception {
        Optional<Subject> opt = subjectDao.getById(1L);
        Assertions.assertFalse(opt.isPresent());
    }

    @Test
    void testUpdateSubject() throws Exception {
        Assertions.assertEquals(0, subjectDao.update(subject));
    }

    @Test
    void testDeleteSubject() throws Exception {
        Assertions.assertEquals(0, subjectDao.delete(1L));
    }

    @Test
    void testInsertSubject() throws Exception {
        Assertions.assertEquals(0, subjectDao.insert(subject));
    }
}