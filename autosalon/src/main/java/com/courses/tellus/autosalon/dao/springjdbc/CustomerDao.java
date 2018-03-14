package com.courses.tellus.autosalon.dao.springjdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.autosalon.dao.AutosalonDaoInterface;
import com.courses.tellus.autosalon.exception.DaoException;
import com.courses.tellus.autosalon.model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CustomerDao implements AutosalonDaoInterface<Customer> {

    private final transient JdbcTemplate jdbcTemplate;

    public CustomerDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Customer> getAll() throws DaoException {
        return jdbcTemplate.query("SELECT * FROM customer",
                (ResultSet resultSet, int rowNum) -> customerFromResultSet(resultSet));
    }

    @Override
    public Optional<Customer> getById(final Long customerId) throws DaoException {
        return jdbcTemplate.query("SELECT * FROM customer WHERE id=" + customerId, (resultSet) -> {
            return Optional.of(customerFromResultSet(resultSet));
        });
    }

    @Override
    public Integer update(final Customer customer) throws DaoException {
        final String sql = "UPDATE customer "
                + "SET name=?,surname=?,date_of_birth=?,phone_number=?,available_funds=?"
                + "WHERE id=?";
        return jdbcTemplate.update(sql, customerData(customer));
    }

    @Override
    public Integer delete(final Long customerId) throws DaoException {
        return jdbcTemplate.update("DELETE FROM customer WHERE id=" + customerId);
    }

    @Override
    public Integer insert(final Customer customer) throws DaoException {
        final String sql = "INSERT INTO"
                + " customer (name, surname, date_of_birth, phone_number, available_funds, id)"
                + " VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, customerData(customer));
    }

    private Customer customerFromResultSet(final ResultSet resultSet) throws SQLException {
        final Customer customer = new Customer();
        customer.setId(resultSet.getLong("id"));
        customer.setName(resultSet.getString("name"));
        customer.setSurname(resultSet.getString("surname"));
        customer.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
        customer.setPhoneNumber(resultSet.getString("phone_number"));
        customer.setAvailableFunds(resultSet.getDouble("available_funds"));
        return customer;
    }

    private Object[] customerData(final Customer customer) {
        return new Object[]{customer.getName(),
                customer.getSurname(),
                customer.getDateOfBirth(),
                customer.getPhoneNumber(),
                customer.getAvailableFunds(), customer.getId()};
    }
}