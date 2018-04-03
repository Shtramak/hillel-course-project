package com.courses.tellus.autosalon.dao.springjdbc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

import java.util.*;

import com.courses.tellus.autosalon.model.Auto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class AutoDaoMockTest {

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
    public void testGetAllWhenResultTrue() {
        List<Auto> autoList = new ArrayList<Auto>();
        autoList.add(auto);
        when(jdbcTemplate.query(anyString(), any(AutoMapper.class))).thenReturn(autoList);
        assertThat(autoDao.getAll().size(), is(1));
    }

    @Test
    public void testGetAllWhenResultFalse() {
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenThrow(EmptyResultDataAccessException.class);
        assertEquals(Collections.emptyList(), autoDao.getAll());
    }

    @Test
    public void testInsertWhenResultTrue() {
        when(jdbcTemplate.update(anyString(), (Object[]) anyVararg())).thenReturn(1);
        assertThat(autoDao.insert(auto), is(1));
    }

    @Test
    public void testInsertAutoWhenResultFalse() {
        when(jdbcTemplate.update(anyString(), (Object[]) anyVararg())).thenThrow(EmptyResultDataAccessException.class);
        assertEquals(Integer.valueOf(-1), autoDao.insert(auto));
    }

    @Test
    public void testUpdateAutoWhenResultTrue() {
        when(jdbcTemplate.update(anyString(), (Object[]) anyVararg())).thenReturn(1);
        assertThat(autoDao.update(auto), is(1));
    }

    @Test
    public void testUpdateAutoWhenResultFalse() {
        when(jdbcTemplate.update(anyString(), (Object[]) anyVararg())).thenThrow(EmptyResultDataAccessException.class);
        assertEquals(Integer.valueOf(-1), autoDao.update(auto));
    }

    @Test
    public void testDeleteAutoWhenResultTrue() {
        when(jdbcTemplate.update(anyString(), (Object[]) anyVararg())).thenReturn(1);
        assertThat(autoDao.delete(1L), is(1));
    }

    @Test
    public void testDeleteAutoWhenResultFalse() {
        when(jdbcTemplate.update(anyString(), (Object[]) anyVararg())).thenThrow(EmptyResultDataAccessException.class);
        assertEquals(Integer.valueOf(-1), autoDao.delete(1L));
    }

    @Test
    public void testGetAutoByIdWhenResultTrue() {
        when(jdbcTemplate.queryForObject(anyString(), anyObject(), any(AutoMapper.class))).thenReturn(auto);
        assertEquals(Optional.of(auto), autoDao.getById(1L));
    }

    @Test
    public void testGetAutoByIdWhenResultFalse() {
        when(jdbcTemplate.queryForObject(anyString(), anyObject(), any(RowMapper.class))).thenThrow(EmptyResultDataAccessException.class);
        assertEquals(Optional.empty(), autoDao.getById(12L));
    }
}
