package com.courses.tellus.persistence.dao.spring;

import com.courses.tellus.entity.model.University;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestDataSource.class, UniversityDao.class})
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                scripts = "classpath:initial/h2/table/spring/univer_test_table.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                scripts = "classpath:initial/h2/util/trunc.sql")
})

class UniversityDaoIntegrationTest {

    @Autowired
    private UniversityDao universityDao;
    private University university = new University(1L,"KPI",
            "pr.Peremogy", "technical");

    @Test
    void testGetAllWhenReturnEntityList() {
        assertEquals(1, universityDao.getAll().size());
    }

    @Test
    void testGetAllWhenReturnFalse() {
        universityDao.delete(1L);
        assertEquals(0, universityDao.getAll().size());
    }

    @Test
    void testGetByIdWhenReturnEntity() {
        assertEquals(university, universityDao.getById(1L).get());
    }

    @Test
    void testGetByIdWhenThrowException() {
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> universityDao.getById(10L));
    }

    @Test
    void testInsert() {
        assertEquals(1,universityDao.insert(university));
    }

    @Test
    void testUpdate() {
        university.setAddress("new Address");
        assertEquals(1, universityDao.update(university));
    }

    @Test
    void testDelete() {
        assertEquals(1, universityDao.delete(1L));
    }
}