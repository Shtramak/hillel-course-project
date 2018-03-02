package com.courses.tellus.autosalon.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
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

import com.courses.tellus.autosalon.exception.DaoException;
import com.courses.tellus.autosalon.model.Customer;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CustomerDaoUnitTest {
    private Connection connection;
    private CustomerDao customerDao;

    @BeforeClass
    public static void disableWarning() {
        System.err.close();
        System.setErr(System.out);
    }

    @Before
    public void setUp() {
        connection = mock(Connection.class);
        customerDao = new CustomerDao(connection);
    }

    @Test
    public void insertWithValidDataReturnsTrue() throws DaoException, SQLException {
        Customer customer = new Customer(1, "John", "Smith", LocalDate.of(2018, 2, 20), "(012)345-67-89", 10000.50);
        PreparedStatement statement = mock(PreparedStatement.class);
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeUpdate()).thenReturn(1);
        assertTrue(customerDao.insert(customer));
    }

    @Test(expected = DaoException.class)
    public void insertWhenBadConnectionThrowsDAOException() throws DaoException, SQLException {
        Customer customer = mock(Customer.class);
        when(connection.prepareStatement(anyString())).thenThrow(SQLException.class);
        customerDao.insert(customer);
    }

    @Test
    public void findByIdWithExistingIdReturnsCustomer() throws DaoException, SQLException {
        Statement statement = mock(Statement.class);
        ResultSet resultSet = mock(ResultSet.class);
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        Customer customer = new Customer(1, "John", "Smith", LocalDate.of(2018, 2, 20), "(012)345-67-89", 10000.50);
        when(resultSet.getLong("id")).thenReturn(customer.getId());
        when(resultSet.getString("name")).thenReturn(customer.getName());
        when(resultSet.getString("surname")).thenReturn(customer.getSurname());
        Date date = Date.valueOf(customer.getDateOfBirth());
        when(resultSet.getDate("date_of_birth")).thenReturn(date);
        when(resultSet.getString("phone_number")).thenReturn(customer.getPhoneNumber());
        when(resultSet.getDouble("available_fund")).thenReturn(customer.getAvailableFunds());
        assertEquals(customer, customerDao.findById(customer.getId()));
    }

    @Test
    public void findByIdWhenEmptyResultSetReturnsNull() throws DaoException, SQLException {
        Statement statement = mock(Statement.class);
        ResultSet resultSet = mock(ResultSet.class);
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);
        assertNull(customerDao.findById(1L));
    }

    @Test(expected = DaoException.class)
    public void findByIdWithNegativeIdThrowsDaoException() throws DaoException {
        customerDao.findById(-1);
    }

    @Test(expected = DaoException.class)
    public void findByIdWhenBadConnectionThrowsDaoException() throws DaoException, SQLException {
        when(connection.createStatement()).thenThrow(SQLException.class);
        customerDao.findById(1L);
    }

    @Test(expected = DaoException.class)
    public void findAllWhenBadConnectionThrowsDaoException() throws DaoException, SQLException {
        when(connection.createStatement()).thenThrow(SQLException.class);
        customerDao.findAll();
    }

    @Test(expected = DaoException.class)
    public void updateWhenBadConnectionThrowsDaoException() throws DaoException, SQLException {
        Customer customer = mock(Customer.class);
        when(connection.prepareStatement(anyString())).thenThrow(SQLException.class);
        customerDao.update(customer);
    }

    @Test(expected = DaoException.class)
    public void removeByIdWhenBadConnectionThrowsDaoException() throws DaoException, SQLException {
        when(connection.createStatement()).thenThrow(SQLException.class);
        customerDao.removeById(1L);
    }

}