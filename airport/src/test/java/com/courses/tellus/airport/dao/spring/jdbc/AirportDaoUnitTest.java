package com.courses.tellus.airport.dao.spring.jdbc;

import com.courses.tellus.airport.config.springjdbc.JdbcTemplateConfig;
import com.courses.tellus.airport.dao.spring.jdbc.AirportDao;
import com.courses.tellus.airport.model.Airport;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyVararg;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AirportDaoUnitTest {
    private static final Airport TEST_AIRPORT= new Airport(1L, "Borysbil", LocalDate.of(1990, 1, 1), "D", "04466666666");

    @Mock
    private static JdbcTemplate jdbcTemplate;
    private AirportDao airportDao;

    @BeforeAll
    static void initJdbcTemplate() {
        JdbcTemplateConfig config = mock(JdbcTemplateConfig.class);
        DataSource dataSource = mock(DataSource.class);
        when(config.dataSource()).thenReturn(dataSource);
        when(config.jdbcTemplate(dataSource)).thenReturn(jdbcTemplate);
    }

    @BeforeAll
    static void disableWarning() {
        System.err.close();
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        airportDao = new AirportDao(jdbcTemplate);
    }

    @Test
    void getAllWhenEntryExistsReturnsListWithEssences() {
        List<Airport> airports = Arrays.asList(TEST_AIRPORT);
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(airports);
        assertEquals(airports, airportDao.getAll());
    }

    @Test
    void getAllWhenBadConnectionReturnsEmptyList() {
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenThrow(BadSqlGrammarException.class);
        assertEquals(Collections.emptyList(), airportDao.getAll());
    }

    @Test
    void getByIdWhenExistingIdReturnsEssence() {
        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class))).thenReturn(TEST_AIRPORT);
        assertEquals(Optional.of(TEST_AIRPORT), airportDao.getById(TEST_AIRPORT.getAirportId()));
    }

    @Test
    void getByIdWhenResultSetReturnsOptionalEmpty() {
        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class))).thenThrow(EmptyResultDataAccessException.class);
        assertEquals(Optional.empty(), airportDao.getById(10L));
    }

    @Test
    void updateWhenUpdatedSuccessfuly() {
        when(jdbcTemplate.update(anyString(), (Object[]) anyVararg())).thenReturn(1);
        assertEquals(Integer.valueOf(1), airportDao.update(TEST_AIRPORT));
    }

    @Test
    void updateWhenBadConnectionReturnsMinusOne() {
        when(jdbcTemplate.update(anyString(), (Object[]) anyVararg())).thenThrow(BadSqlGrammarException.class);
        assertEquals(Integer.valueOf(-1), airportDao.update(mock(Airport.class)));
    }

    @Test
    void deleteWhenDeletedSuccessfuly() {
        when(jdbcTemplate.update(anyString())).thenReturn(1);
        assertEquals(Integer.valueOf(1), airportDao.delete(TEST_AIRPORT.getAirportId()));
    }

    @Test void deleteWhenBadConnectionReturnsMinusOne() {
        when(jdbcTemplate.update(anyString())).thenThrow(BadSqlGrammarException.class);
        assertEquals(Integer.valueOf(-1), airportDao.delete(TEST_AIRPORT.getAirportId()));
    }

    @Test
    void insertWhenInsertedSuccessfuly() {
        when(jdbcTemplate.update(anyString(), (Object[]) anyVararg())).thenReturn(0);
        assertEquals(Integer.valueOf(0), airportDao.insert(TEST_AIRPORT));
    }

    @Test
    void insertWhenBadConnectionReturnsZero() {
        when(jdbcTemplate.update(anyString(), (Object[]) anyVararg())).thenThrow(BadSqlGrammarException.class);
        assertEquals(Integer.valueOf(-1), airportDao.insert(TEST_AIRPORT));
    }
}
