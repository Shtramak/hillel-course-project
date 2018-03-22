package com.courses.tellus.autosalon.dao.springjdbc;

import static org.mockito.Mockito.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.autosalon.model.Autosalon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


public class AutosalonDaoMockTest {


    private AutosalonDao autosalonDao;
    private Autosalon autosalon;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void reInitDepartmentDao() throws SQLException {
        MockitoAnnotations.initMocks(this);
        autosalonDao = new AutosalonDao(jdbcTemplate);
        autosalon = new Autosalon(1L, "Toyota", "Japan", "444000");
    }

    @Test
    public void testInsertAutosalonDaoTrue() {
        when(autosalonDao.insert(autosalon)).thenReturn(1);
        Assertions.assertTrue(autosalonDao.insert(autosalon) == 1);
    }

    @Test
    public void testInsertAutosalonDaoWhenFalse() {
        when(autosalonDao.insert(autosalon)).thenThrow(BadSqlGrammarException.class);
        Assertions.assertTrue(autosalonDao.insert(autosalon) == -1);
    }

    @Test
    public void testUpdateAutoWhenTrue() {
        when(autosalonDao.update(autosalon)).thenReturn(1);
        Assertions.assertTrue(autosalonDao.update(autosalon) == 1);
    }

    @Test
    public void testUpdateAutoWhenFalse() {
        when(autosalonDao.update(autosalon)).thenThrow(BadSqlGrammarException.class);
        Assertions.assertTrue(autosalonDao.update(autosalon) == -1);
    }

    @Test
    public void testGetAutoSalonById() {
        when(jdbcTemplate.queryForObject(anyString(),  anyObject(), (RowMapper<Object>) any())).thenReturn(autosalon);
        when(autosalonDao.getById(1L)).thenReturn(Optional.of(autosalon));
        Assertions.assertEquals(autosalonDao.getById(1L), Optional.of(autosalon));
    }

    @Test
    public void testGetAutoSalonByIdWhenResultSetNull() throws SQLException {
        when(jdbcTemplate.queryForObject(anyString(),  anyObject(), (RowMapper<Object>) any())).thenThrow(EmptyResultDataAccessException.class);
        Assertions.assertEquals(Optional.empty(), autosalonDao.getById(1L));
    }

    @Test
    public void testAllAutosalon() {
        List<Autosalon> spy = spy(new ArrayList<Autosalon>());
        spy.add(autosalon);
        when(jdbcTemplate.query(anyString(), (RowMapper<Object>) any())).thenReturn(Collections.singletonList(spy));
        when(autosalonDao.getAll()).thenReturn(spy);
        Assertions.assertEquals(autosalonDao.getAll().size(), spy.size());
    }

    @Test
    public void testAllAutosalonWhenEmptyList() {
        when(jdbcTemplate.query(anyString(), (RowMapper<Object>) any())).thenThrow(BadSqlGrammarException.class);
        Assertions.assertEquals(Collections.emptyList(), autosalonDao.getAll());
    }

    @Test
    public void testDeleteAutoSalonById() {
        when(autosalonDao.delete(1L)).thenReturn(1);
        Assertions.assertTrue(autosalonDao.delete(1L) == 1);
    }

    @Test
    public void testDeleteAutoSalonByIdIfNull() {
        when(autosalonDao.delete(1L)).thenThrow(BadSqlGrammarException.class);
        Assertions.assertTrue(autosalonDao.delete(1L) == -1);
    }
}
