package com.courses.airport.dao;

import com.courses.airport.model.Airport;
import com.courses.airport.exception.DaoException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AirDaoMockTest {
    private Connection connection;
    private AirportDao airportDao;
    private Airport airport;
    private ResultSet mockResultSet;
    private PreparedStatement mockPreState;

    @BeforeAll
    public static void disableWarning() {
        System.err.close();
        System.setErr(System.out);
    }

    @BeforeEach
    public void setUp() {
        connection = mock(Connection.class);
        airportDao = new AirportDao(connection);
    }

    @Test
    public void insertWhenExecuteUpdatePositiveDataReturnsTrue() throws DaoException, SQLException {
        Airport airport = new Airport(1L, "Borispol",  LocalDate.of(2018, 2, 20),"D-3", "(012)345-67-89");
        PreparedStatement statement = mock(PreparedStatement.class);
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeUpdate()).thenReturn(1);
        assertEquals(1L, airportDao.insert(airport));
    }
    @Test
    public void insertWhenExecuteUpdateNegativeDataReturnsTrue() throws DaoException, SQLException {
        Airport airport = new Airport(1L, "Borispol",  LocalDate.of(2018, 2, 20),"D-3", "(012)345-67-89");
        PreparedStatement statement = mock(PreparedStatement.class);
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeUpdate()).thenReturn(-1);
        assertEquals(-1L, airportDao.insert(airport));
    }

    @Test
    public void insertWhenBadConnectionThrowsDAOException() throws DaoException, SQLException {
        Airport airport = mock(Airport.class);
        when(connection.prepareStatement(anyString())).thenThrow(SQLException.class);
        assertThrows(DaoException.class, () -> airportDao.insert(airport));
    }

    @Test
    public void findByIdWithExistingIdReturnsAirport() throws DaoException, SQLException {
        Statement statement = mock(Statement.class);
        ResultSet resultSet = mock(ResultSet.class);
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        Airport airport = new Airport(1, "Borispol",  LocalDate.of(2018, 2, 20),"D-3", "(012)345-67-89");
        when(resultSet.getLong("id")).thenReturn(airport.getAirportId());
        when(resultSet.getString("name")).thenReturn(airport.getNameAirport());
        Date date = Date.valueOf(airport.getDateOfBirth());
        when(resultSet.getDate("date_of_birth")).thenReturn(date);
        when(resultSet.getString("terminal")).thenReturn(airport.getNumberTerminal());
        when(resultSet.getString("phone_number")).thenReturn(airport.getTelephone());
        assertEquals(airport, airportDao.findById(airport.getAirportId()));
    }

    @Test
    public void findByIdWhenEmptyResultSetReturnsNull() throws DaoException, SQLException {
        mockPreState.setLong(1, 1);
        when(mockPreState.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);
        when(mockResultSet.getLong("airport_id")).thenReturn(airport.getAirportId());
        when(mockResultSet.getString("name_of_airport")).thenReturn(airport.getNameAirport());
        when(mockResultSet.getString("telephone")).thenReturn(airport.getTelephone());
        when(mockResultSet.getString("terminal")).thenReturn(airport.getNumberTerminal());
        assertNull(airportDao.findById(airport.getAirportId()));
    }

     @Test
    public void findByIdWhenBadConnectionThrowsDaoException() throws DaoException, SQLException {
        when(connection.createStatement()).thenThrow(SQLException.class);
        assertThrows(DaoException.class, () -> airportDao.findById(1L));
    }

    @Test
    public void findAllWhenBadConnectionThrowsDaoException() throws DaoException, SQLException {
        when(connection.createStatement()).thenThrow(SQLException.class);
        assertThrows(DaoException.class, () -> airportDao.findAll(1L));
    }

    @Test
    public void updateWhenBadConnectionThrowsDaoException() throws DaoException, SQLException {
        Airport airport = mock(Airport.class);
        when(connection.prepareStatement(anyString())).thenThrow(SQLException.class);
        assertThrows(DaoException.class, () -> airportDao.update(airport));
    }

      @Test
    public void removeByIdWhenBadConnectionThrowsDaoException() throws DaoException, SQLException {
        when(connection.createStatement()).thenThrow(SQLException.class);
        assertThrows(DaoException.class, () -> airportDao.removeById(1L));
    }
}
