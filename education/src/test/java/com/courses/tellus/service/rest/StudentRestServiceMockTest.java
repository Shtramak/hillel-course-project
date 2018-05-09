package com.courses.tellus.service.rest;

import com.courses.tellus.entity.dto.StudentDto;
import com.courses.tellus.entity.model.Student;
import com.courses.tellus.persistence.repository.rest.StudentRestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;

public class StudentRestServiceMockTest {

    @Mock private StudentRestRepository repository;
    @Mock private Student student;
    @Mock private StudentDto studentDto;

    @InjectMocks
    private StudentRestServiceImpl service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllTestAndReturnEntityList() {
        List<Student> students = new ArrayList<>();
        students.add(student);
        given(repository.findAll()).willReturn(students);

        assertEquals(1, service.getAll().size());
    }

    @Test
    void getAllTestAndReturnEmptyEntityList() {
        assertEquals(0, service.getAll().size());
    }

    @Test
    void getByIdTest() {
        given(repository.findById(anyLong())).willReturn(Optional.of(student));
        assertTrue(service.getEntityById(anyLong()).isPresent());
    }

    @Test
    void deleteTest() {
        assertTrue(service.delete(anyLong()));
    }

    @Test
    void deleteTestAndThrowException() {
        willThrow(IllegalArgumentException.class).given(repository).deleteById(anyLong());
        assertThrows(IllegalArgumentException.class, () -> service.delete(anyLong()));
    }

    @Test
    void insertTestWhenItSuccessful() {
        StudentDto studentDto = new StudentDto("testIn", "testIN",
                "testIN","tesIn");
        assertTrue(service.insert(studentDto));
    }

    @Test
    void insertTestWhenItGetException() {
        StudentDto studentDto = new StudentDto("","", " ", " ", " ");
        assertThrows(NumberFormatException.class, () -> service.insert(studentDto));
    }


    @Test
    void updateTestWhenItFailed() {
        StudentDto studentDto = new StudentDto("1","testUp", "testUp",
                "testUp","tesUp");

        given(repository.findById(anyLong())).willReturn(Optional.empty());

        assertFalse(service.update(1L, studentDto));
    }

    @Test
    void updateTestWhenItSuccessful() {
        StudentDto studentDto = new StudentDto("1","testUp", "testUp",
                "testUp","tesUp");
        Student student = new Student(1L, "testUp", "testUp",
               "testUp","testUp");
        given(service.getEntityById(anyLong())).willReturn(Optional.of(student));

        assertTrue(service.update(1L, studentDto));
    }

    }


