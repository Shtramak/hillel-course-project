package com.courses.tellus.service;

import com.courses.tellus.dao.spring.TestDataSource;
import com.courses.tellus.dao.spring.UniversityDao;
import com.courses.tellus.model.University;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestDataSource.class, UniversityDao.class, UniversityServiceImpl.class})
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                scripts = "classpath:initial/h2/table/spring/univer_test_table.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                scripts = "classpath:initial/h2/util/trunc.sql")
})
class UniversityServiceImplTest {

    @Autowired
    private UniversityServiceImpl service;
    private University university;

    @BeforeEach
    void setup() {
        university = new University(1L,"nameTest", "addressTest",
                "specializationTest");
    }

    @Test
    void testServiceImplGetAll() {
        assertEquals(1, service.getAll().size());

    }

    @Test
    void testServiceImplGetById() {
        assertTrue(service.getById(1L).isPresent());
    }

    @Test
    void testServiceImplDelete() {
        assertEquals(1, service.delete(1L));
    }

    @Test
    void testServiceImplInsert() {
        assertEquals(1, service.insert(university));
    }


    @Test
    void testServiceImplUpdate() {
        assertEquals(1, service.update(university));
    }

}