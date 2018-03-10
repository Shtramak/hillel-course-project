package com.courses.tellus.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;

import com.courses.tellus.dbconnection.ConnectionFactory;
import com.courses.tellus.entity.Student;
import com.courses.tellus.entity.Subject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentDaoExceptionMockTest {

    private static ConnectionFactory connectionFactory;
    private static StudentDao studentDao;
    private Connection mockConnection;
    private Student student;

    @BeforeAll
    static void init() {
        connectionFactory = mock(ConnectionFactory.class);
        studentDao = new StudentDao(connectionFactory);
    }

    @BeforeEach
    void reInitDepartmentDao() throws SQLException {
        student = new Student();
        mockConnection = mock(Connection.class);
        when(connectionFactory.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenThrow(new SQLException());
    }

    @Test
    void testGetAllEntity() throws Exception {
        List<Student> list = studentDao.getAll();
        Assertions.assertNull(list);
    }

    @Test
    void testGetEntityById() throws Exception {
        Student student = studentDao.getById(1L);
        Assertions.assertNull(student);
    }

    @Test
    void testUpdateSubject() throws Exception {
        Assertions.assertEquals(0, studentDao.update(student));
    }

    @Test
    void testDeleteSubject() throws Exception {
        Assertions.assertEquals(0, studentDao.delete(1L));
    }

    @Test
    void testInsertSubject() throws Exception {
        Assertions.assertEquals(0, studentDao.insert(student));
    }
}
