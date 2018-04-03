package com.courses.tellus.dao.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.courses.tellus.dao.jdbc.StudentDao;
import com.courses.tellus.connection.jdbc.ConnectionFactory;
import com.courses.tellus.entity.Student;
import com.courses.tellus.exception.jdbc.EntityIdNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

class StudentDaoMockTest {

    private static ConnectionFactory connFactory;
    private static StudentDao studentDao;
    private Student student;
    private ResultSet mockResSet;
    private PreparedStatement mockPreState;

    @BeforeAll
    static void init() {
        connFactory = mock(ConnectionFactory.class);
        studentDao = new StudentDao(connFactory);
    }

    @BeforeEach
    void reinitializeRequest() throws Exception {
        mockPreState = mock(PreparedStatement.class);
        mockResSet = mock(ResultSet.class);
        Connection mockConnection = mock(Connection.class);
        when(connFactory.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreState);
        when(mockPreState.executeQuery()).thenReturn(mockResSet);
        student = new Student("Andrey", "Petrov", "0123456789",
                "Peremohy str, 178");
    }

    @Test
    void testGetAllAndReturnEntityList() throws Exception {
        List<Student> subjectList = new ArrayList<>();
        List<Student> spy = spy(subjectList);
        when(mockResSet.next()).thenReturn(true).thenReturn(false);
        getSubjectFromResultSet();
        spy.add(student);
        assertEquals(1, (studentDao.getAll()).size());
    }

    @Test
    void testGetAllEntityAndReturnEmptyList() throws Exception {
        when(mockResSet.next()).thenReturn(false);
        assertEquals(0, (studentDao.getAll()).size());
    }

    @Test
    void testGetEntityByIdAndReturnEntity() throws Exception {
        mockPreState.setLong(1, 1L);
        when(mockPreState.executeQuery()).thenReturn(mockResSet);
        when(mockResSet.next()).thenReturn(true);
        getSubjectFromResultSet();
        assertEquals(student, studentDao.getById(1L));
    }

    @Test
    void testGetEntityByIdAndThrowException() throws Exception {
        mockPreState.setLong(1, 1L);
        when(mockPreState.executeQuery()).thenReturn(mockResSet);
        when(mockResSet.next()).thenReturn(false);
        assertThrows(EntityIdNotFoundException.class,() -> studentDao.getById(1L));
    }

    @Test
    void testUpdateSubject() throws Exception {
        student.setStudentCardNumber("0213456789");
        when(mockPreState.executeUpdate()).thenReturn(1);
        assertEquals(1, studentDao.update(student));
    }

    @Test
    void testDeleteSubject() throws Exception {
        when(mockPreState.executeUpdate()).thenReturn(1);
        assertEquals(1 , studentDao.delete(1L));
    }

    @Test
    void testInsertSubject() throws Exception {
        when(mockPreState.executeUpdate()).thenReturn(1);
        assertEquals(1, studentDao.insert(student));
    }

    private void getSubjectFromResultSet() throws SQLException {
        when(mockResSet.getLong("student_id")).thenReturn(student.getStudentId());
        when(mockResSet.getString("firstName")).thenReturn(student.getFirstName());
        when(mockResSet.getString("lastName")).thenReturn(student.getLastName());
        when(mockResSet.getString("student_card_number")).thenReturn(student.getStudentCardNumber());
        when(mockResSet.getString("address")).thenReturn(student.getAddress());
    }
}
