package com.courses.tellus.dao;

import java.io.FileReader;
import java.util.GregorianCalendar;
import java.util.List;

import com.courses.tellus.dbconnection.ConnectionFactory;
import com.courses.tellus.entity.Subject;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.*;

public class SubjectDaoExceptionIntegrationTest {

    private static ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
    private static SubjectDao subjectDao;

    @BeforeAll
    static void init() throws Exception {
        RunScript.execute(ConnectionFactory.getInstance().getConnection(),
                new FileReader("src/test/resources/trunc.sql"));
        subjectDao = new SubjectDao(connectionFactory);
        
    }

    @Test
    public void testGetAllObjectAndThrowNull() {
        List<Subject> subjectList = subjectDao.getAll();
        Assertions.assertNull(subjectList);
    }

    @Test
    public void testGetEntityByIdAndThrowNull() {
        Subject subject = subjectDao.getById(1L);
        Assertions.assertNull(subject);
    }

    @Test
    public void testUpdateSubject() {
        Subject subject = new Subject(
                2L, "Math", "Teach how to calculate numbers", true,
                new GregorianCalendar(1996,5,12));
        Assertions.assertEquals(0, subjectDao.update(subject));
    }

    @Test
    public void testDeleteSubject() {
        Assertions.assertEquals(0, subjectDao.delete(1L));
    }

    @Test
    public void testInsertSubject() {
        Subject subject = new Subject(
                1L, "Math", "Teach how to calculate numbers", true,
                new GregorianCalendar(1996,5,12));
        Assertions.assertEquals(0, subjectDao.insert(subject));
    }

}
