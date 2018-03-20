package com.courses.airport.dao.spring.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.courses.airport.dao.AirportDaoImpl;
import com.courses.airport.exception.DaoException;
import com.courses.airport.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class TicketsDao implements AirportDaoImpl<Ticket> {

    private final transient JdbcTemplate jdbcTemplate;

    @Autowired
    public TicketsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Ticket> getAll() throws DaoException {
        final List<Ticket> ticketsList = jdbcTemplate.query("SELECT * FROM airport_tickets", new TicketMapper());
        if (ticketsList.size() >= 1) {
            return ticketsList;
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<Ticket> getById(Long ticketId) throws DaoException {
        final Ticket ticket = jdbcTemplate
                .queryForObject("SELECT * FROM airport_tickets WHERE id=" + ticketId, new TicketMapper());
        return Optional.of(ticket);
    }

    @Override
    public Integer update(Ticket ticket) throws DaoException {
        final String sql = "UPDATE airport_tickets SET name = ?, surname = ?, dateOfFlight = ?, destCity = ? WHERE id = ?";
        return jdbcTemplate.update(sql, ticket.getName(), ticket.getSurname(), ticket.getDateFlight(), ticket.getDestCity(), ticket.getTicketId());
    }

    @Override
    public Integer delete(Long ticketId) throws DaoException {
        return jdbcTemplate.update("DELETE FROM airport_tickets WHERE id = " + ticketId);
    }

    @Override
    public Integer insert(Ticket ticket) throws DaoException {
        final String sql = "INSERT INTO airport_tickets (name, surname, dateOfFlight, destCity) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, ticket.getName(), ticket.getSurname(), ticket.getDateFlight(), ticket.getDestCity());
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
