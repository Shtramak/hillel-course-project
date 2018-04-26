package com.courses.tellus.autosalon.controller;


import com.courses.tellus.autosalon.model.Auto;
import com.courses.tellus.autosalon.service.AutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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


public class AutoControllerMockTest {

    @Mock
    Auto auto;

    @Mock
    Model model;

    @Mock
    AutoService autoService;

    AutoController autoController;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        autoController = new AutoController(autoService);
    }

    @Test
    public void testListAuto(){
        List<Auto> autoList = new ArrayList<>();
        autoList.add(auto);
        when(autoService.getAll()).thenReturn(autoList);
        assertEquals("listAuto", autoController.getAllAuto(model));
    }

    @Test
    public void  testCreateAutoMethodPost(){
        when(autoService.insert(anyObject())).thenReturn(1);
        assertEquals("redirect:list", autoController.createAuto(auto));
    }

    @Test
    public void  testCreateAutoMethodGet(){
        assertEquals("createAuto", autoController.createAutoPage());
    }

    @Test
    public void testGetAutoById(){
        when(autoService.getAutoById(anyLong())).thenReturn(Optional.of(auto));
        assertEquals("showAuto", autoController.getAutoById(1L, model));
    }

    @Test
    public void testUpdateAutoMethodPost(){
        when(autoService.update(anyObject())).thenReturn(1);
        assertEquals("redirect:list", autoController.updateAuto(auto));
    }

    @Test
    public void testUpdateAutoMethodGet(){
        when(autoService.getAutoById(anyLong())).thenReturn(Optional.of(auto));
        assertEquals("editAuto", autoController.updateAutoById(1L, model));
    }

    @Test
    public void testDelete(){
        when(autoService.delete(anyLong())).thenReturn(1);
        assertEquals("redirect:list", autoController.deleteAutoByid(1L));
    }
}
