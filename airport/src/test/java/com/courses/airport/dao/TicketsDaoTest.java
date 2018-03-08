package com.courses.airport.dao;

import com.courses.airport.connection.ConnectionFactory;
import com.courses.airport.essences.Ticket;
import com.courses.airport.exception.DaoException;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class TicketsDaoTest {
    private Connection connection;
    private TicketsDao ticketsDao;

    @BeforeEach
    public void setUp() throws IOException, SQLException {
        connection = ConnectionFactory.getInstance().getConnection();
        RunScript.execute(connection, new FileReader("src/test/resources/db-creation.sql"));
        ticketsDao = new TicketsDao(connection);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        executeSqlQuery("DROP TABLE airport_tickets");
    }

    @Test
    public void insertWithValidDataReturnsTrue() throws DaoException {
        Ticket ticket = new Ticket(1, "Igor", "Fedotov", LocalDate.of(2018, 1, 1), "Keln");
        assertTrue(ticketsDao.insert(ticket));
    }

    @Test
    public void insertWhenTicketHasNullElementThrowsDAOException() throws DaoException {
        Ticket ticket = new Ticket(1, "Igor", null, LocalDate.of(2018, 1, 1), "Keln");
        assertThrows(DaoException.class, () -> {
            ticketsDao.insert(ticket);
        });
    }

    @Test
    public void insertWhenTicketIsNullThrowsDaoException() throws DaoException {
        Throwable exception = assertThrows(DaoException.class, () -> {
            ticketsDao.insert(null);
        });
        assertEquals("Ticket must be not null!", exception.getMessage());
    }

    @Test
    public void findByIdWithExistingIdReturnsTicket() throws DaoException {
        insertTicketsToDb(3);
        Ticket expected = new Ticket(2, "name2", "surname2", LocalDate.of(2018, 1, 1), "destCity2");
        Ticket actual = ticketsDao.findById(2);
        assertEquals(expected, actual);
    }

    @Test
    public void findByIdWithNotExistingIdReturnsNull() throws DaoException {
        insertTicketsToDb(1);
        assertEquals(null, ticketsDao.findById(2));
    }

    @Test
    public void findByIdWithNegativeIdThrowsDaoException() throws DaoException {
        Throwable exception = assertThrows(DaoException.class, () -> {
            ticketsDao.findById(-1);
        });
        assertEquals("Ticket id must be positive, but entered: -1", exception.getMessage());
    }

    @Test
    public void findAllWhenTableHasDataReturnsListOfTickets() throws DaoException {
        insertTicketsToDb(2);
        Ticket ticket1 = new Ticket(1, "name1", "surname1", LocalDate.of(2018, 1, 1), "destCity1");
        Ticket ticket2 = new Ticket(2, "name2", "surname2", LocalDate.of(2018, 1, 1), "destCity2");
        List<Ticket> expected = Arrays.asList(ticket1, ticket2);
        List<Ticket> actual = ticketsDao.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void findAllWhenTableHasNoDataReturnsEmptyList() throws DaoException {
        assertEquals(Collections.emptyList(), ticketsDao.findAll());
    }

    @Test
    public void updateWhenEntryExistsReturnsTrue() throws DaoException {
        insertTicketsToDb(3);
        Ticket updatedTicket = new Ticket(2, "updateName", "updateSurname", LocalDate.of(2018, 1, 1), "destCity2");
        assertTrue(ticketsDao.update(updatedTicket));
    }

    @Test
    public void updateWhenEntryNotExistsReturnsFalse() throws DaoException {
        insertTicketsToDb(2);
        Ticket updatedTicket = new Ticket(3, "updateName", "updateSurname", LocalDate.of(2018, 1, 1), "destCity3");
        assertFalse(ticketsDao.update(updatedTicket));
    }

    @Test
    public void updateWhenTicketIsNullThrowsDaoException() throws DaoException {
        Throwable exception = assertThrows(DaoException.class, () -> {
            ticketsDao.update(null);
        });
        assertEquals("Ticket must be not null!", exception.getMessage());
    }

    @Test
    public void removeByIdWithExistingIdReturnsTrue() throws DaoException {
        insertTicketsToDb(3);
        assertTrue(ticketsDao.removeById(2));
    }

    @Test
    public void removeByIdWithNotExistingReturnsFalse() throws DaoException {
        insertTicketsToDb(1);
        assertFalse(ticketsDao.removeById(2));
    }

    @Test
    public void removeByIdWithNegativeIdThrowsDaoException() throws DaoException {
        Throwable exception = assertThrows(DaoException.class, () -> {
            ticketsDao.removeById(-1);
        });
        assertEquals("Ticket id must be positive, but entered: -1", exception.getMessage());
    }

    private void executeSqlQuery(String sqlQuery) throws SQLException {
        Objects.requireNonNull(connection);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlQuery);
        }
    }

    private void insertTicketsToDb(int numberOfTickets) {
        String sql = "INSERT INTO" +
                " airport_tickets (id, name, surname, dateOfFlight, destCity)" +
                " VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int id = 1; id <= numberOfTickets; id++) {
                statement.setLong(1, id);
                statement.setString(2, "name" + id);
                statement.setString(3, "surname" + id);
                statement.setDate(4, Date.valueOf(LocalDate.of(2018, 1, 1)));
                statement.setString(5, "destCity" + id);
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}