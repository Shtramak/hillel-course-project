package com.courses.airport.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import com.courses.airport.connection.jdbc.ConnectionFactory;
import com.courses.airport.exception.DaoException;
import com.courses.airport.model.Ticket;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class TicketsDaoUnitMockTest {
    private final Ticket TEST_TICKET = new Ticket(1, "Igor", "Fedotov", LocalDate.of(2018, 1, 1), "Keln");
    private TicketsDao ticketsDao;

    @Mock
    private ConnectionFactory connectionFactoryMock;
    @Mock
    private Connection connectionMock;
    @Mock
    private PreparedStatement preparedStatementMock;
    @Mock
    private Ticket ticketMock;
    @Mock
    private Statement statementMock;
    @Mock
    private ResultSet resultSetMock;

    @BeforeAll
    static void disableWarning() {
        System.err.close();
    }

    @BeforeEach
    void initMocks() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(connectionFactoryMock.getConnection()).thenReturn(connectionMock);
        ticketsDao = new TicketsDao(connectionFactoryMock);
    }

    @Test
    void insertWhenExecuteUpdatePositiveDataReturnsTrue() throws Exception {
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeUpdate()).thenReturn(1);
        assertEquals(Integer.valueOf(1), ticketsDao.insert(TEST_TICKET));
    }

    @Test
    void insertWhenBadConnectionThrowsDAOException() throws Exception {
        when(connectionMock.prepareStatement(anyString())).thenThrow(SQLException.class);
        assertThrows(DaoException.class, () -> ticketsDao.insert(ticketMock));
    }

    @Test
    void getByIdWithExistingIdReturnsTicket() throws Exception {
        returnMockResultSet();
        when(resultSetMock.next()).thenReturn(true);
        putRealTicketIntoResulsetMock();
        Ticket result = ticketsDao.getById(TEST_TICKET.getTicketId()).orElse(null);
        assertEquals(TEST_TICKET, result);
    }

    @Test
    void getByIdWhenEmptyResultSetReturnsOptionalEmpty() throws Exception {
        returnMockResultSet();
        when(resultSetMock.next()).thenReturn(false);
        Long id = ThreadLocalRandom.current().nextLong();
        assertEquals(Optional.empty(), ticketsDao.getById(id));
    }

    @Test
    void getByIdWhenBadConnectionThrowsDaoException() throws Exception {
        when(connectionMock.createStatement()).thenThrow(SQLException.class);
        assertThrows(DaoException.class, () -> ticketsDao.getById(1L));
    }

    @Test
    void getAllWhenEntryExistsReturnsListWithTickets() throws Exception {
        returnMockResultSet();
        when(resultSetMock.next()).thenReturn(true).thenReturn(false);
        putRealTicketIntoResulsetMock();
        List<Ticket> expected = Collections.singletonList(TEST_TICKET);
        assertEquals(expected, ticketsDao.getAll());
    }

    @Test
    void getAllWhenBadConnectionThrowsDaoException() throws Exception {
        when(connectionMock.createStatement()).thenThrow(SQLException.class);
        assertThrows(DaoException.class, () -> ticketsDao.getAll());
    }

    @Test
    void updateWhenEntryExistsReturnsOne() throws Exception {
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeUpdate()).thenReturn(1);
        when(ticketMock.getDateFlight()).thenReturn(LocalDate.now());
        assertEquals(Integer.valueOf(1), ticketsDao.update(ticketMock));
    }

    @Test
    void updateWhenEntryNotExistsReturnsZero() throws Exception {
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeUpdate()).thenReturn(0);
        when(ticketMock.getDateFlight()).thenReturn(LocalDate.now());
        assertEquals(Integer.valueOf(0), ticketsDao.update(ticketMock));
    }

    @Test
    void updateWhenBadConnectionThrowsDaoException() throws Exception {
        when(connectionMock.prepareStatement(anyString())).thenThrow(SQLException.class);
        assertThrows(DaoException.class, () -> ticketsDao.update(ticketMock));
    }

    @Test
    void deleteWhenWhenEntryExistsReturnsOne() throws Exception {
        when(connectionMock.createStatement()).thenReturn(statementMock);
        when(statementMock.executeUpdate(anyString())).thenReturn(1);
        Long id = ThreadLocalRandom.current().nextLong();
        assertEquals(Integer.valueOf(1), ticketsDao.delete(id));
    }

    @Test
    void deleteWhenWhenEntryNotExistsReturnsZero() throws Exception {
        when(connectionMock.createStatement()).thenReturn(statementMock);
        when(statementMock.executeUpdate(anyString())).thenReturn(0);
        assertEquals(Integer.valueOf(0), ticketsDao.delete(1L));
    }

    @Test
    void deleteWhenBadConnectionThrowsDaoException() throws Exception {
        when(connectionMock.createStatement()).thenThrow(SQLException.class);
        assertThrows(DaoException.class, () -> ticketsDao.delete(1L));
    }

    private void returnMockResultSet() throws SQLException {
        when(connectionMock.createStatement()).thenReturn(statementMock);
        when(statementMock.executeQuery(anyString())).thenReturn(resultSetMock);
    }

    private void putRealTicketIntoResulsetMock() throws SQLException {
        when(resultSetMock.getLong("id")).thenReturn(TEST_TICKET.getTicketId());
        when(resultSetMock.getString("name")).thenReturn(TEST_TICKET.getName());
        when(resultSetMock.getString("surname")).thenReturn(TEST_TICKET.getSurname());
        Date date = Date.valueOf(TEST_TICKET.getDateFlight());
        when(resultSetMock.getDate("dateOfFlight")).thenReturn(date);
        when(resultSetMock.getString("destCity")).thenReturn(TEST_TICKET.getDestCity());
    }
}