package com.courses.tellus.autosalon.dao.springjdbc;

import java.util.List;
import java.util.Optional;

import com.courses.tellus.autosalon.dao.AutosalonDaoInterface;
import com.courses.tellus.autosalon.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CustomerDao implements AutosalonDaoInterface<Customer> {

    private final transient JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Customer> getAll() {
        return jdbcTemplate.query("SELECT * FROM customer", new CustomerMapper());
    }

    @Override
    public Optional<Customer> getById(final Long customerId) {
        final Customer customer = jdbcTemplate
                .queryForObject("SELECT * FROM customer where id=" + customerId, new CustomerMapper());
        return Optional.of(customer);
    }

    @Override
    public Integer update(final Customer customer) {
        final String sql = "UPDATE customer "
                + "SET name=?,surname=?,date_of_birth=?,phone_number=?,available_funds=?"
                + "WHERE id=?";
        return jdbcTemplate.update(sql, customerData(customer));
    }

    @Override
    public Integer delete(final Long customerId) {
        return jdbcTemplate.update("DELETE FROM customer WHERE id=" + customerId);
    }

    @Override
    public Integer insert(final Customer customer) {
        final String sql = "INSERT INTO"
                + " customer (name, surname, date_of_birth, phone_number, available_funds, id)"
                + " VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, customerData(customer));
    }

    private Object[] customerData(final Customer customer) {
        return new Object[]{customer.getName(),
                customer.getSurname(),
                customer.getDateOfBirth(),
                customer.getPhoneNumber(),
                customer.getAvailableFunds(), customer.getId()};
    }
}