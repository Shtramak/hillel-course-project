package com.courses.tellus.web.controller.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.entity.dto.StudentDto;
import com.courses.tellus.entity.model.Student;
import com.courses.tellus.service.rest.StudentRestServiceImpl;
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


public class StudentRestControllerMockTest {

    @Mock private StudentRestServiceImpl service;
    @Mock private StudentDto studentDto;
    @Mock private Student student;

    @InjectMocks StudentRestController restController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindAllAndItSuccessful(){
        List<Student> students = new ArrayList<>();
        students.add(student);
        given(service.getAll()).willReturn(students);

        assertEquals(ResponseEntity.ok(students), restController.getAll());
    }

    @Test
    void testFindAllAndThrowException(){
        List<Student> students = new ArrayList<>();
        students.add(student);
        given(service.getAll()).willThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () ->  restController.getAll());
    }

    @Test
    void testFindByIdAndItSuccessful() {
        given(service.getEntityById(anyLong())).willReturn(Optional.of(student));

        assertEquals(ResponseEntity.ok(student), restController.getById(anyLong()));
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
        restController.insert(studentDto);
        verify(service, atLeastOnce()).insert(studentDto);
    }

    @Test
    void testUpdateAndItSuccessful() {
        given(service.update(1L, studentDto)).willReturn(true);

        assertEquals(ResponseEntity.ok(null), restController.update(1L, studentDto));
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