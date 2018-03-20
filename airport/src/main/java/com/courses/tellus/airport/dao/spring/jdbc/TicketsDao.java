package com.courses.tellus.airport.dao.spring.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.airport.dao.AirportDaoImpl;
import com.courses.tellus.airport.exception.DaoException;
import com.courses.tellus.airport.model.Ticket;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class TicketsDao implements AirportDaoImpl<Ticket> {
    private static final Logger LOGGER = Logger.getLogger(TicketsDao.class);
    private final transient JdbcTemplate jdbcTemplate;

    @Autowired
    public TicketsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Ticket> getAll() {
        try {
            return jdbcTemplate.query("SELECT * FROM airport_tickets", new TicketMapper());
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<Ticket> getById(Long ticketId) {
        try {
            final Ticket ticket = jdbcTemplate.queryForObject("SELECT * FROM airport_tickets WHERE id=" + ticketId, new TicketMapper());
            return Optional.of(ticket);
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Integer update(Ticket ticket) {
        final String sql = "UPDATE airport_tickets SET name = ?, surname = ?, dateOfFlight = ?, destCity = ? WHERE id = ?";
        try {
            return jdbcTemplate.update(sql, ticket.getName(), ticket.getSurname(), ticket.getDateFlight(), ticket.getDestCity(), ticket.getTicketId());
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage());
            return -1;
        }
    }

    @Override
    public Integer delete(Long ticketId) {
        try {
            return jdbcTemplate.update("DELETE FROM airport_tickets WHERE id = " + ticketId);
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage());
            return -1;
        }
    }

    @Override
    public Integer insert(Ticket ticket) {
        final String sql = "INSERT INTO airport_tickets (name, surname, dateOfFlight, destCity) VALUES (?, ?, ?, ?)";
        try {
            return jdbcTemplate.update(sql, ticket.getName(), ticket.getSurname(), ticket.getDateFlight(), ticket.getDestCity());
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage());
            return 0;
        }
    }

    class TicketMapper implements RowMapper<Ticket> {
        @Override
        public Ticket mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
            final Ticket ticket = new Ticket();
            ticket.setTicketId(resultSet.getLong("id"));
            ticket.setName(resultSet.getString("name"));
            ticket.setSurname(resultSet.getString("surname"));
            ticket.setDateFlight(resultSet.getDate("dateOfFlight").toLocalDate());
            ticket.setDestCity(resultSet.getString("destCity"));
            return ticket;
        }
    }
}
