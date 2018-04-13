package com.courses.tellus.springMVC;


import com.courses.tellus.controller.UniversityController;
import com.courses.tellus.entity.University;
import com.courses.tellus.service.UniversityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.lang.reflect.Field;
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

    private UniversityController universityController;

    private Field field;



    @BeforeEach
    public void setUp() throws NoSuchFieldException {
        universityController = new UniversityController();
        field = universityController.getClass().getDeclaredField("universityServiceImpl");
        field.setAccessible(true);
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testGetAllUniversities() throws IllegalAccessException {
        field.set(universityController , universityServiceImpl);
        List<University> universities = new ArrayList<>();
        universities.add(university);
        when(universityServiceImpl.getAll()).thenReturn(universities);
        assertEquals("listOfUniversities", universityController.getAllUniversities(model));
    }

    @Test
    public void  testAddUniversityMethodPost() throws IllegalAccessException{
        field.set(universityController , universityServiceImpl);
        when(universityServiceImpl.insert(anyObject())).thenReturn(1);
        assertEquals("redirect:/getAllUniversities", universityController.addUniversity(university));
    }

    @Test
    public void  testAddUniversityMethodGet(){
        assertEquals("addUniversity", universityController.addUniversity());
    }

    @Test
    public void testUpdateUniversityMethodPost() throws IllegalAccessException{
        field.set(universityController , universityServiceImpl);
        when(universityServiceImpl.update(anyObject())).thenReturn(1);
        assertEquals("redirect:/getAllUniversities", universityController.updateUniversity(university));
    }

    @Test
    public void testUpdateAutoMethodGet() throws IllegalAccessException{
        field.set(universityController , universityServiceImpl);
        when(universityServiceImpl.getById(anyLong())).thenReturn(Optional.of(university));
        assertEquals("updateUniversity", universityController.updateUniversity(anyLong(), model));
    }

    @Test
    public void testDeleteUniversity() throws IllegalAccessException{
        field.set(universityController , universityServiceImpl);
        when(universityServiceImpl.delete(anyLong())).thenReturn(1);
        assertEquals("redirect:/getAllUniversities", universityController.deleteUniversity(anyLong()));
    }

    @Test
    public void testIndex() throws IllegalAccessException{
        assertEquals("index", universityController.index());
    }
}

