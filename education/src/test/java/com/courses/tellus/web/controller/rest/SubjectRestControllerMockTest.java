package com.courses.tellus.web.controller.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.entity.dto.SubjectDTO;
import com.courses.tellus.entity.model.Subject;
import com.courses.tellus.service.rest.SubjectRestServiceImpl;
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

class SubjectRestControllerMockTest {

    @Mock private SubjectRestServiceImpl service;
    @Mock private SubjectDTO subjectDTO;
    @Mock private Subject subject;

    @InjectMocks SubjectRestController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindAllAndItSuccessful(){
        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject);
        given(service.getAll()).willReturn(subjects);

        assertEquals(ResponseEntity.ok(subjects), controller.getAllEntity());
    }

    @Test
    void testFindAllAndThrowException(){
        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject);
        given(service.getAll()).willThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () ->  controller.getAllEntity());
    }

    @Test
    void testFindByIdAndItSuccessful() {
        given(service.getEntityById(anyLong())).willReturn(Optional.of(subject));

        assertEquals(ResponseEntity.ok(subject), controller.getSubjectById(anyLong()));
    }

    @Test
    void testFindByIdAndThrowException() {
        given(service.getEntityById(anyLong())).willThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () ->  controller.getSubjectById(anyLong()));
    }

    @Test
    void testDeleteAndItSuccessful() {
        given(service.delete(anyLong())).willReturn(true);

        assertEquals(ResponseEntity.ok(null), controller.deleteEntity(anyLong()));
    }

    @Test
    void testDeleteAndThrowException() {
        given(service.delete(anyLong())).willThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> controller.deleteEntity(anyLong()));
    }

    @Test
    void testInsertAndItSuccessful() {
        given(service.insert(subjectDTO)).willReturn(true);

        assertEquals(ResponseEntity.ok(null), controller.insertEntity(subjectDTO));
    }

    @Test
    void testInsertAndThrowException() {
        given(service.insert(subjectDTO)).willThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> controller.insertEntity(subjectDTO));
    }
    @Test
    void testUpdateAndItSuccessful() {
        given(service.update(1L, subjectDTO)).willReturn(true);

        assertEquals(ResponseEntity.ok(null), controller.updateEntity(1L, subjectDTO));
    }

    @Test
    void testUpdateAndThrowException() {
        given(service.update(1L, subjectDTO)).willThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () ->  controller.updateEntity(1L, subjectDTO));
    }

    @Test
    void exceptionCatchMainTest() {
        assertEquals(HttpStatus.BAD_REQUEST, controller.exceptionCatchMain().getStatusCode());
    }

    @Test
    void unsupportedMediaTypeCatchTest() {
        assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE, controller.unsupportedMediaTypeCatch().getStatusCode());
    }
}
