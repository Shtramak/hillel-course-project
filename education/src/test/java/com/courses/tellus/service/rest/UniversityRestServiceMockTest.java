package com.courses.tellus.service.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.entity.dto.UniversityDto;
import com.courses.tellus.entity.model.University;
import com.courses.tellus.persistence.repository.rest.UniversityRestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Matchers.anyLong;

public class UniversityRestServiceMockTest {

    @Mock private UniversityRestRepository repository;
    @Mock private University university;
    @Mock private UniversityDto universityDto;

    @InjectMocks
    private UniversityRestServiceImpl service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllTestAndReturnEntityList() {
        List<University> universities = new ArrayList<>();
        universities.add(university);
        given(repository.findAll()).willReturn(universities);

        assertEquals(1, service.getAll().size());
    }

    @Test
    void getAllTestAndReturnEmptyEntityList() {
        assertEquals(0, service.getAll().size());
    }

    @Test
    void getByIdTest() {
        given(repository.findById(anyLong())).willReturn(Optional.of(university));
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
        UniversityDto universityDto = new UniversityDto("testN", "testA", "testS");
        assertTrue(service.insert(universityDto));
    }

    @Test
    void insertTestWhenItGetException() {
        UniversityDto universityDto = new UniversityDto("", " ", " ", " ");
        assertThrows(NumberFormatException.class, () -> service.insert(universityDto));
    }


    @Test
    void updateTestWhenItFailed() {
        UniversityDto universityDto = new UniversityDto("1", "testN", "testA", "testS");

        given(repository.findById(anyLong())).willReturn(Optional.empty());

        assertFalse(service.update(1L, universityDto));
    }

    @Test
    void updateTestWhenItSuccessful() {
        UniversityDto universityDto = new UniversityDto("1", "testN", "testA", "testS");
       University university = new University(1L, "testN", "testA","testS");
        given(service.getEntityById(anyLong())).willReturn(Optional.of(university));

        assertTrue(service.update(1L, universityDto));
    }

    }


