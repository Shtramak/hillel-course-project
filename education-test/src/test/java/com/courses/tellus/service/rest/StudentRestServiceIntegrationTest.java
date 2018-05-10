package com.courses.tellus.service.rest;

import com.courses.tellus.config.spring.mvc.JdbcTemplateConfig;
import com.courses.tellus.config.spring.rest.RepoConfig;
import com.courses.tellus.entity.dto.StudentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {StudentRestServiceImpl.class, RepoConfig.class, JdbcTemplateConfig.class})
class StudentRestServiceIntegrationTest {

    @Autowired
    private StudentRestServiceImpl restService;
    private StudentDto studentDto;

    @BeforeEach
    void setup() {

    }

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
        StudentDto studentDto = new StudentDto("testIn", "testIN",
                "testIN","tesIn");
        assertTrue(restService.insert(studentDto));
    }

    @Test
    void testUpdateSendValidData() {
        StudentDto studentDto = new StudentDto("1","testUp", "testUp",
                "testUp","tesUp");
        assertTrue(restService.update(3L, studentDto));
    }
}

