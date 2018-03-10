package com.courses.tellus.dao;

import java.io.FileReader;
import java.util.GregorianCalendar;
import java.util.List;

import com.courses.tellus.dbconnection.ConnectionFactory;
import com.courses.tellus.entity.Student;
import com.courses.tellus.entity.Subject;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class StudentDaoExceptionIntegrationTest {

    private static ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
    private static StudentDao studentDao;

    @BeforeAll
    static void init() throws Exception {
        RunScript.execute(ConnectionFactory.getInstance().getConnection(),
                new FileReader("src/test/resources/trunc.sql"));
        studentDao = new StudentDao(connectionFactory);
    }

    @Test
    public void testGetAllObjectAndThrowNull() {
        List<Student> subjectList = studentDao.getAll();
        Assertions.assertNull(subjectList);
    }

    @Test
    public void testGetEntityByIdAndThrowNull() {
        Student student = studentDao.getById(1L);
        Assertions.assertNull(student);
    }

    @Test
    public void testUpdateSubject() {
        Student student = new Student(1L,"Andrey", "Petrov", "0123456789",
                "Peremohy str, 178");
        Assertions.assertEquals(0, studentDao.update(student));
    }

    @Test
    public void testDeleteSubject() {
        Assertions.assertEquals(0, studentDao.delete(1L));
    }

    @Test
    public void testInsertSubject() {
        Student student = new Student(1L,"Andrey", "Petrov", "0123456789",
                "Peremohy str, 178");
        Assertions.assertEquals(0, studentDao.insert(student));
    }

}
