package com.courses.airport.dao;

import com.courses.airport.connection.ConnectionFactory;
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

    private static final Airport AIRPORT = new Airport(3, "John", LocalDate.of(2018, 2, 20), "3","(012)345-67-89");
    private static ConnectionFactory connectionFactory;
    private static AirportDao airportDao;

    @BeforeEach
    void setUp() throws Exception {
        connectionFactory = ConnectionFactory.getInstance();
        airportDao = new AirportDao(connectionFactory);
        RunScript.execute(connectionFactory.getConnection(), new FileReader("src/test/resources/db-creation.sql"));
    }

    @Test
    void insertWithValidDataReturnsTrue() throws Exception {
        Airport AIRPORT = new Airport(5, "John", LocalDate.of(2018, 2, 20), "3","(012)345-67-89");
        Integer res = airportDao.insert(AIRPORT);
        assertEquals(Integer.valueOf(1), res);
    }

    @Test
    void insertWhenTableNotExistsThrowsDaoException() throws Exception {
        dropTableAirport();
        assertThrows(DaoException.class, () -> {
            airportDao.insert(AIRPORT);
        });
    }

    @Test
    void getByIdWithExistingIdReturnsCustomer() throws Exception {
        Airport actual = airportDao.findById(3L).orElse(null);
        assertEquals(AIRPORT, actual);
    }

    @Test
    void getByIdWithNotExistingIdReturnsOptionalEmpty() throws Exception {
        assertEquals(Optional.empty(), airportDao.findById(5L));
    }

    @Test
    void getByIdWhenTableNotExistsThrowsDaoException() throws Exception {
        dropTableAirport();
        long id = ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE);
        assertThrows(DaoException.class, () -> {
            airportDao.findById(id);
        });
    }

    @Test
    void getAllWhenTableHasDataReturnsListOfCustomers() throws Exception {
        Airport airport1 = new Airport(2, "John", LocalDate.of(2018, 2, 20), "2","(012)345-67-89");
        Airport airport2 = new Airport(3, "John", LocalDate.of(2018, 2, 20), "3","(012)345-67-89");
        List<Airport> expected = Arrays.asList(airport1, airport2);
        List<Airport> actual = airportDao.findAll();
        assertEquals(expected, actual);
    }

    @Test
    void getAllWhenTableHasNoDataReturnsEmptyList() throws Exception {
        Connection connection = connectionFactory.getConnection();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE AIRPORT");
        }
        assertEquals(Collections.emptyList(), airportDao.findAll());
    }

    @Test
    void getAllWhenTableNotExistsThrowsDaoException() throws Exception {
        dropTableAirport();
        assertThrows(DaoException.class, () -> {
            airportDao.findAll();
        });
    }

    @Test
    void updateWhenEntryExistsReturnsTrue() throws Exception {
        Airport updatedAirport = new Airport(2, "updateName",  LocalDate.of(2018, 2, 20), "terminal2", "phone2");
        assertEquals(Integer.valueOf(1), airportDao.update(updatedAirport));
    }

    @Test
    void updateWhenEntryNotExistsReturnsFalse() throws Exception {
        Airport updatedAirport = new Airport(5, "updateName", LocalDate.of(2018, 2, 20), "terminal3", "phone3");
        assertEquals(Integer.valueOf(0), airportDao.update(updatedAirport));
    }

    @Test
    void updateWhenTableNotExistsThrowsDaoException() throws Exception {
        dropTableAirport();
        assertThrows(DaoException.class, () -> {
            airportDao.update(new Airport());
        });
    }

    @Test
    void deleteWithExistingIdReturns1() throws Exception {
        assertEquals(Integer.valueOf(1), airportDao.removeById(2L));
    }

    @Test
    void deleteWithNotExistingReturns0() throws Exception {
        assertEquals(Integer.valueOf(0), airportDao.removeById(5L));
    }

    @Test
    void deleteWhenTableNotExistsThrowsDaoException() throws Exception {
        dropTableAirport();
        long id = ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE);
        assertThrows(DaoException.class, () -> {
            airportDao.removeById(id);
        });
    }

    private void dropTableAirport() throws SQLException {
        Connection connection = connectionFactory.getConnection();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE airport");
        }
    }
}
