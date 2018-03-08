package com.courses.tellus.autosalon.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.courses.tellus.autosalon.config.ConnectionFactory;
import com.courses.tellus.autosalon.exception.DaoException;
import com.courses.tellus.autosalon.model.Customer;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerDaoIntegrationTest {
    private CustomerDao customerDao;
    private static ConnectionFactory connectionFactory;

    @BeforeAll
    static void initTables() throws Exception {
        connectionFactory = ConnectionFactory.getInstance();
        RunScript.execute(connectionFactory.getConnection(), new FileReader("src/test/resources/test.sql"));

    }

    @BeforeEach
    void setUp() throws Exception {
        connectionFactory = ConnectionFactory.getInstance();
        customerDao = new CustomerDao(connectionFactory);
    }

    @AfterEach
    void tearDown() throws Exception {
        truncateTableCustomer();
    }

    @Test
    void insertWithValidDataReturnsTrue() throws Exception {
        Customer customer = new Customer(1, "John", "Smith", LocalDate.of(2018, 2, 20), "(012)345-67-89", 10000.50);
        assertTrue(customerDao.insert(customer));
    }

    @Test
    void insertWhenCustomerHasNullElementThrowsDAOException() throws Exception {
        Customer customer = new Customer(1, "John", null, LocalDate.of(2018, 2, 20), "(012)345-67-89", 10000.50);
        assertThrows(DaoException.class, () -> {
            customerDao.insert(customer);
        });
    }

    @Test
    void insertWhenCustomerIsNullThrowsDaoException() throws Exception {
        Throwable exception = assertThrows(DaoException.class, () -> {
            customerDao.insert(null);
        });
        assertEquals("Customer must be not null!", exception.getMessage());
    }

    @Test
    void findByIdWithExistingIdReturnsCustomer() throws Exception {
        insertCustomersToDb(3);
        Customer expected = new Customer(2, "name2", "surname2", LocalDate.of(2018, 2, 20), "phoneNumber2", 2000);
        Customer actual = customerDao.findById(2);
        assertEquals(expected, actual);
    }

    @Test
    void findByIdWithNotExistingIdReturnsNull() throws Exception {
        insertCustomersToDb(1);
        assertEquals(null, customerDao.findById(2));
    }

    @Test
    void findByIdWithNegativeIdThrowsDaoException() throws Exception {
        Throwable exception = assertThrows(DaoException.class, () -> {
            customerDao.findById(-1);
        });
        assertEquals("Customer id must be positive, but entered: -1", exception.getMessage());
    }

    @Test
    void findAllWhenTableHasDataReturnsListOfCustomers() throws Exception {
        insertCustomersToDb(2);
        Customer customer1 = new Customer(1, "name1", "surname1", LocalDate.of(2018, 2, 20), "phoneNumber1", 1000);
        Customer customer2 = new Customer(2, "name2", "surname2", LocalDate.of(2018, 2, 20), "phoneNumber2", 2000);
        List<Customer> expected = Arrays.asList(customer1, customer2);
        List<Customer> actual = customerDao.findAll();
        assertEquals(expected, actual);
    }

    @Test
    void findAllWhenTableHasNoDataReturnsEmptyList() throws DaoException {
        assertEquals(Collections.emptyList(), customerDao.findAll());
    }

    @Test
    void updateWhenEntryExistsReturnsTrue() throws Exception {
        insertCustomersToDb(3);
        Customer updatedCustomer = new Customer(2, "updateName", "updateSurname", LocalDate.of(2018, 2, 20), "phoneNumber2", 2000);
        assertTrue(customerDao.update(updatedCustomer));
    }

    @Test
    void updateWhenEntryNotExistsReturnsFalse() throws Exception {
        insertCustomersToDb(2);
        Customer updatedCustomer = new Customer(3, "updateName", "updateSurname", LocalDate.of(2018, 2, 20), "phoneNumber3", 2000);
        assertFalse(customerDao.update(updatedCustomer));
    }

    @Test
    void updateWhenCustomerIsNullThrowsDaoException() throws DaoException {
        Throwable exception = assertThrows(DaoException.class, () -> {
            customerDao.update(null);
        });
        assertEquals("Customer must be not null!", exception.getMessage());
    }

    @Test
    void removeByIdWithExistingIdReturnsTrue() throws Exception {
        insertCustomersToDb(3);
        assertTrue(customerDao.removeById(2));
    }

    @Test
    void removeByIdWithNotExistingReturnsFalse() throws Exception {
        insertCustomersToDb(1);
        assertFalse(customerDao.removeById(2));
    }

    @Test
    void removeByIdWithNegativeIdThrowsDaoException() throws Exception {
        Throwable exception = assertThrows(DaoException.class, () -> {
            customerDao.removeById(-1);
        });
        assertEquals("Customer id must be positive, but entered: -1", exception.getMessage());
    }

    private void truncateTableCustomer() throws SQLException {
        Connection connection = connectionFactory.getConnection();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE CUSTOMER");
        }
    }

    private void insertCustomersToDb(int numberOfCustomers) throws SQLException {
        Connection connection = connectionFactory.getConnection();
        String sql = "INSERT INTO" +
                " customer (id, name, surname, date_of_birth, phone_number, available_funds)" +
                " VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int id = 1; id <= numberOfCustomers; id++) {
                statement.setLong(1, id);
                statement.setString(2, "name" + id);
                statement.setString(3, "surname" + id);
                statement.setDate(4, Date.valueOf(LocalDate.of(2018, 2, 20)));
                statement.setString(5, "phoneNumber" + id);
                statement.setLong(6, id * 1000);
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}