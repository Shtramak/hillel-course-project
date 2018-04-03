package com.courses.tellus.airport.dao.spring.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.courses.tellus.airport.model.Ticket;
import org.springframework.jdbc.core.RowMapper;

public class TicketMapper implements RowMapper<Ticket> {
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