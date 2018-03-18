package com.courses.tellus.autosalon.dao.springjdbc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.autosalon.dao.AutosalonDaoInterface;
import com.courses.tellus.autosalon.model.Customer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CustomerDao implements AutosalonDaoInterface<Customer> {
    private static final Logger LOGGER = Logger.getLogger(CustomerDao.class);
    private final transient JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Customer> getAll() {
        try {
            return jdbcTemplate.query("SELECT * FROM customer", new CustomerMapper());
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<Customer> getById(final Long customerId) {
        try {
            final Customer customer = jdbcTemplate
                    .queryForObject("SELECT * FROM customer where id=" + customerId, new CustomerMapper());
            return Optional.of(customer);
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Integer update(final Customer customer) {
        final String sql = "UPDATE customer "
                + "SET name=?,surname=?,date_of_birth=?,phone_number=?,available_funds=?"
                + "WHERE id=?";
        try {
            return jdbcTemplate.update(sql, customerData(customer));
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage());
            return -1;
        }
    }

    @Override
    public Integer delete(final Long customerId) {
        try {
            return jdbcTemplate.update("DELETE FROM customer WHERE id=" + customerId);
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage());
            return -1;
        }
    }

    @Override
    public Integer insert(final Customer customer) {
        final String sql = "INSERT INTO"
                + " customer (name, surname, date_of_birth, phone_number, available_funds, id)"
                + " VALUES (?, ?, ?, ?, ?, ?)";
        try {
            return jdbcTemplate.update(sql, customerData(customer));
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage());
            return 0;
        }
    }

    private Object[] customerData(final Customer customer) {
        return new Object[]{customer.getName(),
                customer.getSurname(),
                customer.getDateOfBirth(),
                customer.getPhoneNumber(),
                customer.getAvailableFunds(), customer.getId()};
    }
}