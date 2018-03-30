package com.courses.tellus.airport.dao.spring.jdbc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.airport.dao.AirportDaoInterface;
import com.courses.tellus.airport.model.Ticket;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class TicketsDao implements AirportDaoInterface<Ticket> {
    private static final Logger LOGGER = Logger.getLogger(TicketsDao.class);
    private final transient JdbcTemplate jdbcTemplate;

    @Autowired
    public TicketsDao(final JdbcTemplate jdbcTemplate) {
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
    public Optional<Ticket> getById(final Long ticketId) {
        try {
            final Ticket ticket = jdbcTemplate.queryForObject("SELECT * FROM airport_tickets "
                    + "WHERE id=" + ticketId, new TicketMapper());
            return Optional.of(ticket);
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Integer update(final Ticket ticket) {
        final String sql = "UPDATE airport_tickets SET name = ?, surname = ?, dateOfFlight = ?, destCity = ? "
                + "WHERE id = ?";
        try {
            return jdbcTemplate.update(sql, ticket.getName(), ticket.getSurname(), ticket.getDateFlight(),
                    ticket.getDestCity(), ticket.getTicketId());
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage());
            return -1;
        }
    }

    @Override
    public Integer delete(final Long ticketId) {
        try {
            return jdbcTemplate.update("DELETE FROM airport_tickets WHERE id = " + ticketId);
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage());
            return -1;
        }
    }

    @Override
    public Integer insert(final Ticket ticket) {
        final String sql = "INSERT INTO airport_tickets (name, surname, dateOfFlight, destCity) VALUES (?, ?, ?, ?)";
        try {
            return jdbcTemplate.update(sql, ticket.getName(), ticket.getSurname(), ticket.getDateFlight(), ticket.getDestCity());
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage());
            return 0;
        }
    }
}
