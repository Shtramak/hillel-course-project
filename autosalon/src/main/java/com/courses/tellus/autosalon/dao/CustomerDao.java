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

import com.courses.tellus.autosalon.exception.DaoException;
import com.courses.tellus.autosalon.model.Customer;
import org.apache.log4j.Logger;

public class CustomerDao {

    private static final int INDEX_NAME = 1;
    private static final int INDEX_SURNAME = 2;
    private static final int INDEX_BIRTHDAY = 3;
    private static final int INDEX_PHONE = 4;
    private static final int INDEX_FUNDS = 5;
    private static final int INDEX_ID = 6;
    private static final Logger LOGGER = Logger.getLogger(CustomerDao.class);

    private final transient Connection connection;

    public CustomerDao(final Connection connection) {
        this.connection = connection;
    }

    /**
     * Inserts entry about given customer to database.
     *
     * @param customer - parameter with customer data
     * @return true if customer data was inserted to database
     * @throws DaoException if insert operation fails with SQLException
     */
    public boolean insert(final Customer customer) throws DaoException {
        if (customer == null) {
            throw new DaoException("Customer must be not null!");
        }
        final String sql = "INSERT INTO"
                + " customer (name, surname, date_of_birth, phone_number, available_funds, id)"
                + " VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            setStatementValues(statement, customer);
            final int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            final String message = "Insertion failed! Reason: " + e.getMessage();
            LOGGER.error(message);
            throw new DaoException(message, e);
        }
    }

    /**
     * Returns <i>customer</i> from database entries by specified id.
     *
     * @param customerId customer id
     * @return customer from database
     * @throws DaoException if find operation fails with SQLException
     */
    public Customer findById(final long customerId) throws DaoException {
        if (customerId < 0) {
            throw new DaoException("Customer id must be positive, but entered: " + customerId);
        }
        final String sql = "SELECT * FROM customer WHERE id=" + customerId;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            if (resultSet.next()) {
                return customerFromResultSet(resultSet);
            }
            return null;
        } catch (SQLException e) {
            final String message = "Selection failed! Reason: " + e.getMessage();
            LOGGER.error(message);
            throw new DaoException(message, e);
        }
    }

    /**
     * Returns all <i>customers</i> from database entries.
     *
     * @return List of customers
     * @throws DaoException if find operation fails with SQLException
     */
    public List<Customer> findAll() throws DaoException {
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

    /**
     * Updates  entry in database about given <i>customer</i>.
     *
     * @param customer - customer who info should be updated
     * @return true if operation was success
     * @throws DaoException if remove operation fails with SQLException
     */
    public boolean update(final Customer customer) throws DaoException {
        if (customer == null) {
            throw new DaoException("Customer must be not null!");
        }
        final String sql = "UPDATE customer "
                + "SET name=?,surname=?,date_of_birth=?,phone_number=?,available_funds=?"
                + "WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            setStatementValues(statement, customer);
            final int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException e) {
            final String message = "Update failed! Reason: " + e.getMessage();
            LOGGER.error(message);
            throw new DaoException(message, e);
        }
    }

    /**
     * Removes entry about <i>customer</i> from database by specified id.
     *
     * @param customerId id number of customer
     * @return true if customer was removed from database
     * @throws DaoException if remove operation fails with SQLException
     */
    public boolean removeById(final long customerId) throws DaoException {
        if (customerId < 0) {
            throw new DaoException("Customer id must be positive, but entered: " + customerId);
        }
        final String sql = "DELETE FROM customer WHERE id=" + customerId;
        try (Statement statement = connection.createStatement()) {
            final int rowsAffected = statement.executeUpdate(sql);
            return rowsAffected == 1;
        } catch (SQLException e) {
            final String message = "Remove failed! Reason: " + e.getMessage();
            LOGGER.error(message);
            throw new DaoException(message, e);
        }
    }

    void setStatementValues(final PreparedStatement statement, final Customer customer) throws SQLException {
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