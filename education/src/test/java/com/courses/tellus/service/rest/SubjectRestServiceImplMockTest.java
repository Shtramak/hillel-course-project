package com.courses.tellus.service.rest;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.entity.dto.SubjectDTO;
import com.courses.tellus.entity.model.Subject;
import com.courses.tellus.persistence.repository.rest.SubjectRestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

class SubjectRestServiceImplMockTest {

    @Mock private SubjectRestRepository repository;
    @Mock private Subject subject;
    @Mock private SubjectDTO subjectDTO;

    @InjectMocks
    private SubjectRestServiceImpl service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllTestAndReturnEntityList() {
        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject);
        given(repository.findAll()).willReturn(subjects);

        assertEquals(1, service.getAll().size());
    }

    @Test
    void getAllTestAndReturnEmptyEntityList() {
        assertEquals(0, service.getAll().size());
    }

    @Test
    void getByIdTest() {
        given(repository.findById(anyLong())).willReturn(Optional.of(subject));
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
        SubjectDTO subjectDTO = new SubjectDTO("Math", "Leave me alone", "Y", "2000-05-15");

        assertTrue(service.insert(subjectDTO));
    }

    @Test
    void insertTestWhenItGetException() {
        SubjectDTO subjectDTO = new SubjectDTO(" ", " ", " ", " ");

        assertThrows(DateTimeParseException.class, () -> service.insert(subjectDTO));
    }

    @Test
    void updateTestWhenItSuccessful() {
        SubjectDTO subjectDTO = new SubjectDTO("1", "Math", "Leave me alone", "Y", "2000-05-15");
        Subject subject = new Subject(1L, "Math", "Leave me alone", true, LocalDate.of(2000, 5, 15));
        given(service.getEntityById(anyLong())).willReturn(Optional.of(subject));

        assertTrue(service.update(1L, subjectDTO));
    }

    @Test
    void updateTestWhenItFailed() {
        SubjectDTO subjectDTO = new SubjectDTO("1", "Math", "Leave me alone", "Y", "2000-05-15");
        given(repository.findById(anyLong())).willReturn(Optional.empty());

        assertFalse(service.update(1L, subjectDTO));
    }

    @Test
    void updateTestWhenItGetException() {
        SubjectDTO subjectDTO = new SubjectDTO(" ", " ", " ", " ", " ");
        Subject subject = new Subject(1L, "Math", "Leave me alone", true, LocalDate.of(2000, 5, 15));
        given(repository.findById(anyLong())).willReturn(Optional.of(subject));

        assertThrows(DateTimeParseException.class, () -> service.update(1L, subjectDTO));
    }
}
