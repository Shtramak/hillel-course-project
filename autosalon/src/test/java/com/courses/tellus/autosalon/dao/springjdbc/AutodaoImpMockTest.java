package com.courses.tellus.autosalon.dao.springjdbc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.autosalon.model.Auto;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

public class AutodaoImpMockTest {

    private AutoDao autoDao;

    @Mock
    Auto auto;

    @Mock
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        autoDao = new AutoDao(jdbcTemplate);
    }

    @Test
    public void testGetAllWhenResultTrue(){
        List<Auto> autoList = new ArrayList<Auto>();
        autoList.add(auto);
        when(jdbcTemplate.query(anyString(), (RowMapper<Object>) any())).thenReturn(Collections.singletonList(autoList));
        assertThat(autoDao.getAll().size(), is(1));
    }

    @Test
    public void testGetAllWhenResultFalse(){
        List<Auto> autoList = new ArrayList<Auto>();
        when(jdbcTemplate.query(anyString(), (RowMapper<Object>) any())).thenReturn(Collections.singletonList(autoList));
        assertNotEquals(2, autoDao.getAll().size());
    }

    @Test
    public void testInsertWhenResultTrue(){
        when(autoDao.insert(auto)).thenReturn(1);
        assertThat(autoDao.insert(auto), is(1));
    }

    @Test
    public void testInsertAutoWhenResultFalse() {
        when(autoDao.insert(auto)).thenReturn(0);
        assertNotEquals(1, autoDao.insert(auto));
    }

    @Test
    public void testUpdateAutoWhenResultTrue() {
        when(autoDao.update(auto)).thenReturn(1);
        assertThat(autoDao.update(auto), is(1));
    }

    @Test
    public void testUpdateAutoWhenResultFalse() {
        when(autoDao.update(auto)).thenReturn(0);
        assertNotEquals(1, autoDao.update(auto));
    }

    @Test
    public void testRemoveAutoWhenResultTrue() {
        when(autoDao.delete(1L)).thenReturn(1);
        assertThat(autoDao.delete(1L), is(1));
    }

    @Test
    public void testRemoveAutoWhenResultFalse() {
        when(autoDao.delete(1L)).thenReturn(0);
        assertNotEquals(1, autoDao.delete(1L));
    }

    @Test
    public void testGetAutoByIdWhenResultTrue() {
        when(jdbcTemplate.queryForObject(anyString(),  anyObject(), (RowMapper<Object>) any())).thenReturn(auto);
        assertEquals(Optional.of(auto), autoDao.getById(1L));
    }

    @Test
    public void testGetAutoByIdWhenResultFalse() {
        when(jdbcTemplate.queryForObject(anyString(),  anyObject(), (RowMapper<Object>) any())).thenReturn(auto);
        assertNotEquals(Optional.of(autoDao), autoDao.getById(1L));
    }
}
