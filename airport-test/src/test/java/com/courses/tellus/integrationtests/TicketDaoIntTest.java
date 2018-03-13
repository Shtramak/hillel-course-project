package com.courses.tellus.integrationtests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import com.courses.airport.connection.ConnectionFactory;
import com.courses.airport.dao.TicketsDao;
import com.courses.airport.model.Ticket;
import com.courses.airport.exception.DaoException;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TicketDaoIntTest {

    private static final Ticket TICKET = new Ticket(1, "Igor", "Fedotov", LocalDate.of(2018, 1, 1), "Keln");

    private static ConnectionFactory connectionFactory;

    private TicketsDao ticketsDao;

    @BeforeEach
    public void setUp() throws Exception {
        connectionFactory = ConnectionFactory.getInstance();
        ticketsDao = new TicketsDao(connectionFactory);
        RunScript.execute(connectionFactory.getConnection(), new FileReader("src/test/resources/db-creation.sql"));
    }

    @Test
    void insertWithValidDataReturnsTrue() throws Exception {
        Ticket ticket = new Ticket(3, "Vladimir", "Klichko", LocalDate.of(2018, 1, 1), "Munchen");
        Integer res = ticketsDao.insert(ticket);
        assertEquals(Integer.valueOf(1), res);
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
        long id = ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE);
        assertThrows(DaoException.class, () -> {
            ticketsDao.getById(id);
        });
    }

    @Test
    void getAllWhenTableHasDataReturnsListOfTickets() throws Exception {
        List<Ticket> expected = Arrays.asList(TICKET, new Ticket(2, "Vitalii", "Klichko", LocalDate.of(2018, 1, 1), "Berlin"));
        List<Ticket> actual = ticketsDao.getAll();
        assertEquals(expected, actual);
    }

    @Test
    void getAllWhenTableHasNoDataReturnsEmptyList() throws Exception {
        Connection connection = connectionFactory.getConnection();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE airport_tickets");
        }
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
        Ticket updatedTicket = new Ticket(1, "Arnold", "Swarzneger", LocalDate.of(2018, 3, 3), "Viena");
        assertEquals(Integer.valueOf(1), ticketsDao.update(updatedTicket));
    }

    @Test
    void updateWhenEntryNotExistsReturnsFalse() throws Exception {
        Ticket updatedCustomer = new Ticket(3, "Leonid", "Chernovezkii", LocalDate.of(1991, 01, 01), "Bombei");
        assertEquals(Integer.valueOf(0), ticketsDao.update(updatedCustomer));
    }

    @Test
    void updateWhenTableNotExistsThrowsDaoException() throws Exception {
        RunScript.execute(connectionFactory.getConnection(), new FileReader("src/test/resources/drop-table.sql"));
        assertThrows(DaoException.class, () -> {
            ticketsDao.update(new Ticket());
        });
    }

    @Test
    void deleteWithExistingIdReturns1() throws Exception {
        assertEquals(Integer.valueOf(1), ticketsDao.delete(2L));
    }

    @Test
    void deleteWithNotExistingReturnsZero() throws Exception {
        assertEquals(Integer.valueOf(0), ticketsDao.delete(3L));
    }

    @Test
    void deleteWhenTableNotExistsThrowsDaoException() throws Exception {
        RunScript.execute(connectionFactory.getConnection(), new FileReader("src/test/resources/drop-table.sql"));
        assertThrows(DaoException.class, () -> {
            ticketsDao.delete(ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE));
        });
    }
}
