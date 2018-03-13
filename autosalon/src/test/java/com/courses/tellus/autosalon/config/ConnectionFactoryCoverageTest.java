package com.courses.tellus.autosalon.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileReader;
import java.time.LocalDate;

import com.courses.tellus.autosalon.dao.CustomerDao;
import com.courses.tellus.autosalon.model.Customer;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ConnectionFactoryCoverageTest {

    private static CustomerDao customerDao;

    @BeforeAll
    static void init() throws Exception {
        RunScript.execute(ConnectionFactory.getInstance().getConnection(),
                new FileReader("src/test/resources/test.sql"));
        customerDao = new CustomerDao(ConnectionFactory.getInstance());
    }

    @Test
    void tesDataBaseConnectionSuccess() throws Exception {
        Customer customer = new Customer(3, "John", "Smith", LocalDate.of(2018, 2, 20), "(012)345-67-89", 10000.50);
        assertEquals(Integer.valueOf(1), customerDao.insert(customer));
    }
}
