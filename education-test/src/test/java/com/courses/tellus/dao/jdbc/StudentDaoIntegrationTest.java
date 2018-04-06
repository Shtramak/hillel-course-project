package com.courses.tellus.dao.jdbc;

import java.io.FileReader;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.connection.jdbc.ConnectionFactory;
import com.courses.tellus.entity.Student;
import com.courses.tellus.exception.jdbc.EntityIdNotFoundException;
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
                new FileReader("src/test/resources/initial/h2/table/jdbc/student_test_table.sql"));
        studentDao = new StudentDao(ConnectionFactory.getInstance());
        student = new Student(1L,"Andrey", "Petrov", "0123456789",
                "Peremohy str, 178");
        studentDao.insert(student);
    }

    @AfterEach
    void clearTable() throws Exception {
        RunScript.execute(ConnectionFactory.getInstance().getConnection(),
                new FileReader("src/test/resources/initial/h2/util/trunc.sql"));
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
        Student student = studentDao
                .getById(studentDao.getAll().get(0).getStudentId());
        Assertions.assertEquals(student, this.student);
    }

    @Test
    void testGetEntityByIdAndThrowException() throws Exception {
        Assertions.assertThrows(EntityIdNotFoundException.class, () -> studentDao.getById(12L));
    }

    @Test
    void testUpdateSubject() throws Exception {
        student.setStudentCardNumber("0321456789");
        Assertions.assertEquals(1, studentDao.update(student));
    }

    @Test
    void testDeleteSubject() throws Exception {
        Assertions.assertEquals(1, studentDao.delete(1L));
    }

    @Test
    void testInsertSubject() throws Exception {
        Assertions.assertEquals(1, studentDao.insert(student));
    }
}
