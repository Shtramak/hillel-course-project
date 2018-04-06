package com.courses.tellus.airport.dao.spring.jdbc;

import com.courses.tellus.airport.dao.spring.jdbc.TicketMapper;
import com.courses.tellus.airport.model.Ticket;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TicketsMapperTest {
    private static final Ticket TEST_TICKET = new Ticket(1L, "Ivan", "Ivanov", LocalDate.of(2018, 1, 1), "Odessa");

    @Test
    void ticketMapperMapRowReturnsTicket() throws SQLException {
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.getLong("id")).thenReturn(TEST_TICKET.getTicketId());
        when(resultSetMock.getString("name")).thenReturn(TEST_TICKET.getName());
        when(resultSetMock.getString("surname")).thenReturn(TEST_TICKET.getSurname());
        when(resultSetMock.getDate("dateOfFlight")).thenReturn(Date.valueOf(TEST_TICKET.getDateFlight()));
        when(resultSetMock.getString("destCity")).thenReturn(TEST_TICKET.getDestCity());
        TicketMapper ticketMapper = new TicketMapper();
        assertEquals(TEST_TICKET, ticketMapper.mapRow(resultSetMock, 1));
    }
}
