package com.courses.tellus.autosalon.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.anyString;
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
    @Mock
    ConnectionFactory connectionFactoryMock;
    @Mock
    private Connection connectionMock;
    @Mock
    private PreparedStatement preparedStatementMock;
    @Mock
    private Customer customerMock;
    @Mock
    private Statement statementMock;
    @Mock
    private ResultSet resultSetMock;
    private CustomerDao customerDao;
    private Customer realCustomer;

    @BeforeAll
    static void disableWarning() {
        System.err.close();
        System.setErr(System.out);
    }

    @BeforeEach
    void initMocks() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(connectionFactoryMock.getConnection()).thenReturn(connectionMock);
        customerDao = new CustomerDao(connectionFactoryMock);
        realCustomer = new Customer(1, "John", "Smith", LocalDate.of(2018, 2, 20), "(012)345-67-89", 10000.50);
    }

    @Test
    void newCustomerWhenConnectionFailedThrowsDaoException() throws Exception {
        when(connectionFactoryMock.getConnection()).thenThrow(SQLException.class);
        assertThrows(DaoException.class, () -> new CustomerDao(connectionFactoryMock));
    }

    @Test
    void insertWhenExecuteUpdatePositiveDataReturnsTrue() throws Exception {
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeUpdate()).thenReturn(1);
        assertTrue(customerDao.insert(realCustomer));
    }

    @Test
    void insertWhenExecuteUpdateNegativeDataReturnsTrue() throws Exception {
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeUpdate()).thenReturn(-1);
        assertFalse(customerDao.insert(realCustomer));
    }

    @Test
    void insertWhenBadConnectionThrowsDAOException() throws Exception {
        when(connectionMock.prepareStatement(anyString())).thenThrow(SQLException.class);
        assertThrows(DaoException.class, () -> customerDao.insert(customerMock));
    }

    @Test
    void insertWhenCustomerIsNullThrowsDAOException() {
        Throwable exception = assertThrows(DaoException.class, () -> {
            customerDao.insert(null);
        });
        assertEquals("Customer must be not null!", exception.getMessage());
    }

    @Test
    void findByIdWithExistingIdReturnsCustomer() throws Exception {
        when(connectionMock.createStatement()).thenReturn(statementMock);
        when(statementMock.executeQuery(anyString())).thenReturn(resultSetMock);
        when(resultSetMock.next()).thenReturn(true);
        putRealCustomerIntoResulSetMock();
        assertEquals(realCustomer, customerDao.findById(realCustomer.getId()));
    }

    @Test
    void findByIdWhenEmptyResultSetReturnsNull() throws Exception {
        when(connectionMock.createStatement()).thenReturn(statementMock);
        when(statementMock.executeQuery(anyString())).thenReturn(resultSetMock);
        when(resultSetMock.next()).thenReturn(false);
        Long id = ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE);
        assertNull(customerDao.findById(id));
    }

    @Test
    void findByIdWithNegativeIdThrowsDaoException() throws Exception {
        Long id = ThreadLocalRandom.current().nextLong(Long.MIN_VALUE, -1);
        Throwable exception = assertThrows(DaoException.class, () -> {
            customerDao.findById(id);
        });
        assertEquals("Customer id must be positive, but entered: " + id, exception.getMessage());
    }

    @Test
    void findByIdWhenBadConnectionThrowsDaoException() throws Exception {
        when(connectionMock.createStatement()).thenThrow(SQLException.class);
        Long id = ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE);
        assertThrows(DaoException.class, () -> customerDao.findById(id));
    }

    @Test
    void findAllWhenEntryExistsReturnsListWithCustomer() throws Exception {
        when(connectionMock.createStatement()).thenReturn(statementMock);
        when(statementMock.executeQuery(anyString())).thenReturn(resultSetMock);
        when(resultSetMock.next()).thenReturn(true).thenReturn(false);
        putRealCustomerIntoResulSetMock();
        List<Customer> expected = Collections.singletonList(realCustomer);
        assertEquals(expected, customerDao.findAll());
    }

    @Test
    void findAllWhenBadConnectionThrowsDaoException() throws Exception {
        when(connectionMock.createStatement()).thenThrow(SQLException.class);
        assertThrows(DaoException.class, () -> customerDao.findAll());
    }

    @Test
    void updateWhenEntryExistsReturnsTrue() throws Exception {
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeUpdate()).thenReturn(1);
        when(customerMock.getDateOfBirth()).thenReturn(LocalDate.now());
        assertTrue(customerDao.update(customerMock));
    }

    @Test
    void updateWhenEntryNotExistsReturnsFalse() throws Exception {
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeUpdate()).thenReturn(0);
        when(customerMock.getDateOfBirth()).thenReturn(LocalDate.now());
        assertFalse(customerDao.update(customerMock));
    }

    @Test
    void updateWhenCustomerIsNullThrowsDAOException() {
        Throwable exception = assertThrows(DaoException.class, () -> {
            customerDao.update(null);
        });
        assertEquals("Customer must be not null!", exception.getMessage());
    }

    @Test
    void updateWhenBadConnectionThrowsDaoException() throws Exception {
        when(connectionMock.prepareStatement(anyString())).thenThrow(SQLException.class);
        assertThrows(DaoException.class, () -> customerDao.update(customerMock));
    }

    @Test
    void removeByIdWhenWhenEntryExistsReturnsTrue() throws Exception {
        when(connectionMock.createStatement()).thenReturn(statementMock);
        when(statementMock.executeUpdate(anyString())).thenReturn(1);
        Long id = ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE);
        assertTrue(customerDao.removeById(id));
    }

    @Test
    void removeByIdWhenWhenEntryNotExistsReturnsFalse() throws Exception {
        when(connectionMock.createStatement()).thenReturn(statementMock);
        when(statementMock.executeUpdate(anyString())).thenReturn(0);
        Long id = ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE);
        assertFalse(customerDao.removeById(id));
    }

    @Test
    void removeByIdWithNegativeIdThrowsDaoException() throws Exception {
        Long id = ThreadLocalRandom.current().nextLong(Long.MIN_VALUE, -1);
        Throwable exception = assertThrows(DaoException.class, () -> {
            customerDao.removeById(id);
        });
        assertEquals("Customer id must be positive, but entered: " + id, exception.getMessage());
    }

    @Test
    void removeByIdWhenBadConnectionThrowsDaoException() throws Exception {
        when(connectionMock.createStatement()).thenThrow(SQLException.class);
        assertThrows(DaoException.class, () -> customerDao.removeById(1L));
    }

    private void putRealCustomerIntoResulSetMock() throws SQLException {
        when(resultSetMock.getLong("id")).thenReturn(realCustomer.getId());
        when(resultSetMock.getString("name")).thenReturn(realCustomer.getName());
        when(resultSetMock.getString("surname")).thenReturn(realCustomer.getSurname());
        Date date = Date.valueOf(realCustomer.getDateOfBirth());
        when(resultSetMock.getDate("date_of_birth")).thenReturn(date);
        when(resultSetMock.getString("phone_number")).thenReturn(realCustomer.getPhoneNumber());
        when(resultSetMock.getDouble("available_fund")).thenReturn(realCustomer.getAvailableFunds());
    }

}