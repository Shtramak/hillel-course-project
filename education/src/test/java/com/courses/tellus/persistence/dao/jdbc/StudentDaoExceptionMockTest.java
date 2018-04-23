package com.courses.tellus.persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.config.jdbc.ConnectionFactory;
import com.courses.tellus.entity.model.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
        List<Student> studentList = studentDao.getAll();
        assertEquals(0, studentList.size());
    }

    @Test
    void testGetEntityById() throws Exception {
        Optional<Student> opt = studentDao.getById(1L);
        assertFalse(opt.isPresent());
    }

    @Test
    void testUpdateSubject() throws Exception {
        assertEquals(0, studentDao.update(student));
    }

    @Test
    void testDeleteSubject() throws Exception {
        assertEquals(0, studentDao.delete(1L));
    }

    @Test
    void testInsertSubject() throws Exception {
        assertEquals(0, studentDao.insert(student));
    }
}