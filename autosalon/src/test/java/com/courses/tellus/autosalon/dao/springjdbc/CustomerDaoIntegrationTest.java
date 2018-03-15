package com.courses.tellus.autosalon.dao.springjdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileReader;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.courses.tellus.autosalon.config.springjdbc.JdbcTemplatesConfig;
import com.courses.tellus.autosalon.model.Customer;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {JdbcTemplatesConfig.class, CustomerDao.class})
@ExtendWith(SpringExtension.class)
public class CustomerDaoIntegrationTest {
    private static final Customer EXISTED_CUSTOMER = new Customer(1, "John", "Rambo", LocalDate.of(1946, 7, 6), "(012)345-67-89", 10000000.50);
    private static final Customer NOT_EXISTED_CUSTOMER = new Customer(3, "John", "Smith", LocalDate.of(2018, 2, 20), "(012)345-67-89", 10000.50);

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() throws Exception {
        Connection connection = jdbcTemplate.getDataSource().getConnection();
        RunScript.execute(connection, new FileReader("src/test/resources/test.sql"));
    }

    @Test
    void getAllWhenTableHasDataReturnsListOfCustomers() {
        Customer customer2 = new Customer(2, "John", "Travolta", LocalDate.of(1954, 2, 18), "(012)345-67-89", 5000000.50);
        List<Customer> expected = Arrays.asList(EXISTED_CUSTOMER, customer2);
        List<Customer> actual = customerDao.getAll();
        assertEquals(expected, actual);
    }

    @Test
    void getAllWhenTableHasNoDataReturnsEmptyList() throws Exception {
        truncateTable();
        assertEquals(Collections.emptyList(), customerDao.getAll());
    }

    @Test
    void getByIdWithExistingIdReturnsCustomer(){
        Customer actual = customerDao.getById(1L).get();
        assertEquals(EXISTED_CUSTOMER, actual);
    }

    @Test
    void getByIdWithExistingIdReturnsCustomerThrowsEmptyResultDataAccessException(){
        assertThrows(EmptyResultDataAccessException.class, () -> customerDao.getById(3L));
    }

    @Test
    void updateWhenEntryExistsReturnsTrue(){
        Customer updatedCustomer = new Customer(2, "updateName", "updateSurname", LocalDate.of(2018, 2, 20), "phoneNumber2", 2000);
        assertEquals(Integer.valueOf(1), customerDao.update(updatedCustomer));
    }

    @Test
    void updateWhenEntryNotExistsReturnsFalse(){
        Customer updatedCustomer = new Customer(3, "updateName", "updateSurname", LocalDate.of(2018, 2, 20), "phoneNumber3", 2000);
        assertEquals(Integer.valueOf(0), customerDao.update(updatedCustomer));
    }

    @Test
    void deleteWithExistingIdReturns1() {
        assertEquals(Integer.valueOf(1), customerDao.delete(2L));
    }

    @Test
    void deleteWithNotExistingReturns0(){
        assertEquals(Integer.valueOf(0), customerDao.delete(3L));
    }

    @Test
    void insertWhenNewEntry() {
        assertEquals(Integer.valueOf(1), customerDao.insert(NOT_EXISTED_CUSTOMER));
    }

    @Test
    void insertWhenEntryExistsThrowsDuplicateKeyException() {
        assertThrows(DuplicateKeyException.class, () -> {
            customerDao.insert(EXISTED_CUSTOMER);
        });
    }

    private void truncateTable() throws Exception {
        Connection connection = jdbcTemplate.getDataSource().getConnection();
        RunScript.execute(connection, new FileReader("src/test/resources/Truncate.sql"));
    }
}