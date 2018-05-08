package com.courses.tellus.service.rest;

import com.courses.tellus.config.spring.rest.RepoConfig;
import com.courses.tellus.entity.dto.UniversityDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UniversityRestServiceImpl.class, RepoConfig.class})
class UniversityRestServiceIntegrationTest {

    @Autowired
    private UniversityRestServiceImpl restService;
    private UniversityDto universityDto;

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
        UniversityDto universityDto = new UniversityDto("testN", "testA", "testS");
        assertTrue(restService.insert(universityDto));
    }

    @Test
    void testUpdateSendValidData() {
        UniversityDto universityDto = new UniversityDto("1","testN", "testA", "testS");
        assertTrue(restService.update(3L, universityDto));
    }
}

