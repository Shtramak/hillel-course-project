package com.courses.tellus.airport.config.jdbc;

import com.courses.tellus.airport.config.jdbc.ConnectionFactory;
import com.courses.tellus.airport.dao.jdbc.TicketsDao;
import com.courses.tellus.airport.exception.DaoException;
import com.courses.tellus.airport.model.Ticket;
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
