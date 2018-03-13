package com.courses.tellus.autosalon.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import com.courses.tellus.autosalon.config.ConnectionFactory;
import com.courses.tellus.autosalon.exception.DaoException;
import com.courses.tellus.autosalon.model.Customer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CustomerDaoUnitTest {
    private static final Customer REAL_CUSTOMER =
            new Customer(1, "John", "Smith", LocalDate.of(2018, 2, 20), "(012)345-67-89", 10000.50);

    @Mock
    private Connection connectionMock;
    @Mock
    private Customer customerMock;
    @Mock
    private Statement statementMock;
    @Mock
    private ResultSet resultSetMock;
    private CustomerDao customerDao;

    @BeforeAll
    static void disableWarning() {
        System.err.close();
    }

    @BeforeEach
    void initMocks() throws Exception {
        MockitoAnnotations.initMocks(this);
        ConnectionFactory connectionFactoryMock = mock(ConnectionFactory.class);
        when(connectionFactoryMock.getConnection()).thenReturn(connectionMock);
        customerDao = new CustomerDao(connectionFactoryMock);
    }

    @Test
    void insertWhenExecuteUpdatePositiveDataReturnsTrue() throws Exception {
        PreparedStatement preparedStatementMock = mock(PreparedStatement.class);
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeUpdate()).thenReturn(1);
        assertEquals(Integer.valueOf(1), customerDao.insert(REAL_CUSTOMER));
    }

    @Test
    void insertWhenBadConnectionThrowsDAOException() throws Exception {
        when(connectionMock.prepareStatement(anyString())).thenThrow(SQLException.class);
        assertThrows(DaoException.class, () -> customerDao.insert(customerMock));
    }

    @Test
    void getByIdWithExistingIdReturnsCustomer() throws Exception {
        when(connectionMock.createStatement()).thenReturn(statementMock);
        when(statementMock.executeQuery(anyString())).thenReturn(resultSetMock);
        when(resultSetMock.next()).thenReturn(true);
        putRealCustomerIntoResulsetMock();
        Customer result = customerDao.getById(REAL_CUSTOMER.getId()).orElse(null);
        assertEquals(REAL_CUSTOMER, result);
    }

    @Test
    void getByIdWhenEmptyResultSetReturnsOptionalEmpty() throws Exception {
        ResultSet resultSetMock = mock(ResultSet.class);
        when(connectionMock.createStatement()).thenReturn(statementMock);
        when(statementMock.executeQuery(anyString())).thenReturn(resultSetMock);
        when(resultSetMock.next()).thenReturn(false);
        assertEquals(Optional.empty(), customerDao.getById(1L));
    }

    @Test
    void getByIdWhenBadConnectionThrowsDaoException() throws Exception {
        when(connectionMock.createStatement()).thenThrow(SQLException.class);
        Long id = ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE);
        assertThrows(DaoException.class, () -> customerDao.getById(id));
    }

    @Test
    void getAllWhenEntryExistsReturnsListWithCustomer() throws Exception {
        when(connectionMock.createStatement()).thenReturn(statementMock);
        when(statementMock.executeQuery(anyString())).thenReturn(resultSetMock);
        when(resultSetMock.next()).thenReturn(true).thenReturn(false);
        putRealCustomerIntoResulsetMock();
        List<Customer> expected = Collections.singletonList(REAL_CUSTOMER);
        assertEquals(expected, customerDao.getAll());
    }

    @Test
    void getAllWhenBadConnectionThrowsDaoException() throws Exception {
        when(connectionMock.createStatement()).thenThrow(SQLException.class);
        assertThrows(DaoException.class, () -> customerDao.getAll());
    }

    @Test
    void updateWhenEntryExistsReturns1() throws Exception {
        PreparedStatement preparedStatementMock = mock(PreparedStatement.class);
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeUpdate()).thenReturn(1);
        when(customerMock.getDateOfBirth()).thenReturn(LocalDate.now());
        assertEquals(Integer.valueOf(1), customerDao.update(customerMock));
    }

    @Test
    void updateWhenEntryNotExistsReturns0() throws Exception {
        PreparedStatement preparedStatementMock = mock(PreparedStatement.class);
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeUpdate()).thenReturn(0);
        when(customerMock.getDateOfBirth()).thenReturn(LocalDate.now());
        assertEquals(Integer.valueOf(0), customerDao.update(customerMock));
    }

    @Test
    void updateWhenBadConnectionThrowsDaoException() throws Exception {
        when(connectionMock.prepareStatement(anyString())).thenThrow(SQLException.class);
        assertThrows(DaoException.class, () -> customerDao.update(customerMock));
    }

    @Test
    void deleteWhenWhenEntryExistsReturns1() throws Exception {
        when(connectionMock.createStatement()).thenReturn(statementMock);
        when(statementMock.executeUpdate(anyString())).thenReturn(1);
        assertEquals(Integer.valueOf(1), customerDao.delete(1L));
    }

    @Test
    void deleteWhenWhenEntryNotExistsReturns0() throws Exception {
        when(connectionMock.createStatement()).thenReturn(statementMock);
        when(statementMock.executeUpdate(anyString())).thenReturn(0);
        assertEquals(Integer.valueOf(0), customerDao.delete(1L));
    }

    @Test
    void deleteWhenBadConnectionThrowsDaoException() throws Exception {
        when(connectionMock.createStatement()).thenThrow(SQLException.class);
        assertThrows(DaoException.class, () -> customerDao.delete(1L));
    }

    private void putRealCustomerIntoResulsetMock() throws SQLException {
        when(resultSetMock.getLong("id")).thenReturn(REAL_CUSTOMER.getId());
        when(resultSetMock.getString("name")).thenReturn(REAL_CUSTOMER.getName());
        when(resultSetMock.getString("surname")).thenReturn(REAL_CUSTOMER.getSurname());
        Date date = Date.valueOf(REAL_CUSTOMER.getDateOfBirth());
        when(resultSetMock.getDate("date_of_birth")).thenReturn(date);
        when(resultSetMock.getString("phone_number")).thenReturn(REAL_CUSTOMER.getPhoneNumber());
        when(resultSetMock.getDouble("available_fund")).thenReturn(REAL_CUSTOMER.getAvailableFunds());
    }
}