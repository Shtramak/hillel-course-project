package com.courses.tellus.autosalon.service;

import com.courses.tellus.autosalon.model.Autosalon;
import com.courses.tellus.autosalon.model.dto.AutosalonDto;
import com.courses.tellus.autosalon.repository.AutosalonRepository;
import com.courses.tellus.autosalon.service.springrest.AutosalonServiceImplRest;
import com.courses.tellus.autosalon.service.springrest.AutosalonServiceRest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AutosalonServiceMockTest {

    @Mock
    AutosalonRepository autosalonRepository;
    @Mock
    Autosalon autosalon;
    @Mock
    AutosalonDto autosalonDto;

    AutosalonServiceRest autosalonService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        autosalonService = new AutosalonServiceImplRest(autosalonRepository);
    }

    @Test
    public void testGetAllWhenResultTrue(){
        List<Autosalon> autosalonList = new ArrayList<>();
        autosalonList.add(autosalon);
        when(autosalonRepository.findAll()).thenReturn(autosalonList);
        assertThat(autosalonService.getAll(), is(autosalonList));
    }

    @Test
    public void testGetAllWhenResultFalse(){
        List<Autosalon> autosalonList = new ArrayList<>();
        when(autosalonRepository.findAll()).thenReturn(autosalonList);
        assertThat(autosalonService.getAll(), is(Collections.emptyList()));
    }

    @Test
    public void testGetByIdWhenResultTrue(){
        when(autosalonRepository.findById(anyLong())).thenReturn(Optional.of(autosalon));
        assertThat(autosalonService.getAutosalonById(1L), is(Optional.of(autosalon)));
    }

    @Test
    public void testGetByIdWhenResultFalse(){
        when(autosalonRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThat(autosalonService.getAutosalonById(1L), is(Optional.empty()));
    }

    @Test
    public void testDeleteMethod(){
        autosalonService.delete(1L);
        verify(autosalonRepository, atLeastOnce()).deleteById(anyLong());
    }

    @Test
    public void testInsertWhenResultTrue() {
        when(autosalonDto.getName()).thenReturn("BMW");
        when(autosalonDto.getAddress()).thenReturn("Germany");
        when(autosalonDto.getTelephone()).thenReturn("43322017");
        autosalonService.insert(autosalonDto);
        verify(autosalonRepository, atLeastOnce()).save(anyObject());
    }

    @Test
    public void testUpdateWhenResultTrue() {
        when(autosalonDto.getName()).thenReturn("BMW");
        when(autosalonDto.getAddress()).thenReturn("Germany");
        when(autosalonDto.getTelephone()).thenReturn("43322017");
        autosalonService.update(autosalonDto);
        verify(autosalonRepository, atLeastOnce()).save(anyObject());
    }
}
