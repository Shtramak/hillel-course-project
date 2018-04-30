package com.courses.tellus.autosalon.service;

import com.courses.tellus.autosalon.model.Auto;
import com.courses.tellus.autosalon.model.dto.AutoDto;
import com.courses.tellus.autosalon.repository.AutoRepository;
import com.courses.tellus.autosalon.service.springrest.AutoServiceImplRest;
import com.courses.tellus.autosalon.service.springrest.AutoServiceRest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.BooleanSupplier;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

public class AutoServiceMockTest {

    @Mock
    AutoRepository autoRepository;
    @Mock
    Auto auto;
    @Mock
    AutoDto autoDto;

    AutoServiceRest autoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        autoService = new AutoServiceImplRest(autoRepository);
    }

    @Test
    public void testGetAllWhenResultTrue(){
        List<Auto> autoList = new ArrayList<>();
        autoList.add(auto);
        when(autoRepository.findAll()).thenReturn(autoList);
        assertThat(autoService.getAll(), is(autoList));
    }

    @Test
    public void testGetAllWhenResultFalse(){
        List<Auto> autoList = new ArrayList<>();
        when(autoRepository.findAll()).thenReturn(autoList);
        assertThat(autoService.getAll(), is(Collections.emptyList()));
    }

    @Test
    public void testGetByIdWhenResultTrue(){
        when(autoRepository.findById(anyLong())).thenReturn(Optional.of(auto));
        assertThat(autoService.getAutoById(1L), is(Optional.of(auto)));
    }

    @Test
    public void testGetByIdWhenResultFalse(){
        when(autoRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThat(autoService.getAutoById(1L), is(Optional.empty()));
    }

    @Test
    public void testDeleteMethod(){
        autoService.delete(1L);
        verify(autoRepository, atLeastOnce()).deleteById(anyLong());
    }

    @Test
    public void testInsertWhenResultTrue() {
        when(autoDto.getBrand()).thenReturn("BMW");
        when(autoDto.getModel()).thenReturn("X5");
        when(autoDto.getManufactYear()).thenReturn("2017");
        when(autoDto.getProducerCountry()).thenReturn("Germany");
        when(autoDto.getPrice()).thenReturn("20000");
        autoService.insert(autoDto);
        verify(autoRepository, atLeastOnce()).save(anyObject());
    }

    @Test
    public void testUpdateWhenResultTrue() {
        when(autoDto.getId()).thenReturn(1L);
        when(autoDto.getBrand()).thenReturn("BMW");
        when(autoDto.getModel()).thenReturn("X5");
        when(autoDto.getManufactYear()).thenReturn("2017");
        when(autoDto.getProducerCountry()).thenReturn("Germany");
        when(autoDto.getPrice()).thenReturn("20000");
        autoService.update(autoDto);
        verify(autoRepository, atLeastOnce()).save(anyObject());
    }

}
