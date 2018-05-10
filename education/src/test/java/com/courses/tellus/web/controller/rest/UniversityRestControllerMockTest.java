package com.courses.tellus.web.controller.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.entity.dto.UniversityDto;
import com.courses.tellus.entity.model.University;
import com.courses.tellus.service.rest.UniversityRestServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;


public class UniversityRestControllerMockTest {

    @Mock private UniversityRestServiceImpl service;
    @Mock private UniversityDto universityDto;
    @Mock private University university;

    @InjectMocks UniversityRestController restController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindAllAndItSuccessful(){
        List<University> universities = new ArrayList<>();
        universities.add(university);
        given(service.getAll()).willReturn(universities);

        assertEquals(ResponseEntity.ok(universities), restController.getAll());
    }

    @Test
    void testFindAllAndThrowException(){
        List<University> universities = new ArrayList<>();
        universities.add(university);
        given(service.getAll()).willThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () ->  restController.getAll());
    }

    @Test
    void testFindByIdAndItSuccessful() {
        given(service.getEntityById(anyLong())).willReturn(Optional.of(university));

        assertEquals(ResponseEntity.ok(university), restController.getById(anyLong()));
    }

    @Test
    void testFindByIdAndThrowException() {
        given(service.getEntityById(anyLong())).willThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () ->  restController.getById(anyLong()));
    }

    @Test
    void testDeleteAndItSuccessful() {
            restController.delete(anyLong());
            verify(service, atLeastOnce()).delete(anyLong());
        }


    @Test
    void testInsertAndItSuccessful() {
        restController.insert(universityDto);
        verify(service, atLeastOnce()).insert(universityDto);
    }

    @Test
    void testUpdateAndItSuccessful() {
        given(service.update(1L, universityDto)).willReturn(true);

        assertEquals(ResponseEntity.ok(null), restController.update(1L, universityDto));
    }



    @Test
    void exceptionCatchMainTest() {
        assertEquals(HttpStatus.BAD_REQUEST, restController.exceptionCatchMain().getStatusCode());
    }

    @Test
    void unsupportedMediaTypeCatchTest() {
        assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE, restController.unsupportedMediaTypeCatch().getStatusCode());
    }
}