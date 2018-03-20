package com.courses.tellus.integrationtests;

import com.courses.tellus.airport.config.jdbc.ConnectionFactory;
import com.courses.tellus.airport.dao.jdbc.AirportDao;
import com.courses.tellus.airport.exception.DaoException;
import com.courses.tellus.airport.model.Airport;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AirportDaoTest {

    private static final Airport AIRPORT = new Airport(1, "Borispil", LocalDate.of(1995, 2, 20), "D","(044)666-66-66");
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
        RunScript.execute(connectionFactory.getConnection(), new FileReader("src/test/resources/delete-raws-airport.sql"));
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
        Airport actual = airportDao.getById(1L).orElse(null);
        assertEquals(AIRPORT, actual);
    }

    @Test
    void getByIdWithNotExistingIdReturnsOptionalEmpty() throws Exception {
        assertEquals(Optional.empty(), airportDao.getById(5L));
    }

    @Test
    void getByIdWhenTableNotExistsThrowsDaoException() throws Exception {
        RunScript.execute(connectionFactory.getConnection(), new FileReader("src/test/resources/drop-table.sql"));
        assertThrows(DaoException.class, () -> {
            airportDao.getById(1L);
        });
    }

    @Test
    void getAllWhenTableHasDataReturnsListOfAirports() throws Exception {
        List<Airport> expected = Arrays.asList(AIRPORT);
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
        RunScript.execute(connectionFactory.getConnection(), new FileReader("src/test/resources/delete-raws-airport.sql"));
        assertEquals(Integer.valueOf(0), airportDao.update(AIRPORT));
    }

    @Test
    void updateWhenTableNotExistsThrowsDaoException() throws Exception {
        RunScript.execute(connectionFactory.getConnection(), new FileReader("src/test/resources/drop-table.sql"));
        assertThrows(DaoException.class, () -> {
            airportDao.update(new Airport());
        });
    }

    @Test
    void deleteWithExistingIdReturnsOne() throws Exception {
        assertEquals(Integer.valueOf(1), airportDao.delete(1L));
    }

    @Test
    void deleteWithNotExistingReturnsZero() throws Exception {
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
