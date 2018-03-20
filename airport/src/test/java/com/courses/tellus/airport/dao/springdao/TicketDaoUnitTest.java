package com.courses.tellus.airport.dao.springdao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

import com.courses.tellus.airport.config.springjdbc.JdbcTemplateConfig;
import com.courses.tellus.airport.dao.spring.jdbc.TicketsDao;
import com.courses.tellus.airport.model.Ticket;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyVararg;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class TicketDaoUnitTest {
    private static final Ticket TEST_TICKET = new Ticket(1L, "Ivan", "Ivanov", LocalDate.of(2018, 1, 1), "Odessa");

    @Mock
    private static JdbcTemplate jdbcTemplate;
    private TicketsDao ticketsDao;

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
        ticketsDao = new TicketsDao(jdbcTemplate);
    }

    @Test
    void getAllWhenEntryExistsReturnsListWithEssences() {
        List<Ticket> tickets = Arrays.asList(TEST_TICKET);
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(tickets);
        assertEquals(tickets, ticketsDao.getAll());
    }

    @Test
    void getAllWhenBadConnectionReturnsEmptyList() {
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenThrow(BadSqlGrammarException.class);
        assertEquals(Collections.emptyList(), ticketsDao.getAll());
    }

    @Test
    void getByIdWhenExistingIdReturnsEssence() {
        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class))).thenReturn(TEST_TICKET);
        assertEquals(Optional.of(TEST_TICKET), ticketsDao.getById(TEST_TICKET.getTicketId()));
    }

    @Test
    void getByIdWhenResultSetReturnsOptionalEmpty() {
        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class))).thenThrow(EmptyResultDataAccessException.class);
        assertEquals(Optional.empty(), ticketsDao.getById(10L));
    }

    @Test
    void updateWhenUpdatedSuccessfuly() {
        when(jdbcTemplate.update(anyString(), (Object[]) anyVararg())).thenReturn(1);
        assertEquals(Integer.valueOf(1), ticketsDao.update(TEST_TICKET));
    }

    @Test
    void updateWhenBadConnectionReturnsMinusOne() {
        when(jdbcTemplate.update(anyString(), (Object[]) anyVararg())).thenThrow(BadSqlGrammarException.class);
        assertEquals(Integer.valueOf(-1), ticketsDao.update(mock(Ticket.class)));
    }

    @Test
    void deleteWhenDeletedSuccessfuly() {
        when(jdbcTemplate.update(anyString())).thenReturn(1);
        assertEquals(Integer.valueOf(1), ticketsDao.delete(TEST_TICKET.getTicketId()));
    }

    @Test void deleteWhenBadConnectionReturnsMinusOne() {
        when(jdbcTemplate.update(anyString())).thenThrow(BadSqlGrammarException.class);
        assertEquals(Integer.valueOf(-1), ticketsDao.delete(TEST_TICKET.getTicketId()));
    }

    @Test
    void insertWhenInsertedSuccessfuly() {
        when(jdbcTemplate.update(anyString(), (Object[]) anyVararg())).thenReturn(0);
        assertEquals(Integer.valueOf(0), ticketsDao.insert(TEST_TICKET));
    }

    @Test
    void insertWhenBadConnectionReturnsZero() {
        when(jdbcTemplate.update(anyString(), (Object[]) anyVararg())).thenThrow(BadSqlGrammarException.class);
        assertEquals(Integer.valueOf(0), ticketsDao.insert(TEST_TICKET));
    }
}
