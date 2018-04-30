package com.courses.tellus.autosalon.controller.springmvc;

import com.courses.tellus.autosalon.dao.springjdbc.AutoDao;
import com.courses.tellus.autosalon.model.Auto;
import com.courses.tellus.autosalon.service.AutoService;
import com.courses.tellus.autosalon.service.AutoServiceImpl;
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
import static org.mockito.Mockito.when;

public class AutoServiceMockTest {

    @Mock
    AutoDao  autoDao;
    @Mock
    Auto auto;

    AutoService autoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        autoService = new AutoServiceImpl(autoDao);
    }

    @Test
    public void testGetAllWhenResultTrue(){
        List<Auto> autoList = new ArrayList<>();
        autoList.add(auto);
        when(autoDao.getAll()).thenReturn(autoList);
        assertThat(autoService.getAll(), is(autoList));
    }

    @Test
    public void testGetAllWhenResultfalse(){
        List<Auto> autoList = new ArrayList<>();
        when(autoDao.getAll()).thenReturn(autoList);
        assertThat(autoService.getAll(), is(Collections.emptyList()));
    }

    @Test
    public void testInsertWhenResultTrue(){
        when(autoDao.insert(anyObject())).thenReturn(1);
        assertThat(autoService.insert(auto), is(1));
    }

    @Test
    public void testInsertWhenResultFalse(){
        when(autoDao.insert(anyObject())).thenReturn(-1);
        assertThat(autoService.insert(auto), is(-1));
    }

    @Test
    public void testUpdateWhenResultTrue(){
        when(autoDao.update(anyObject())).thenReturn(1);
        assertThat(autoService.update(auto), is(1));
    }

    @Test
    public void testUpdateWhenResultFalse(){
        when(autoDao.update(anyObject())).thenReturn(-1);
        assertThat(autoService.update(auto), is(-1));
    }

    @Test
    public void testDeleteWhenResultTrue(){
        when(autoDao.delete(anyLong())).thenReturn(1);
        assertThat(autoService.delete(1L), is(1));
    }

    @Test
    public void testDeleteWhenREsultFalse(){
        when(autoDao.delete(anyLong())).thenReturn(-1);
        assertThat(autoService.delete(1L), is(-1));
    }

    @Test
    public void testGetByIdWhenResultTrue(){
        when(autoDao.getById(anyLong())).thenReturn(Optional.of(auto));
        assertThat(autoService.getAutoById(1L), is(Optional.of(auto)));
    }

    @Test
    public void testGetByIdWhenResultFalse(){
        when(autoDao.getById(anyLong())).thenReturn(Optional.empty());
        assertThat(autoService.getAutoById(1L), is(Optional.empty()));
    }
}
