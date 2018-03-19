package com.courses.tellus.autosalon.dao.springjdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.autosalon.dao.config.JdbcTemplatesConfigTest;
import com.courses.tellus.autosalon.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {JdbcTemplatesConfigTest.class, CustomerDao.class})
@ExtendWith(SpringExtension.class)
@Sql("classpath:test.sql")
public class CustomerDaoIntegrationTest {
    private static final Customer EXISTED_CUSTOMER = new Customer(1, "John", "Rambo", LocalDate.of(1946, 7, 6), "(012)345-67-89", 10000000.50);
    private static final Customer NOT_EXISTED_CUSTOMER = new Customer(3, "John", "Smith", LocalDate.of(2018, 2, 20), "(012)345-67-89", 10000.50);

    @Autowired
    private CustomerDao customerDao;

    @Test
    void getAllWhenTableHasDataReturnsListOfCustomers() {
        Customer customer2 = new Customer(2, "John", "Travolta", LocalDate.of(1954, 2, 18), "(012)345-67-89", 5000000.50);
        List<Customer> expected = Arrays.asList(EXISTED_CUSTOMER, customer2);
        List<Customer> actual = customerDao.getAll();
        assertEquals(expected, actual);
    }

    @Sql(value = {"classpath:test.sql", "classpath:trunc.sql"})
    @Test
    void getAllWhenTableHasNoDataReturnsEmptyList() throws Exception {
        assertEquals(Collections.emptyList(), customerDao.getAll());
    }

    @Sql("classpath:dropTables.sql")
    @Test
    void getAllWhenTableNotExistsReturnsEmptyList() throws Exception {
        assertEquals(Collections.emptyList(), customerDao.getAll());
    }

    @Test
    void getByIdWithExistingIdReturnsCustomer() {
        Customer actual = customerDao.getById(1L).get();
        assertEquals(EXISTED_CUSTOMER, actual);
    }

    @Test
    void getByIdWhenTableNotExistsReturnsOptionalEmpty() {
        assertEquals(Optional.empty(), customerDao.getById(3L));
    }

    @Sql("classpath:dropTables.sql")
    @Test
    void getByIdWithNotExistingIdReturnsOptionalEmpty() {
        assertEquals(Optional.empty(), customerDao.getById(3L));
    }

    @Test
    void updateWhenEntryExistsReturns1() {
        Customer updatedCustomer = new Customer(2, "updateName", "updateSurname", LocalDate.of(2018, 2, 20), "phoneNumber2", 2000);
        assertEquals(Integer.valueOf(1), customerDao.update(updatedCustomer));
    }

    @Test
    void updateWhenEntryNotExistsReturns0() {
        Customer updatedCustomer = new Customer(3, "updateName", "updateSurname", LocalDate.of(2018, 2, 20), "phoneNumber3", 2000);
        assertEquals(Integer.valueOf(0), customerDao.update(updatedCustomer));
    }

    @Sql("classpath:dropTables.sql")
    @Test
    void updateWhenTableNotExistsReturnsMinus1() {
        Customer updatedCustomer = new Customer(3, "updateName", "updateSurname", LocalDate.of(2018, 2, 20), "phoneNumber3", 2000);
        assertEquals(Integer.valueOf(-1), customerDao.update(updatedCustomer));
    }

    @Test
    void deleteWithExistingIdReturns1() {
        assertEquals(Integer.valueOf(1), customerDao.delete(2L));
    }

    @Test
    void deleteWithNotExistingReturns0() {
        assertEquals(Integer.valueOf(0), customerDao.delete(3L));
    }

    @Sql("classpath:dropTables.sql")
    @Test
    void deleteWhenTableNotExistsReturnsMinus1() {
        assertEquals(Integer.valueOf(-1), customerDao.delete(3L));
    }

    @Test
    void insertWithValidDataReturns1() {
        assertEquals(Integer.valueOf(1), customerDao.insert(NOT_EXISTED_CUSTOMER));
    }

    @Test
    void insertWhenEntryExistsReturnsMinus1() {
        assertEquals(Integer.valueOf(-1), customerDao.insert(EXISTED_CUSTOMER));
    }

    @Sql("classpath:dropTables.sql")
    @Test
    void insertWhenTableNotExistsReturnsMinus1() {
        assertEquals(Integer.valueOf(-1), customerDao.insert(NOT_EXISTED_CUSTOMER));
    }
}