package com.courses.airport.connection;

import com.courses.airport.connection.jdbc.ConnectionFactory;
import com.courses.airport.dao.TicketsDao;
import com.courses.airport.exception.DaoException;
import com.courses.airport.model.Ticket;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.time.LocalDate;

/**
 * Connection Factory test.
 */
public class ConnectionFactoryTest {
    private static TicketsDao ticketsDao;

    @BeforeAll
    static void init() throws Exception {
        RunScript.execute(ConnectionFactory.getInstance().getConnection(),
                new FileReader("src/test/resources/tickettablecreation.sql"));
        ticketsDao = new TicketsDao(ConnectionFactory.getInstance());
    }

    @Test
    void tesDataBaseConnectionSuccess() throws DaoException {
        Assertions.assertEquals(Integer.valueOf(1), ticketsDao.insert(new Ticket(1, "Yurii", "Dud", LocalDate.of(2018, 1, 1), "Moscow")));
    }
}
