package com.courses.tellus.dao;

import java.io.FileReader;
import java.util.List;

import com.courses.tellus.dbconnection.ConnectionFactory;
import com.courses.tellus.entity.Student;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentDaoMethodIntegrationTest {

    private static StudentDao studentDao;
    private Student student;

    @BeforeEach
    void initializeSubject() throws Exception {
        RunScript.execute(ConnectionFactory.getInstance().getConnection(),
                new FileReader("src/test/resources/student_test_table.sql"));
        studentDao = new StudentDao(ConnectionFactory.getInstance());
        student = new Student(1L,"Andrey", "Petrov", "0123456789",
                "Peremohy str, 178");
        studentDao.insert(student);
    }

    @AfterEach
    void clearTable() throws Exception {
        RunScript.execute(ConnectionFactory.getInstance().getConnection(),
                new FileReader("src/test/resources/trunc.sql"));
    }

    @Test
    void testGetAllAndReturnEntityList() throws Exception {
        List<Student> subjectList = studentDao.getAll();
        Assertions.assertEquals(1, subjectList.size());
    }

    @Test
    void testGetAllObjectAndReturnEmptyList() throws Exception {
        studentDao.delete(studentDao.getAll().get(0).getStudentId());
        List<Student> subjectList = studentDao.getAll();
        Assertions.assertEquals(0, subjectList.size());
    }

    @Test
    void testGetEntityByIdAndReturnEntity() throws Exception {
        Student student = studentDao.getById(studentDao.getAll().get(0).getStudentId());
        Assertions.assertNotNull(student);
    }

    @Test
    void testGetEntityByIdAndReturnNull() throws Exception {
        Student student = studentDao.getById(12L);
        Assertions.assertNull(student);
    }

    @Test
    void testUpdateSubject() throws Exception {
        student.setStudentCardNumber("0321456789");
        Assertions.assertEquals(1, studentDao.update(student));
    }

    @Test
    void testDeleteSubject() {
        Assertions.assertEquals(1, studentDao.delete(1L));
    }

    @Test
    void testInsertSubject() {
        Assertions.assertEquals(1, studentDao.insert(student));
    }
}
