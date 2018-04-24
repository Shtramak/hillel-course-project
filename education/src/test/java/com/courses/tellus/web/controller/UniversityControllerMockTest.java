package com.courses.tellus.web.controller;


import com.courses.tellus.entity.model.University;
import com.courses.tellus.service.UniversityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

public class UniversityControllerMockTest {

    @Mock
    private University university;

    @Mock
    private Model model;

    @Mock
    private UniversityService universityServiceImpl;

    @InjectMocks
    private UniversityController universityController;

    @BeforeEach
    public void setUp() {
        universityController = new UniversityController();
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testGetAllUniversities() {
        List<University> universities = new ArrayList<>();
        universities.add(university);
        when(universityServiceImpl.getAll()).thenReturn(universities);
        assertEquals("listOfUniversities", universityController.getAllUniversities(model));
    }

    @Test
    public void  testAddUniversityMethodPost() {
        when(universityServiceImpl.insert(anyObject())).thenReturn(1);
        assertEquals("redirect:/springmvc/university/list", universityController.addUniversity(university));
    }

    @Test
    public void  testAddUniversityMethodGet(){
        assertEquals("addUniversity", universityController.addUniversity());
    }

    @Test
    public void testUpdateUniversityMethodPost() {
        when(universityServiceImpl.update(anyObject())).thenReturn(1);
        assertEquals("redirect:/springmvc/university/list", universityController.updateUniversity(university));
    }

    @Test
    public void testUpdateAutoMethodGet() {
        when(universityServiceImpl.getById(anyLong())).thenReturn(Optional.of(university));
        assertEquals("updateUniversity", universityController.updateUniversity(anyLong(), model));
    }

    @Test
    public void testDeleteUniversity() {
        when(universityServiceImpl.delete(anyLong())).thenReturn(1);
        assertEquals("redirect:/springmvc/university/list", universityController.deleteUniversity(anyLong()));
    }

}

