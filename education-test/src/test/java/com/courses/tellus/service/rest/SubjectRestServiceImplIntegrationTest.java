package com.courses.tellus.service.rest;

import com.courses.tellus.config.spring.mvc.JdbcTemplateConfig;
import com.courses.tellus.config.spring.rest.RepoConfig;
import com.courses.tellus.entity.dto.SubjectDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SubjectRestServiceImpl.class, RepoConfig.class, JdbcTemplateConfig.class})
class SubjectRestServiceImplIntegrationTest {

    @Autowired
    private SubjectRestServiceImpl restService;
    private SubjectDTO subjectDTO;

    @Test
    void testGetAll() {
        assertFalse(restService.getAll().isEmpty());
    }

    @Test
    void testGetById() {
        assertTrue(restService.getEntityById(4L).isPresent());
    }

    @Test
    void testDeleteAndThrowException() {
        assertThrows(EmptyResultDataAccessException.class,
                () -> restService.delete(15L));
    }

    @Test
    void testInsertAndSendValidData() {
        SubjectDTO subjectDTO = new SubjectDTO("Math", "Teach how calculate nums", "true",
                "2000-05-15");
        assertTrue(restService.insert(subjectDTO));
    }

    @Test
    void testUpdateSendValidData() {
        SubjectDTO subjectDTO = new SubjectDTO("1", "Math", "Teach how calculate nums", "true",
                "2000-05-15");
        assertTrue(restService.update(3L, subjectDTO));
    }
}
