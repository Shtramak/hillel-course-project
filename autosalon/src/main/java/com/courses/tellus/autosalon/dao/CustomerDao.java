package com.courses.tellus.autosalon.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.autosalon.config.ConnectionFactory;
import com.courses.tellus.autosalon.exception.DaoException;
import com.courses.tellus.autosalon.model.Customer;
import org.apache.log4j.Logger;

public class CustomerDao implements AutosalonDaoInterface<Customer> {

    private static final int INDEX_NAME = 1;
    private static final int INDEX_SURNAME = 2;
    private static final int INDEX_BIRTHDAY = 3;
    private static final int INDEX_PHONE = 4;
    private static final int INDEX_FUNDS = 5;
    private static final int INDEX_ID = 6;
    private static final Logger LOGGER = Logger.getLogger(CustomerDao.class);

    private final transient Connection connection;

    public CustomerDao(final ConnectionFactory connectionFactory) throws DaoException {
        try {
            this.connection = connectionFactory.getConnection();
        } catch (SQLException e) {
            final String message = "Connection to database failed! Reason:" + e.getMessage();
            LOGGER.error(message);
            throw new DaoException(message, e);
        }
    }

    @Override
    public Integer insert(final Customer customer) throws DaoException {
        final String sql = "INSERT INTO"
                + " customer (name, surname, date_of_birth, phone_number, available_funds, id)"
                + " VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            setStatementValues(statement, customer);
            return statement.executeUpdate();
        } catch (SQLException e) {
            final String message = "Insertion failed! Reason: " + e.getMessage();
            LOGGER.error(message);
            throw new DaoException(message, e);
        }
    }

    @Override
    public Optional<Customer> getById(final Long customerId) throws DaoException {
        final String sql = "SELECT * FROM customer WHERE id=" + customerId;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            if (resultSet.next()) {
                return Optional.of(customerFromResultSet(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            final String message = "Selection failed! Reason: " + e.getMessage();
            LOGGER.error(message);
            throw new DaoException(message, e);
        }
    }

    @Override
    public List<Customer> getAll() throws DaoException {
        final String sql = "SELECT * FROM customer";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            final List<Customer> customers = new ArrayList<>();
            while (resultSet.next()) {
                customers.add(customerFromResultSet(resultSet));
            }
            return customers;
        } catch (SQLException e) {
            final String message = "Selection failed! Reason: " + e.getMessage();
            LOGGER.error(message);
            throw new DaoException(message, e);
        }
    }

    @Override
    public Integer update(final Customer customer) throws DaoException {
        final String sql = "UPDATE customer "
                + "SET name=?,surname=?,date_of_birth=?,phone_number=?,available_funds=?"
                + "WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            setStatementValues(statement, customer);
            return statement.executeUpdate();
        } catch (SQLException e) {
            final String message = "Update failed! Reason: " + e.getMessage();
            LOGGER.error(message);
            throw new DaoException(message, e);
        }
    }

    @Override
    public Integer delete(final Long customerId) throws DaoException {
        final String sql = "DELETE FROM customer WHERE id=" + customerId;
        try (Statement statement = connection.createStatement()) {
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            final String message = "Remove failed! Reason: " + e.getMessage();
            LOGGER.error(message);
            throw new DaoException(message, e);
        }
    }

    private void setStatementValues(final PreparedStatement statement, final Customer customer) throws SQLException {
        statement.setString(INDEX_NAME, customer.getName());
        statement.setString(INDEX_SURNAME, customer.getSurname());
        final Date dateOfBirth = Date.valueOf(customer.getDateOfBirth());
        statement.setDate(INDEX_BIRTHDAY, dateOfBirth);
        statement.setString(INDEX_PHONE, customer.getPhoneNumber());
        statement.setDouble(INDEX_FUNDS, customer.getAvailableFunds());
        statement.setLong(INDEX_ID, customer.getId());
    }

    private Customer customerFromResultSet(final ResultSet resultSet) throws SQLException {
        final long customerId = resultSet.getLong("id");
        final String name = resultSet.getString("name");
        final String surname = resultSet.getString("surname");
        final Date birthday = resultSet.getDate("date_of_birth");
        final String phoneNumber = resultSet.getString("phone_number");
        final Double availableFund = resultSet.getDouble("available_funds");
        return new Customer(customerId, name, surname, localDate(birthday), phoneNumber, availableFund);
    }

    private LocalDate localDate(final Date birthday) {
        return birthday.toLocalDate();
    }
}