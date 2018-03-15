package com.courses.tellus.integrationtests;

import com.courses.airport.connection.ConnectionFactory;
import com.courses.airport.dao.AirportDao;
import com.courses.airport.exception.DaoException;
import com.courses.airport.model.Airport;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AirportDaoTest {

    private static final Airport AIRPORT = new Airport(3, "Borispil", LocalDate.of(1995, 2, 20), "D","(044)666-66-66");
    private static ConnectionFactory connectionFactory;
    private AirportDao airportDao;

    @BeforeEach
    void setUp() throws Exception {
        connectionFactory = ConnectionFactory.getInstance();
        airportDao = new AirportDao(connectionFactory);
        RunScript.execute(connectionFactory.getConnection(), new FileReader("src/test/resources/airport_db.sql"));
    }

    @Test
    void insertWithValidDataReturnsTrue() throws Exception {
        Airport AIRPORT = new Airport(4, "Borispil", LocalDate.of(1995, 2, 20), "D","(044)666-66-66");
        assertEquals(Integer.valueOf(1), airportDao.insert(AIRPORT));
    }

    @Test
    void insertWhenTableNotExistsThrowsDaoException() throws Exception {
        RunScript.execute(connectionFactory.getConnection(), new FileReader("src/test/resources/drop-table.sql"));
        assertThrows(DaoException.class, () -> {
            airportDao.insert(AIRPORT);
        });
    }

    @Test
    void getByIdWithExistingIdReturnsAirport() throws Exception {
        Airport actual = airportDao.getById(3L).orElse(null);
        assertEquals(AIRPORT, actual);
    }

    @Test
    void getByIdWithNotExistingIdReturnsOptionalEmpty() throws Exception {
        assertEquals(Optional.empty(), airportDao.getById(5L));
    }

    @Test
    void getByIdWhenTableNotExistsThrowsDaoException() throws Exception {
        RunScript.execute(connectionFactory.getConnection(), new FileReader("src/test/resources/drop-table.sql"));
        long id = ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE);
        assertThrows(DaoException.class, () -> {
            airportDao.getById(id);
        });
    }

    @Test
    void getAllWhenTableHasDataReturnsListOfAirports() throws Exception {
        List<Airport> expected = Arrays.asList(new Airport(2, "Zhuliani", LocalDate.of(2012, 1, 01), "A","(044)333-33-33"), AIRPORT);
        List<Airport> actual = airportDao.getAll();
        assertEquals(expected, actual);
    }

    @Test
    void getAllWhenTableHasNoDataReturnsEmptyList() throws Exception {
        Connection connection = connectionFactory.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("TRUNCATE TABLE AIRPORT");
        assertEquals(Collections.emptyList(), airportDao.getAll());
    }

    @Test
    void getAllWhenTableNotExistsThrowsDaoException() throws Exception {
        RunScript.execute(connectionFactory.getConnection(), new FileReader("src/test/resources/drop-table.sql"));
        assertThrows(DaoException.class, () -> {
            airportDao.getAll();
        });
    }

    @Test
    void updateWhenEntryExistsReturnsTrue() throws Exception {
        assertEquals(Integer.valueOf(1), airportDao.update(AIRPORT));
    }

    @Test
    void updateWhenEntryNotExistsReturnsFalse() throws Exception {
        Airport updatedAirport = new Airport(5, "updateTerminalName", LocalDate.of(2018, 2, 20), "terminalNumber", "phone");
        assertEquals(Integer.valueOf(0), airportDao.update(updatedAirport));
    }

    @Test
    void updateWhenTableNotExistsThrowsDaoException() throws Exception {
        RunScript.execute(connectionFactory.getConnection(), new FileReader("src/test/resources/drop-table.sql"));
        assertThrows(DaoException.class, () -> {
            airportDao.update(new Airport());
        });
    }

    @Test
    void deleteWithExistingIdReturns1() throws Exception {
        assertEquals(Integer.valueOf(1), airportDao.delete(2L));
    }

    @Test
    void deleteWithNotExistingReturns0() throws Exception {
        assertEquals(Integer.valueOf(0), airportDao.delete(5L));
    }

    @Test
    void deleteWhenTableNotExistsThrowsDaoException() throws Exception {
        RunScript.execute(connectionFactory.getConnection(), new FileReader("src/test/resources/drop-table.sql"));
        assertThrows(DaoException.class, () -> {
            airportDao.delete(1L);
        });
    }
}
