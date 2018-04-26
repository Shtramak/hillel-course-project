package com.courses.tellus.service.simple;

import com.courses.tellus.persistence.dao.spring.SubjectDao;
import com.courses.tellus.persistence.dao.spring.TestDataSource;
import com.courses.tellus.entity.dto.SubjectDTO;
import com.courses.tellus.service.simple.SubjectServiceImpl;
import org.junit.jupiter.api.Assertions;
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
@ContextConfiguration(classes = {TestDataSource.class, SubjectDao.class, SubjectServiceImpl.class})
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                scripts = "classpath:initial/h2/table/spring/subject_test_table.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                scripts = "classpath:initial/h2/util/trunc.sql")
})
class SubjectServiceImplIntegrationTest {

    @Autowired
    private SubjectServiceImpl service;
    private SubjectDTO subjectDTO;

    @BeforeEach
    void setup() {
        subjectDTO = new SubjectDTO("1","Math", "Teach how calculate nums", "true",
                "2000-05-15");
    }

    @Test
    void testGetAll() {
        assertEquals(1, service.getAll().size());
    }

    @Test
    void testGetById() {
        assertTrue(service.getById(1L).isPresent());
    }

    @Test
    void testDelete() {
        assertEquals(1, service.delete(1L));
    }

    @Test
    void testInsertAndSendValidData() {
        assertEquals(1, service.insert(subjectDTO));
    }

    @Test
    void testInsertAndSendNotValidData() {
        subjectDTO.setSubjectId(" ");
        subjectDTO.setName(" ");
        subjectDTO.setDescription(" ");
        subjectDTO.setValid(" ");
        subjectDTO.setDateOfCreation(" ");
        assertEquals(0, service.insert(subjectDTO));
    }

    @Test
    void testUpdateSendValidData() {
        Assertions.assertEquals(1, service.update(subjectDTO));
    }

    @Test
    void testUpdateAndSendNotValidData() {
        subjectDTO.setSubjectId(" ");
        subjectDTO.setSubjectId(" ");
        subjectDTO.setName(" ");
        subjectDTO.setDescription(" ");
        subjectDTO.setValid(" ");
        subjectDTO.setDateOfCreation(" ");
        assertEquals(0, service.insert(subjectDTO));
    }
}
