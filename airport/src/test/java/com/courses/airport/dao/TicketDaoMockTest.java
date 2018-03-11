package com.courses.airport.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import com.courses.airport.model.Ticket;
import com.courses.airport.exception.DaoException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TicketDaoMockTest {
    private Connection connection;
    private TicketsDao ticketsDao;

    @BeforeAll
    public static void disableWarning() {
        System.err.close();
        System.setErr(System.out);
    }

    @BeforeEach
    public void setUp() {
        connection = mock(Connection.class);
        ticketsDao = new TicketsDao(connection);
    }

    @Test
    public void insertWhenExecuteUpdatePositiveDataReturnsTrue() throws DaoException, SQLException {
        Ticket ticket = new Ticket(1, "Igor", "Fedotov", LocalDate.of(2018, 1, 1), "Keln");
        PreparedStatement statement = mock(PreparedStatement.class);
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeUpdate()).thenReturn(1);
        assertTrue(ticketsDao.insert(ticket));
    }
    @Test
    public void insertWhenExecuteUpdateNegativeDataReturnsTrue() throws DaoException, SQLException {
        Ticket ticket = new Ticket(1, "Igor", "Fedotov", LocalDate.of(2018, 1, 1), "Keln");
        PreparedStatement statement = mock(PreparedStatement.class);
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeUpdate()).thenReturn(-1);
        assertFalse(ticketsDao.insert(ticket));
    }

    @Test
    public void insertWhenBadConnectionThrowsDAOException() throws DaoException, SQLException {
        Ticket ticket = mock(Ticket.class);
        when(connection.prepareStatement(anyString())).thenThrow(SQLException.class);
        assertThrows(DaoException.class, () -> ticketsDao.insert(ticket));
    }

    @Test
    public void findByIdWithExistingIdReturnsTicket() throws DaoException, SQLException {
        Statement statement = mock(Statement.class);
        ResultSet resultSet = mock(ResultSet.class);
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        Ticket ticket = new Ticket(1, "Igor", "Fedotov", LocalDate.of(2018, 1, 1), "Keln");
        when(resultSet.getLong("id")).thenReturn(ticket.getTicketId());
        when(resultSet.getString("name")).thenReturn(ticket.getName());
        when(resultSet.getString("surname")).thenReturn(ticket.getSurname());
        Date date = Date.valueOf(ticket.getDateFlight());
        when(resultSet.getDate("dateOfFlight")).thenReturn(date);
        when(resultSet.getString("destCity")).thenReturn(ticket.getDestCity());
        assertEquals(ticket, ticketsDao.findById(ticket.getTicketId()));
    }

    @Test
    public void findByIdWhenEmptyResultSetReturnsNull() throws DaoException, SQLException {
        Statement statement = mock(Statement.class);
        ResultSet resultSet = mock(ResultSet.class);
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);
        assertNull(ticketsDao.findById(1L));
    }

    @Test
    public void findByIdWithNegativeIdThrowsDaoException() throws DaoException {
        Throwable exception = assertThrows(DaoException.class, () -> {
            ticketsDao.findById(-1);
        });
        assertEquals("Ticket id must be positive, but entered: -1", exception.getMessage());    }

    @Test
    public void findByIdWhenBadConnectionThrowsDaoException() throws DaoException, SQLException {
        when(connection.createStatement()).thenThrow(SQLException.class);
        assertThrows(DaoException.class, () -> ticketsDao.findById(1L));
    }

    @Test
    public void findAllWhenBadConnectionThrowsDaoException() throws DaoException, SQLException {
        when(connection.createStatement()).thenThrow(SQLException.class);
        assertThrows(DaoException.class, () -> ticketsDao.findAll());
    }

    @Test
    public void updateWhenBadConnectionThrowsDaoException() throws DaoException, SQLException {
        Ticket ticket = mock(Ticket.class);
        when(connection.prepareStatement(anyString())).thenThrow(SQLException.class);
        assertThrows(DaoException.class, () -> ticketsDao.update(ticket));
    }

    @Test
    public void removeByIdWithNegativeIdThrowsDaoException() throws DaoException {
        Throwable exception = assertThrows(DaoException.class, () -> {
            ticketsDao.findById(-1L);
        });
        assertEquals("Ticket id must be positive, but entered: -1", exception.getMessage());
    }

    @Test
    public void removeByIdWhenBadConnectionThrowsDaoException() throws DaoException, SQLException {
        when(connection.createStatement()).thenThrow(SQLException.class);
        assertThrows(DaoException.class, () -> ticketsDao.removeById(1L));
    }
}
