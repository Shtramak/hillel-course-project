package com.courses.tellus.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import com.courses.tellus.dbconnection.ConnectionFactory;
import com.courses.tellus.entity.Subject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class SubjectDaoMethodMockTest {

    private static ConnectionFactory connectionFactory;
    private static SubjectDao subjectDao;
    private ResultSet mockResSet;
    private Subject subject;

    @BeforeAll
    static void init() {
        connectionFactory = mock(ConnectionFactory.class);
        subjectDao = new SubjectDao(connectionFactory);
    }

    @BeforeEach
    void reinitializeRequest() throws Exception {
        ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreState = mock(PreparedStatement.class);
        mockResSet = mock(ResultSet.class);
        when(connectionFactory.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreState);
        subject = new Subject(
                1L, "Biology", "Lessons about building of humans", true,
                new GregorianCalendar(1996,5,12));
    }

    @Test
    void testGetAllAndReturnObjectList() throws Exception {
        List<Subject> subjectList = new ArrayList<>();
        List<Subject> spy = spy(subjectList);
        when(mockPreState.executeQuery()).thenReturn(mockResSet);
        when(mockResSet.next()).thenReturn(true).thenReturn(false);
        getSubjectFromResultSet();
        spy.add(subject);
        Assertions.assertEquals(spy.size(), subjectDao.getAll().size());
    }

    @Test
    void testGetAllObjectAndReturnEmptyList() throws Exception {
        when(mockPreState.executeQuery()).thenReturn(mockResSet);
        when(mockResSet.next()).thenReturn(false);
        Assertions.assertEquals(0, subjectDao.getAll().size());
    }

    @Test
    void testGetEntityByIdAndReturnEntity() throws Exception {
        mockPreState.setLong(1, 1L);
        when(mockPreState.executeQuery()).thenReturn(mockResSet);
        when(mockResSet.next()).thenReturn(true);
        getSubjectFromResultSet();
        Assertions.assertEquals(subject, subjectDao.getById(1L));
    }

    @Test
    void testGetEntityByIdAndReturnNull() throws Exception {
        mockPreState.setLong(1, 1L);
        when(mockPreState.executeQuery()).thenReturn(mockResSet);
        when(mockResSet.next()).thenReturn(false);
        Assertions.assertNull(subjectDao.getById(1L));
    }

    @Test
    void testUpdateSubject() throws Exception {
        subject.setValid(false);
        when(mockPreState.executeUpdate()).thenReturn(0);
        Assertions.assertEquals(1, subjectDao.update(subject));
    }

    @Test
    void testDeleteSubject() throws Exception {
        when(mockPreState.executeUpdate()).thenReturn(0);
        Assertions.assertEquals(1 ,subjectDao.delete(1L));
    }

    @Test
    void testInsertSubject() throws Exception {
        Subject subject = new Subject(
                2L, "Math", "Teach how to calculate numbers", true,
                new GregorianCalendar(1996,5,12));
        when(mockPreState.executeUpdate()).thenReturn(0);
        Assertions.assertEquals(1, subjectDao.insert(subject));
    }

    private void getSubjectFromResultSet() throws SQLException {
        Date mockSqlDate = mock(Date.class);
        when(mockResSet.getLong("id")).thenReturn(subject.getSubjectId());
        when(mockResSet.getLong("id")).thenReturn(subject.getSubjectId());
        when(mockResSet.getString("name")).thenReturn(subject.getName());
        when(mockResSet.getString("descr")).thenReturn(subject.getDescription());
        when(mockResSet.getBoolean("valid")).thenReturn(subject.isValid());
        when(mockResSet.getDate("date_of_creation")).thenReturn(mockSqlDate);
        when(mockSqlDate.getTime()).thenReturn(subject.getDateOfCreation());
    }
}
