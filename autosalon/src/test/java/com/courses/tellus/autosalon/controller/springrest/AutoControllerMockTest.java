package com.courses.tellus.autosalon.controller.springrest;

import com.courses.tellus.autosalon.controller.AutoController;
import com.courses.tellus.autosalon.model.Auto;
import com.courses.tellus.autosalon.model.dto.AutoDto;
import com.courses.tellus.autosalon.service.AutoServiceImpl;
import com.courses.tellus.autosalon.service.springrest.AutoServiceImplRest;
import com.courses.tellus.autosalon.service.springrest.AutoServiceRest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AutoControllerMockTest {

    @Mock
    Auto auto;
    @Mock
    AutoDto autoDto;
    @Mock
    AutoServiceImplRest autoServiceImplRest;
    @Mock
    AutoControllerRest autoControllerRest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        autoControllerRest = new AutoControllerRest(autoServiceImplRest);
    }

    @Test
    public void testListAutoWhenResultTrue(){
        List<Auto> autoList = new ArrayList<>();
        autoList.add(auto);
        when(autoServiceImplRest.getAll()).thenReturn(autoList);
        assertEquals(ResponseEntity.ok(autoList), autoControllerRest.findAllAuto());
    }

    @Test
    public void testListAutoWhenResultFalse(){
        when(autoServiceImplRest.getAll()).thenThrow(IllegalArgumentException.class);
        assertEquals(ResponseEntity.badRequest().body(null), autoControllerRest.findAllAuto());
    }

    @Test
    public void testGetAutoByIdWhenResultTrue(){
        when(autoServiceImplRest.getAutoById(anyLong())).thenReturn(Optional.of(auto));
        assertEquals(ResponseEntity.ok(Optional.of(auto)), autoControllerRest.getAutoById(1L));
    }

    @Test
    public void testGetAutoByIdWhenResultFalse(){
        when(autoServiceImplRest.getAutoById(anyLong())).thenThrow(IllegalArgumentException.class);
        assertEquals(ResponseEntity.badRequest().body(null), autoControllerRest.getAutoById(1L));
    }

    @Test
    public void testDeleteWhenResultTrue(){
        autoControllerRest.deleteAutoById(1L);
        verify(autoServiceImplRest).delete(anyLong());
        assertEquals(ResponseEntity.ok("Auto successfully deleted"), autoControllerRest.deleteAutoById(1L));
    }

    @Test
    public void testDeleteWhenResultFalse(){
        doThrow(IllegalArgumentException.class).when(autoServiceImplRest).delete(anyLong());
        assertEquals(ResponseEntity.badRequest().body(null), autoControllerRest.deleteAutoById(1L));
    }

    @Test
    public void testInsertWhenResultTrue(){
        when(autoServiceImplRest.insert(anyObject())).thenReturn(auto);
        assertEquals(ResponseEntity.ok(auto), autoControllerRest.insertAuto(autoDto));
    }

    @Test
    public void testInsertWhenResultFalse(){
        when(autoServiceImplRest.insert(anyObject())).thenThrow(IllegalArgumentException.class);
        assertEquals(ResponseEntity.badRequest().body(null), autoControllerRest.insertAuto(autoDto));
    }

    @Test
    public void testUpdateWhenResultTrue(){
        when(autoServiceImplRest.update(anyObject())).thenReturn(auto);
        assertEquals(ResponseEntity.ok(auto), autoControllerRest.updateAuto(autoDto));
    }

    @Test
    public void testUpdateWhenResultFalse(){
        when(autoServiceImplRest.update(anyObject())).thenThrow(IllegalArgumentException.class);
        assertEquals(ResponseEntity.badRequest().body(null), autoControllerRest.updateAuto(autoDto));
    }
}
