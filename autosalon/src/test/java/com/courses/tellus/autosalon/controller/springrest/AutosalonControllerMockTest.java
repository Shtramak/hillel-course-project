package com.courses.tellus.autosalon.controller.springrest;

import com.courses.tellus.autosalon.model.Autosalon;
import com.courses.tellus.autosalon.model.dto.AutosalonDto;
import com.courses.tellus.autosalon.service.springrest.AutosalonServiceImplRest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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

public class AutosalonControllerMockTest {
    @Mock
    Autosalon autosalon;
    @Mock
    AutosalonDto autosalonDto;
    @Mock
    AutosalonServiceImplRest autosalonServiceImplRest;

    AutosalonControllerRests autosalonControllerRests;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        autosalonControllerRests = new AutosalonControllerRests(autosalonServiceImplRest);
    }

    @Test
    public void testListAutosalonWhenResultTrue(){
        List<Autosalon> autosalonList = new ArrayList<>();
        autosalonList.add(autosalon);
        when(autosalonServiceImplRest.getAll()).thenReturn(autosalonList);
        assertEquals(ResponseEntity.ok(autosalonList), autosalonControllerRests.findAllAutoSalon());
    }

    @Test
    public void testListAutosalonWhenResultFalse(){
        when(autosalonServiceImplRest.getAll()).thenThrow(IllegalArgumentException.class);
        assertEquals(ResponseEntity.badRequest().body(null), autosalonControllerRests.findAllAutoSalon());
    }

    @Test
    public void testGetAutosalonByIdWhenResultTrue(){
        when(autosalonServiceImplRest.getAutosalonById(anyLong())).thenReturn(Optional.of(autosalon));
        assertEquals(ResponseEntity.ok(Optional.of(autosalon)), autosalonControllerRests.getAutosalonById(1L));
    }

    @Test
    public void testGetAutosalonByIdWhenResultFalse(){
        when(autosalonServiceImplRest.getAutosalonById(anyLong())).thenThrow(IllegalArgumentException.class);
        assertEquals(ResponseEntity.badRequest().body(null), autosalonControllerRests.getAutosalonById(1L));
    }

    @Test
    public void testDeleteWhenResultTrue(){
        autosalonControllerRests.deleteAutosalonById(1L);
        verify(autosalonServiceImplRest).delete(anyLong());
        assertEquals(ResponseEntity.ok("Autosalon successfully deleted"), autosalonControllerRests.deleteAutosalonById(1L));
    }

    @Test
    public void testDeleteWhenResultFalse(){
        doThrow(IllegalArgumentException.class).when(autosalonServiceImplRest).delete(anyLong());
        assertEquals(ResponseEntity.badRequest().body(null), autosalonControllerRests.deleteAutosalonById(1L));
    }

    @Test
    public void testInsertWhenResultTrue(){
        when(autosalonServiceImplRest.insert(anyObject())).thenReturn(autosalon);
        assertEquals(ResponseEntity.ok(autosalon), autosalonControllerRests.insertAutosalon(autosalonDto));
    }

    @Test
    public void testInsertWhenResultFalse(){
        when(autosalonServiceImplRest.insert(anyObject())).thenThrow(IllegalArgumentException.class);
        assertEquals(ResponseEntity.badRequest().body(null), autosalonControllerRests.insertAutosalon(autosalonDto));
    }

    @Test
    public void testUpdateWhenResultTrue(){
        when(autosalonServiceImplRest.update(anyObject())).thenReturn(autosalon);
        assertEquals(ResponseEntity.ok(autosalon), autosalonControllerRests.updateAutosalon(autosalonDto));
    }

    @Test
    public void testUpdateWhenResultFalse(){
        when(autosalonServiceImplRest.update(anyObject())).thenThrow(IllegalArgumentException.class);
        assertEquals(ResponseEntity.badRequest().body(null), autosalonControllerRests.updateAutosalon(autosalonDto));
    }
}
