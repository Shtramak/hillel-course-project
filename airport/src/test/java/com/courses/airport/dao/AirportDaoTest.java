package com.courses.airport.dao;

import com.courses.airport.connection.ConnectionFactory;
import com.courses.airport.model.Airport;
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

public class AirportDaoTest {

    private static Connection connection;
    private static AirportDao airportDao;

    @BeforeEach
    public void setUp() throws IOException, SQLException {
        connection = ConnectionFactory.getInstance().getConnection();
        RunScript.execute(connection, new FileReader("src/test/resources/db-creation.sql"));
        airportDao = new AirportDao(connection);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        executeSqlQuery("DROP TABLE airport");
        executeSqlQuery("DROP TABLE airport_tickets");
    }

    @Test
    public void insertWithValidDataReturnsTrue() throws DaoException {
        Airport airport = new Airport(1,"Borispol",LocalDate.of(2018,3,12),"D-3","0800-543-7645");
        assertEquals(1, airportDao.insert(airport));
    }

    @Test
    public void insertWhenAirportHasNullElementThrowsDAOException() throws DaoException {
        Airport airport = new Airport(1,null, LocalDate.of(2018,3,12),"D-3","0800-543-7645");
        assertThrows(DaoException.class, () -> {
            airportDao.insert(airport);
        });
    }

    @Test
    public void insertWhenAirportIsNullThrowsDaoException() throws DaoException {
        Throwable exception = assertThrows(DaoException.class, () -> {
            airportDao.insert(null);
        });
        assertEquals("Airport must be not null!", exception.getMessage());
    }

    @Test
    public void findByIdWithExistingIdReturnsAirport() throws DaoException {
        insertAirportToDb(3);
        Airport expected = new Airport(1L,"name2",LocalDate.of(2018,2,20),"terminal2","phoneNumber2");
        assertEquals(expected, 1L);
    }

    @Test
    public void findByIdWithNotExistingIdReturnsNull() throws DaoException {
        insertAirportToDb(1);
        assertEquals(null, airportDao.findById(2L));
    }

    @Test
    public void findByIdWithNegativeIdThrowsDaoException() throws DaoException {
        Throwable exception = assertThrows(DaoException.class, () -> {
            airportDao.findById(-1L);
        });
        assertEquals("Airport id must be positive, but entered: -1", exception.getMessage());
    }

    @Test
    public void findAllWhenTableHasDataReturnsListOfAirport() throws DaoException {
        insertAirportToDb(2);
        Airport airport = new Airport(1,"name1",LocalDate.of(2018,2,20),"terminal1","phoneNumber1");
        Airport airport2 = new Airport(2,"name2",LocalDate.of(2018,2,20),"terminal2","phoneNumber2");
        List<Airport> expected = Arrays.asList(airport,airport2);
        List<Airport> actual = airportDao.findAll(1L);
        assertEquals(expected, actual);
    }

    @Test
    public void findAllWhenTableHasNoDataReturnsEmptyList() throws DaoException {
        assertEquals(Collections.emptyList(), airportDao.findAll(1L));
    }

    @Test
    public void updateWhenEntryExistsReturnsTrue() throws DaoException {
        insertAirportToDb(3);
        Airport updatedAirport = new Airport(2,"name",LocalDate.of(2018,3,12),"terminal","telephone");
        assertEquals(3, airportDao.update(updatedAirport));
    }

    @Test
    public void updateWhenEntryNotExistsReturnsFalse() throws DaoException {
        insertAirportToDb(2);
        Airport updatedAirport = new Airport(3,"name",LocalDate.of(2018,3,12),"terminal","telephone");
        assertEquals(2,airportDao.update(updatedAirport));
    }

    @Test
    public void updateWhenAirportIsNullThrowsDaoException() throws DaoException {
        Throwable exception = assertThrows(DaoException.class, () -> {
            airportDao.update(null);
        });
        assertEquals("Airport must be not null!", exception.getMessage());
    }

    @Test
    public void removeByIdWithExistingIdReturnsTrue() throws DaoException {
        insertAirportToDb(3);
        assertEquals(1L, airportDao.removeById(1L));
    }

    @Test
    public void removeByIdWithNotExistingReturnsFalse() throws DaoException {
        insertAirportToDb(1);
        assertEquals(1,airportDao.removeById(1L));
    }

    @Test
    public void removeByIdWithNegativeIdThrowsDaoException() throws DaoException {
        Throwable exception = assertThrows(DaoException.class, () -> {
            airportDao.removeById(-1L);
        });
        assertEquals("Airport id must be positive, but entered: -1", exception.getMessage());
    }

    private void executeSqlQuery(String sqlQuery) throws SQLException {
        Objects.requireNonNull(connection);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlQuery);
        }
    }

    private void insertAirportToDb(int numberOfAirport) {
        String sql = "INSERT INTO" +
                " airport (id, name, date_of_birth, phone_number, terminal)" +
                " VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int id = 1; id <= numberOfAirport; id++) {
                statement.setLong(1, id);
                statement.setString(2, "name" + id);
                statement.setDate(3, Date.valueOf(LocalDate.of(2018, 2, 20)));
                statement.setString(4, "phoneNumber" + id);
                statement.setString(5, "terminal" + id);
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
