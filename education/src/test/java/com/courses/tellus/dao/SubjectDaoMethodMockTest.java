package com.courses.tellus.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    private Connection mockConnection;
    private PreparedStatement mockPreState;
    private ResultSet mockResSet;
    private Date mockSqlDate;
    private Subject subject;

    @BeforeAll
    static void init() {
        connectionFactory = mock(ConnectionFactory.class);
        subjectDao = new SubjectDao(connectionFactory);
    }

    @BeforeEach
    void reinitializeRequest() throws Exception {
        mockConnection = mock(Connection.class);
        mockPreState = mock(PreparedStatement.class);
        mockResSet = mock(ResultSet.class);
        mockSqlDate = mock(Date.class);
        when(connectionFactory.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreState);
        subject = new Subject(
                1L, "Biology", "Lessons about building of humans", true,
                new GregorianCalendar(1996,5,12));
    }

    @Test
    void testGetAllObjectAndReturnObjectList() throws Exception {
        List<Subject> subjectList = new ArrayList<>();
        List<Subject> spy = spy(subjectList);
        when(mockPreState.executeQuery()).thenReturn(mockResSet);
        when(mockResSet.next()).thenReturn(true).thenReturn(false);
        when(mockResSet.getLong("id")).thenReturn(subject.getSubjectId());
        when(mockResSet.getString("name")).thenReturn(subject.getName());
        when(mockResSet.getString("descr")).thenReturn(subject.getDescription());
        when(mockResSet.getBoolean("valid")).thenReturn(subject.isValid());
        when(mockResSet.getDate("date_of_creation")).thenReturn(mockSqlDate);
        when(mockSqlDate.getTime()).thenReturn(subject.getDateOfCreation());
        when(spy.add(subject)).thenReturn(false);
        Assertions.assertEquals(spy.size(), subjectDao.getAllEntity().size());
    }

    @Test
    void testGetAllObjectAndReturnEmptyList() throws Exception {
        when(mockPreState.executeQuery()).thenReturn(mockResSet);
        when(mockResSet.next()).thenReturn(false);
        Assertions.assertEquals(0, subjectDao.getAllEntity().size());
    }

    @Test
    void testGetEntityByIdAndReturnEntity() throws Exception {
        mockPreState.setLong(1, 1L);
        when(mockPreState.executeQuery()).thenReturn(mockResSet);
        when(mockResSet.next()).thenReturn(true);
        when(mockResSet.getLong(anyString())).thenReturn(subject.getSubjectId());
        when(mockResSet.getString("name")).thenReturn(subject.getName());
        when(mockResSet.getString("descr")).thenReturn(subject.getDescription());
        when(mockResSet.getBoolean(anyString())).thenReturn(subject.isValid());
        when(mockResSet.getDate("date_of_creation")).thenReturn(mockSqlDate);
        when(mockSqlDate.getTime()).thenReturn(subject.getDateOfCreation());
        Assertions.assertEquals(subject, subjectDao.getEntityById(1L));
    }

    @Test
    void testGetEntityByIdAndReturnNull() throws Exception {
        mockPreState.setLong(1, 1L);
        when(mockPreState.executeQuery()).thenReturn(mockResSet);
        when(mockResSet.next()).thenReturn(false);
        Assertions.assertNull(subjectDao.getEntityById(1L));
    }

    @Test
    void testUpdateSubject() throws Exception {
        Subject subject = new Subject(
                1L, "Math", "Teach how to calculate numbers", true,
                new GregorianCalendar(1996,5,12));
        when(mockPreState.executeUpdate()).thenReturn(0);
        boolean result = subjectDao.update(subject);
        Assertions.assertTrue(result);
    }

    @Test
    void testDeleteSubject() throws Exception {
        when(mockPreState.executeUpdate()).thenReturn(0);
        boolean result = subjectDao.delete(1L);
        Assertions.assertTrue(result);
    }

    @Test
    void testInsertSubject() throws Exception {
        Subject subject = new Subject(
                2L, "Math", "Teach how to calculate numbers", true,
                new GregorianCalendar(1996,5,12));
        when(mockPreState.executeUpdate()).thenReturn(0);
        boolean result = subjectDao.insert(subject);
        Assertions.assertTrue(result);
    }
}
