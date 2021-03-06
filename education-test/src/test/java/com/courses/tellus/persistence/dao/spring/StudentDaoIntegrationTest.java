package com.courses.tellus.persistence.dao.spring;

import com.courses.tellus.entity.model.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestDataSource.class, StudentDao.class})
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                scripts = "classpath:initial/h2/table/spring/student_test_table.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                scripts = "classpath:initial/h2/util/trunc.sql")
})
class StudentDaoIntegrationTest {

    @Autowired
    private StudentDao studentDao;
    private Student student = new Student(1L,"Givi", "Trump", "37773",
            "Street 23");

    @Test
    void testGetAllWhenReturnEntityList() {
        assertEquals(1, studentDao.getAll().size());
    }

    @Test
    void testGetAllWhenReturnFalse() {
        studentDao.delete(1L);
        assertEquals(0, studentDao.getAll().size());
    }

    @Test
    void testGetByIdWhenReturnEntity() {
        assertEquals(student, studentDao.getById(1L).get());
    }

    @Test
    void testGetByIdWhenThrowException() {
        assertThrows(EmptyResultDataAccessException.class, () -> studentDao.getById(10L));
    }

    @Test
    void testInsert() {
        assertEquals(1, studentDao.insert(student));
    }

    @Test
    void testUpdate() {
        student.setAddress("new Address");
        assertEquals(1, studentDao.update(student));
    }

    @Test
    void testDelete() {
        assertEquals(1, studentDao.delete(1L));
    }
}
