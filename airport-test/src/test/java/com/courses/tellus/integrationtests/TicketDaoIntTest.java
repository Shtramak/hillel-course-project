package com.courses.tellus.integrationtests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.courses.airport.connection.jdbc.ConnectionFactory;
import com.courses.airport.dao.jdbc.TicketsDao;
import com.courses.airport.model.Ticket;
import com.courses.airport.exception.DaoException;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TicketDaoIntTest {

    private static final Ticket TICKET = new Ticket(1, "Igor", "Fedotov", LocalDate.of(2018, 1, 1), "Keln");
    private static ConnectionFactory connectionFactory;
    private TicketsDao ticketsDao;

    @BeforeEach
    void setUp() throws Exception {
        connectionFactory = ConnectionFactory.getInstance();
        ticketsDao = new TicketsDao(connectionFactory);
        RunScript.execute(connectionFactory.getConnection(), new FileReader("src/test/resources/ticket_db.sql"));
    }

    @Test
    void insertWithValidDataReturnsTrue() throws Exception {
        RunScript.execute(connectionFactory.getConnection(), new FileReader("src/test/resources/delete-raws-ticket.sql"));
        assertEquals(Integer.valueOf(1), ticketsDao.insert(TICKET));
    }

    @Test
    void insertWhenTableNotExistsThrowsDaoException() throws Exception {
        RunScript.execute(connectionFactory.getConnection(), new FileReader("src/test/resources/drop-table.sql"));
        assertThrows(DaoException.class, () -> {
            ticketsDao.insert(TICKET);
        });
    }

    @Test
    void getByIdWithExistingIdReturnsTicket() throws Exception {
        Ticket actual = ticketsDao.getById(1L).orElse(null);
        assertEquals(TICKET, actual);
    }

    @Test
    void getByIdWithNotExistingIdReturnsOptionalEmpty() throws Exception {
        assertEquals(Optional.empty(), ticketsDao.getById(3L));
    }

    @Test
    void getByIdWhenTableNotExistsThrowsDaoException() throws Exception {
        RunScript.execute(connectionFactory.getConnection(), new FileReader("src/test/resources/drop-table.sql"));
        assertThrows(DaoException.class, () -> {
            ticketsDao.getById(1L);
        });
    }

    @Test
    void getAllWhenTableHasDataReturnsListOfTickets() throws Exception {
        List<Ticket> expected = Arrays.asList(TICKET);
        List<Ticket> actual = ticketsDao.getAll();
        assertEquals(expected, actual);
    }

    @Test
    void getAllWhenTableHasNoDataReturnsEmptyList() throws Exception {
        Connection connection = connectionFactory.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("TRUNCATE TABLE airport_tickets");
        assertEquals(Collections.emptyList(), ticketsDao.getAll());
    }

    @Test
    void getAllWhenTableNotExistsThrowsDaoException() throws Exception {
        RunScript.execute(connectionFactory.getConnection(), new FileReader("src/test/resources/drop-table.sql"));
        assertThrows(DaoException.class, () -> {
            ticketsDao.getAll();
        });
    }

    @Test
    void updateWhenEntryExistsReturnsTrue() throws Exception {
        assertEquals(Integer.valueOf(1), ticketsDao.update(TICKET));
    }

    @Test
    void updateWhenEntryNotExistsReturnsFalse() throws Exception {
        RunScript.execute(connectionFactory.getConnection(), new FileReader("src/test/resources/delete-raws-ticket.sql"));
        assertEquals(Integer.valueOf(0), ticketsDao.update(TICKET));
    }

    @Test
    void updateWhenTableNotExistsThrowsDaoException() throws Exception {
        RunScript.execute(connectionFactory.getConnection(), new FileReader("src/test/resources/drop-table.sql"));
        assertThrows(DaoException.class, () -> {
            ticketsDao.update(new Ticket());
        });
    }

    @Test
    void deleteWithExistingIdReturnsOne() throws Exception {
        assertEquals(Integer.valueOf(1), ticketsDao.delete(1L));
    }

    @Test
    void deleteWithNotExistingReturnsZero() throws Exception {
        assertEquals(Integer.valueOf(0), ticketsDao.delete(3L));
    }

    @Test
    void deleteWhenTableNotExistsThrowsDaoException() throws Exception {
        RunScript.execute(connectionFactory.getConnection(), new FileReader("src/test/resources/drop-table.sql"));
        assertThrows(DaoException.class, () -> {
            ticketsDao.delete(1L);
        });
    }
}
