package com.courses.airport.dao;

import com.courses.airport.connection.jdbc.ConnectionFactory;
import com.courses.airport.dao.jdbc.AirportDao;
import com.courses.airport.exception.DaoException;
import com.courses.airport.model.Airport;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

class AirDaoMockTest {
    private final Airport AIRPORT_TRUE = new Airport(1L, "Borispol",  LocalDate.of(2018, 2, 20),"D-3", "(012)345-67-89");
    private AirportDao airportDao;

    @Mock
    private ConnectionFactory connectionFactoryMock;
    @Mock
    private Connection connectionMock;
    @Mock
    private PreparedStatement preparedStatementMock;
    @Mock
    private Statement statementMock;
    @Mock
    private ResultSet resultSetMock;
    @Mock
    private Airport airportMock;

    @BeforeAll
    static void disableWarning() {
        System.err.close();
    }

    @BeforeEach
    void initMocks() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(connectionFactoryMock.getConnection()).thenReturn(connectionMock);
        airportDao = new AirportDao(connectionFactoryMock);
    }

    @Test
    void insertWhenExecuteUpdatePositiveDataReturnsTrue() throws Exception {
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeUpdate()).thenReturn(1);
        assertEquals(Integer.valueOf(1), airportDao.insert(AIRPORT_TRUE));
    }

    @Test
    void insertWhenBadConnectionThrowsDAOException() throws Exception {
        when(connectionMock.prepareStatement(anyString())).thenThrow(SQLException.class);
        assertThrows(DaoException.class, () -> airportDao.insert(airportMock));
    }

    @Test
    void getByIdWithExistingIdReturnsAirport() throws Exception {
        returnMockResultSet();
        when(resultSetMock.next()).thenReturn(true);
        putRealAirportIntoResulsetMock();
        Airport result = airportDao.getById(AIRPORT_TRUE.getAirportId()).orElse(null);
        assertEquals(AIRPORT_TRUE, result);
    }

    @Test
    void getByIdWhenEmptyResultSetReturnsOptionalEmpty() throws Exception {
        returnMockResultSet();
        when(resultSetMock.next()).thenReturn(false);
        Long id = ThreadLocalRandom.current().nextLong();
        assertEquals(Optional.empty(), airportDao.getById(id));
    }

    @Test
    void getByIdWhenBadConnectionThrowsDaoException() throws Exception {
        when(connectionMock.createStatement()).thenThrow(SQLException.class);
        assertThrows(DaoException.class, () -> airportDao.getById(1L));
    }

    @Test
    void getAllWhenEntryExistsReturnsListWithAirports() throws Exception {
        returnMockResultSet();
        when(resultSetMock.next()).thenReturn(true).thenReturn(false);
        putRealAirportIntoResulsetMock();
        List<Airport> expected = Collections.singletonList(AIRPORT_TRUE);
        assertEquals(expected, airportDao.getAll());
    }

    @Test
    void getAllWhenBadConnectionThrowsDaoException() throws Exception {
        when(connectionMock.createStatement()).thenThrow(SQLException.class);
        assertThrows(DaoException.class, () -> airportDao.getAll());
    }

    @Test
    void updateWhenEntryExistsReturnsOne() throws Exception {
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeUpdate()).thenReturn(1);
        when(airportMock.getDateOfBirth()).thenReturn(LocalDate.now());
        assertEquals(Integer.valueOf(1), airportDao.update(airportMock));
    }

    @Test
    void updateWhenEntryNotExistsReturnsZero() throws Exception {
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeUpdate()).thenReturn(0);
        when(airportMock.getDateOfBirth()).thenReturn(LocalDate.now());
        assertEquals(Integer.valueOf(0), airportDao.update(airportMock));
    }

    @Test
    void updateWhenBadConnectionThrowsDaoException() throws Exception {
        when(connectionMock.prepareStatement(anyString())).thenThrow(SQLException.class);
        assertThrows(DaoException.class, () -> airportDao.update(airportMock));
    }

    @Test
    void deleteWhenWhenEntryExistsReturnsOne() throws Exception {
        when(connectionMock.createStatement()).thenReturn(statementMock);
        when(statementMock.executeUpdate(anyString())).thenReturn(1);
        assertEquals(Integer.valueOf(1), airportDao.delete(1L));
    }

    @Test
    void deleteWhenWhenEntryNotExistsReturnsZero() throws Exception {
        when(connectionMock.createStatement()).thenReturn(statementMock);
        when(statementMock.executeUpdate(anyString())).thenReturn(0);
        assertEquals(Integer.valueOf(0), airportDao.delete(1L));
    }

    @Test
    void deleteWhenBadConnectionThrowsDaoException() throws Exception {
        when(connectionMock.createStatement()).thenThrow(SQLException.class);
        assertThrows(DaoException.class, () -> airportDao.delete(1L));
    }

    private void returnMockResultSet() throws SQLException {
        when(connectionMock.createStatement()).thenReturn(statementMock);
        when(statementMock.executeQuery(anyString())).thenReturn(resultSetMock);
    }

    private void putRealAirportIntoResulsetMock() throws SQLException {
        when(resultSetMock.getLong("id")).thenReturn(AIRPORT_TRUE.getAirportId());
        when(resultSetMock.getString("name")).thenReturn(AIRPORT_TRUE.getNameAirport());
        Date date = Date.valueOf(AIRPORT_TRUE.getDateOfBirth());
        when(resultSetMock.getDate("date_of_birth")).thenReturn(date);
        when(resultSetMock.getString("phone_number")).thenReturn(AIRPORT_TRUE.getTelephone());
        when(resultSetMock.getString("terminal")).thenReturn(AIRPORT_TRUE.getNumberTerminal());
    }
}