package com.courses.tellus.autosalon.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

import com.courses.tellus.autosalon.config.ConnectionFactory;
import com.courses.tellus.autosalon.exception.DaoException;
import com.courses.tellus.autosalon.model.Customer;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerDaoIntegrationTest {
    private static final Customer CUSTOMER = new Customer(1, "John", "Rambo", LocalDate.of(1946, 7, 6), "(012)345-67-89", 10000000.50);

    private static ConnectionFactory connectionFactory;

    private CustomerDao customerDao;

    @BeforeEach
    void setUp() throws Exception {
        connectionFactory = ConnectionFactory.getInstance();
        customerDao = new CustomerDao(connectionFactory);
        RunScript.execute(connectionFactory.getConnection(), new FileReader("src/test/resources/test.sql"));
    }

    @Test
    void insertWithValidDataReturnsTrue() throws Exception {
        Customer CUSTOMER = new Customer(3, "John", "Smith", LocalDate.of(2018, 2, 20), "(012)345-67-89", 10000.50);
        Integer res = customerDao.insert(CUSTOMER);
        assertEquals(Integer.valueOf(1), res);
    }

    @Test
    void insertWhenTableNotExistsThrowsDaoException() throws Exception {
        dropTableCustomer();
        assertThrows(DaoException.class, () -> {
            customerDao.insert(CUSTOMER);
        });
    }

    @Test
    void getByIdWithExistingIdReturnsCustomer() throws Exception {
        Customer actual = customerDao.getById(1L).orElse(null);
        assertEquals(CUSTOMER, actual);
    }

    @Test
    void getByIdWithNotExistingIdReturnsOptionalEmpty() throws Exception {
        assertEquals(Optional.empty(), customerDao.getById(5L));
    }

    @Test
    void getByIdWhenTableNotExistsThrowsDaoException() throws Exception {
        dropTableCustomer();
        long id = ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE);
        assertThrows(DaoException.class, () -> {
            customerDao.getById(id);
        });
    }

    @Test
    void getAllWhenTableHasDataReturnsListOfCustomers() throws Exception {
        Customer customer2 = new Customer(2, "John", "Travolta", LocalDate.of(1954, 2, 18), "(012)345-67-89", 5000000.50);
        List<Customer> expected = Arrays.asList(CUSTOMER, customer2);
        List<Customer> actual = customerDao.getAll();
        assertEquals(expected, actual);
    }

    @Test
    void getAllWhenTableHasNoDataReturnsEmptyList() throws Exception {
        Connection connection = connectionFactory.getConnection();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE CUSTOMER");
        }
        assertEquals(Collections.emptyList(), customerDao.getAll());
    }

    @Test
    void getAllWhenTableNotExistsThrowsDaoException() throws Exception {
        dropTableCustomer();
        assertThrows(DaoException.class, () -> {
            customerDao.getAll();
        });
    }

    @Test
    void updateWhenEntryExistsReturnsTrue() throws Exception {
        Customer updatedCustomer = new Customer(2, "updateName", "updateSurname", LocalDate.of(2018, 2, 20), "phoneNumber2", 2000);
        assertEquals(Integer.valueOf(1), customerDao.update(updatedCustomer));
    }

    @Test
    void updateWhenEntryNotExistsReturnsFalse() throws Exception {
        Customer updatedCustomer = new Customer(3, "updateName", "updateSurname", LocalDate.of(2018, 2, 20), "phoneNumber3", 2000);
        assertEquals(Integer.valueOf(0), customerDao.update(updatedCustomer));
    }

    @Test
    void updateWhenTableNotExistsThrowsDaoException() throws Exception {
        dropTableCustomer();
        assertThrows(DaoException.class, () -> {
            customerDao.update(new Customer());
        });
    }

    @Test
    void deleteWithExistingIdReturns1() throws Exception {
        assertEquals(Integer.valueOf(1), customerDao.delete(2L));
    }

    @Test
    void deleteWithNotExistingReturns0() throws Exception {
        assertEquals(Integer.valueOf(0), customerDao.delete(3L));
    }

    @Test
    void deleteWhenTableNotExistsThrowsDaoException() throws Exception {
        dropTableCustomer();
        long id = ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE);
        assertThrows(DaoException.class, () -> {
            customerDao.delete(id);
        });
    }

    private void dropTableCustomer() throws SQLException {
        Connection connection = connectionFactory.getConnection();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE CUSTOMER");
        }
    }
}