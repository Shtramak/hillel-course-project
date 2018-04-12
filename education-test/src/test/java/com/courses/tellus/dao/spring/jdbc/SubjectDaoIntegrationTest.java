package com.courses.tellus.dao.spring.jdbc;

import com.courses.tellus.dao.spring.jdbc.datasource.TestDataSource;
import com.courses.tellus.entity.Subject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestDataSource.class, SubjectDao.class})
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                scripts = "classpath:initial/h2/table/spring/subject_test_table.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                scripts = "classpath:initial/h2/util/trunc.sql")
})
class SubjectDaoIntegrationTest {

    @Autowired
    private SubjectDao subjectDao;
    private Subject subject = new Subject(1L,"Math", "Teach how calculate nums", true,
            "15/04/2000");

    @Test
    void testGetAllAndReturnEntityList() {
        Assertions.assertEquals(1, subjectDao.getAll().size());
    }

    @Test
    void testGetAllAndReturnFalse() {
        subjectDao.delete(1L);
        Assertions.assertEquals(0, subjectDao.getAll().size());
    }

    @Test
    void testGetByIdAndReturnEntity() {
        Assertions.assertEquals(subject, subjectDao.getById(1L).get());
    }

    @Test
    void testGetByIdAndThrowException() {
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> subjectDao.getById(10L));
    }

    @Test
    void testInsert() {
        Assertions.assertTrue(subjectDao.insert(subject) == 1);
    }

    @Test
    void testUpdate() {
        subject.setValid(false);
        Assertions.assertEquals(1, subjectDao.update(subject));
    }

    @Test
    void testDelete() {
        Assertions.assertEquals(1, subjectDao.delete(1L));
    }
}