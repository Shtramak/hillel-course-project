package com.courses.tellus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import com.courses.tellus.dbconnection.ConnectionFactory;

import com.courses.tellus.entity.Subject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SubjectDaoExceptionMockTest {

    private static ConnectionFactory connectionFactory;
    private Connection mockConnection;
    private PreparedStatement mockStatement;
    private static SubjectDao subjectDao;

    @BeforeAll
    public static void init() {
        connectionFactory = Mockito.mock(ConnectionFactory.class);
        subjectDao = new SubjectDao(connectionFactory);
    }

    @BeforeEach
    public void reInitDepartmentDao() throws SQLException {
        mockConnection = Mockito.mock(Connection.class);
        mockStatement = Mockito.mock(PreparedStatement.class);
        Mockito.when(connectionFactory.getConnection()).thenReturn(mockConnection);
    }

    @Test
    public void testCRUDExceptionThrows() {
        try {
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockStatement);
            Mockito.when(mockStatement.executeQuery()).thenThrow(new SQLException());
            Mockito.when(mockStatement.executeUpdate()).thenThrow(new SQLException());
            subjectDao.getAllEntity();
            subjectDao.getEntityById(1);
            subjectDao.delete(1);
            subjectDao.insert(new Subject(
                    3, "Math", "Teach how to calculate numbers", true,
                    new GregorianCalendar(1996,5,12)));
            subjectDao.update(new Subject(
                    4, "Math", "Teach how to calculate numbers", true,
                    new GregorianCalendar(1996,5,12)));
        } catch (Exception except) {
            Assertions.assertTrue(except instanceof SQLException);
        }
    }
}
