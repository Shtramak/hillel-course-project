package com.courses.tellus.persistence.dao.jdbc;

import java.io.FileReader;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.config.jdbc.ConnectionFactory;
import com.courses.tellus.entity.model.Student;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class StudentDaoIntegrationTest {

    private static StudentDao studentDao;
    private Student student;

    @BeforeEach
    void initializeSubject() throws Exception {
        RunScript.execute(ConnectionFactory.getInstance().getConnection(),
                new FileReader("src/test/resources/initial/h2/table/jdbc/student_test_table.sql"));
        studentDao = new StudentDao(ConnectionFactory.getInstance());
        student = new Student(1L,"Andrey", "Petrov", "0123456789",
                "Peremohy str, 178");
        studentDao.insert(student);
    }

    @Test
    void testGetAllAndReturnEntityList() throws Exception {
        List<Student> subjectList = studentDao.getAll();
        Assertions.assertEquals(1, subjectList.size());
    }

    @Test
    void testGetAllEntityAndReturnEmptyList() throws Exception {
        studentDao.delete(studentDao.getAll().get(0).getStudentId());
        List<Student> subjectList = studentDao.getAll();
        Assertions.assertEquals(0, subjectList.size());
    }

    @Test
    void testGetEntityByIdAndReturnEntity() throws Exception {
        Optional<Student> student = studentDao.getById(studentDao.getAll().get(0).getStudentId());
        Assertions.assertTrue(student.isPresent());
    }

    @Test
    void testGetEntityByIdAndReturnFalse() throws Exception {
        Optional<Student> student = studentDao.getById(12L);
        assertFalse(student.isPresent());
    }

    @Test
    void testUpdateStudent() throws Exception {
        student.setStudentCardNumber("0321456789");
        Assertions.assertEquals(1, studentDao.update(student));
    }

    @Test
    void testDeleteStudent() throws Exception {
        Assertions.assertEquals(1, studentDao.delete(1L));
    }

    @Test
    void testInsertStudent() throws Exception {
        Assertions.assertEquals(1, studentDao.insert(student));
    }
}
