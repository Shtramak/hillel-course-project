package com.courses.tellus.autosalon.dao.springjdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.courses.tellus.autosalon.model.Customer;
import org.springframework.jdbc.core.RowMapper;

public class CustomerMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
        final Customer customer = new Customer();
        customer.setId(resultSet.getLong("id"));
        customer.setName(resultSet.getString("name"));
        customer.setSurname(resultSet.getString("surname"));
        customer.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
        customer.setPhoneNumber(resultSet.getString("phone_number"));
        customer.setAvailableFunds(resultSet.getDouble("available_funds"));
        return customer;    }
}
