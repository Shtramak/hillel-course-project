package com.courses.tellus.dao;

import java.io.FileReader;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.dbconnection.ConnectionFactory;
import com.courses.tellus.entity.Student;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentDaoIntegrationTest {

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
        Optional<List<Student>> subjectList = studentDao.getAll();
        Assertions.assertTrue(subjectList.isPresent());
    }

    @Test
    void testGetAllEntityAndReturnEmptyList() throws Exception {
        studentDao.delete(studentDao.getAll().get().get(0).getStudentId());
        Optional<List<Student>> subjectList = studentDao.getAll();
        Assertions.assertEquals(0, subjectList.get().size());
    }

    @Test
    void testGetEntityByIdAndReturnEntity() throws Exception {
        Optional<Student> student = studentDao
                .getById(studentDao.getAll().get().get(0).getStudentId());
        Assertions.assertTrue(student.isPresent());
    }

    @Test
    void testGetEntityByIdAndReturnFalse() throws Exception {
        Optional<Student> student = studentDao.getById(12L);
        Assertions.assertFalse(student.isPresent());
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
