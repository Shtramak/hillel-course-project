package com.courses.tellus.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.config.jdbc.ConnectionFactory;
import com.courses.tellus.model.Subject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
                LocalDate.of(2000, 10, 15).toString());
        Connection mockConnection = mock(Connection.class);
        when(connectionFactory.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenThrow(new SQLException());
    }

    @Test
    void testGetAllEntity() throws Exception {
        List<Subject> opt = subjectDao.getAll();
        assertEquals(0, opt.size());
    }

    @Test
    void testGetEntityById() throws Exception {
        Optional<Subject> opt = subjectDao.getById(1L);
        assertFalse(opt.isPresent());
    }

    @Test
    void testUpdateSubject() throws Exception {
        assertEquals(0, subjectDao.update(subject));
    }

    @Test
    void testDeleteSubject() throws Exception {
        assertEquals(0, subjectDao.delete(1L));
    }

    @Test
    void testInsertSubject() throws Exception {
        assertEquals(0, subjectDao.insert(subject));
    }
}