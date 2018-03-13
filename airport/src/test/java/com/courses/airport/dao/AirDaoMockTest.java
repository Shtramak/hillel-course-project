package com.courses.airport.dao;

import com.courses.airport.connection.ConnectionFactory;
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


public class AirDaoMockTest {
    private static final Airport AIRPORT_TRUE
            = new Airport(1L, "Borispol",  LocalDate.of(2018, 2, 20),"D-3", "(012)345-67-89");
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
    private AirportDao airportDao;



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
    void newCustomerWhenConnectionFailedThrowsDaoException() throws Exception {
        when(connectionFactoryMock.getConnection()).thenThrow(SQLException.class);
        assertThrows(DaoException.class, () -> new AirportDao(connectionFactoryMock));
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
    void getByIdWithExistingIdReturnsCustomer() throws Exception {
        when(connectionMock.createStatement()).thenReturn(statementMock);
        when(statementMock.executeQuery(anyString())).thenReturn(resultSetMock);
        when(resultSetMock.next()).thenReturn(true);
        putRealCustomerIntoResulsetMock();
        Airport result = airportDao.findById(AIRPORT_TRUE.getAirportId()).orElse(null);
        assertEquals(AIRPORT_TRUE, result);
    }

    @Test
    void getByIdWhenEmptyResultSetReturnsOptionalEmpty() throws Exception {
        when(connectionMock.createStatement()).thenReturn(statementMock);
        when(statementMock.executeQuery(anyString())).thenReturn(resultSetMock);
        when(resultSetMock.next()).thenReturn(false);
        Long id = ThreadLocalRandom.current().nextLong();
        assertEquals(Optional.empty(), airportDao.findById(id));
    }

    @Test
    void getByIdWhenBadConnectionThrowsDaoException() throws Exception {
        when(connectionMock.createStatement()).thenThrow(SQLException.class);
        Long id = ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE);
        assertThrows(DaoException.class, () -> airportDao.findById(id));
    }

    @Test
    void getAllWhenEntryExistsReturnsListWithCustomer() throws Exception {
        when(connectionMock.createStatement()).thenReturn(statementMock);
        when(statementMock.executeQuery(anyString())).thenReturn(resultSetMock);
        when(resultSetMock.next()).thenReturn(true).thenReturn(false);
        putRealCustomerIntoResulsetMock();
        List<Airport> expected = Collections.singletonList(AIRPORT_TRUE);
        assertEquals(expected, airportDao.findAll());
    }

    @Test
    void getAllWhenBadConnectionThrowsDaoException() throws Exception {
        when(connectionMock.createStatement()).thenThrow(SQLException.class);
        assertThrows(DaoException.class, () -> airportDao.findAll());
    }

    @Test
    void updateWhenEntryExistsReturns1() throws Exception {
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeUpdate()).thenReturn(1);
        when(airportMock.getDateOfBirth()).thenReturn(LocalDate.now());
        assertEquals(Integer.valueOf(1), airportDao.update(airportMock));
    }

    @Test
    void updateWhenEntryNotExistsReturns0() throws Exception {
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
    void deleteWhenWhenEntryExistsReturns1() throws Exception {
        when(connectionMock.createStatement()).thenReturn(statementMock);
        when(statementMock.executeUpdate(anyString())).thenReturn(1);
        Long id = ThreadLocalRandom.current().nextLong();
        assertEquals(Integer.valueOf(1), airportDao.removeById(id));
    }

    @Test
    void deleteWhenWhenEntryNotExistsReturns0() throws Exception {
        when(connectionMock.createStatement()).thenReturn(statementMock);
        when(statementMock.executeUpdate(anyString())).thenReturn(0);
        Long id = ThreadLocalRandom.current().nextLong();
        assertEquals(Integer.valueOf(0), airportDao.removeById(id));
    }

    @Test
    void deleteWhenBadConnectionThrowsDaoException() throws Exception {
        when(connectionMock.createStatement()).thenThrow(SQLException.class);
        assertThrows(DaoException.class, () -> airportDao.removeById(1L));
    }

    private void putRealCustomerIntoResulsetMock() throws SQLException {
        when(resultSetMock.getLong("id")).thenReturn(AIRPORT_TRUE.getAirportId());
        when(resultSetMock.getString("name")).thenReturn(AIRPORT_TRUE.getNameAirport());
        Date date = Date.valueOf(AIRPORT_TRUE.getDateOfBirth());
        when(resultSetMock.getDate("date_of_birth")).thenReturn(date);
        when(resultSetMock.getString("phone_number")).thenReturn(AIRPORT_TRUE.getTelephone());
        when(resultSetMock.getString("terminal")).thenReturn(AIRPORT_TRUE.getNumberTerminal());
    }
}
