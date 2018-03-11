package com.courses.airport.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.courses.airport.exception.DaoException;
import com.courses.airport.model.Ticket;
import org.apache.log4j.Logger;

public class TicketsDao {
    private static final int INDEX_NAME = 1;
    private static final int INDEX_SURNAME = 2;
    private static final int INDEX_FLIGHT_DATE = 3;
    private static final int INDEX_DEST_CITY = 4;
    private static final int INDEX_ID = 5;
    private static final Logger LOGGER = Logger.getLogger(TicketsDao.class);

    private final transient Connection connection;

    public TicketsDao(final Connection connection) {
        this.connection = connection;
    }

    /**
     * Inserts entry about given customer to database.
     *
     * @param ticket - parameter with customer data
     * @return true if ticket data was inserted to database
     * @throws DaoException if insert operation fails with SQLException
     */
    public boolean insert(final Ticket ticket) throws DaoException {
        if (ticket == null) {
            throw new DaoException("Ticket must be not null!");
        }
        final String sql = "INSERT INTO"
                + " airport_tickets (name, surname, dateOfFlight, destCity, id)"
                + " VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            setStatementValues(statement, ticket);
            final int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            final String message = "Insertion failed! Reason: " + e.getMessage();
            LOGGER.error(message);
            throw new DaoException(message, e);
        }
    }

    /**
     * Returns <i>ticket</i> from database entries by specified id.
     *
     * @param ticketId ticket id
     * @return ticket from database
     * @throws DaoException if find operation fails with SQLException
     */
    public Ticket findById(final long ticketId) throws DaoException {
        if (ticketId < 0) {
            throw new DaoException("Ticket id must be positive, but entered: " + ticketId);
        }
        final String sql = "SELECT * FROM airport_tickets WHERE id=" + ticketId;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            if (resultSet.next()) {
                return ticketsFromResultSet(resultSet);
            }
            return null;
        } catch (SQLException e) {
            final String message = "Selection failed! Reason: " + e.getMessage();
            LOGGER.error(message);
            throw new DaoException(message, e);
        }
    }

    /**
     * Returns all <i>tickets</i> from database entries.
     *
     * @return List of customers
     * @throws DaoException if find operation fails with SQLException
     */
    public List<Ticket> findAll() throws DaoException {
        final String sql = "SELECT * FROM airport_tickets";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            final List<Ticket> tickets = new ArrayList<>();
            while (resultSet.next()) {
                tickets.add(ticketsFromResultSet(resultSet));
            }
            return tickets;
        } catch (SQLException e) {
            final String message = "Selection failed! Reason: " + e.getMessage();
            LOGGER.error(message);
            throw new DaoException(message, e);
        }
    }

    /**
     * Updates  entry in database about given <i>customer</i>.
     *
     * @param ticket - customer who info should be updated
     * @return true if operation was success
     * @throws DaoException if remove operation fails with SQLException
     */
    public boolean update(final Ticket ticket) throws DaoException {
        if (ticket == null) {
            throw new DaoException("Ticket must be not null!");
        }
        final String sql = "UPDATE airport_tickets "
                + "SET name=?,surname=?,dateOfFlight=?,destCity=?"
                + "WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            setStatementValues(statement, ticket);
            final int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException e) {
            final String message = "Update failed! Reason: " + e.getMessage();
            LOGGER.error(message);
            throw new DaoException(message, e);
        }
    }

    /**
     * Removes entry about <i>ticket</i> from database by specified id.
     *
     * @param ticketId id number of customer
     * @return true if customer was removed from database
     * @throws DaoException if remove operation fails with SQLException
     */
    public boolean removeById(final long ticketId) throws DaoException {
        if (ticketId < 0) {
            throw new DaoException("Ticket id must be positive, but entered: " + ticketId);
        }
        final String sql = "DELETE FROM airport_tickets WHERE id=" + ticketId;
        try (Statement statement = connection.createStatement()) {
            final int rowsAffected = statement.executeUpdate(sql);
            return rowsAffected == 1;
        } catch (SQLException e) {
            final String message = "Remove failed! Reason: " + e.getMessage();
            LOGGER.error(message);
            throw new DaoException(message, e);
        }
    }

    void setStatementValues(final PreparedStatement statement, final Ticket ticket) throws SQLException {
        statement.setString(INDEX_NAME, ticket.getName());
        statement.setString(INDEX_SURNAME, ticket.getSurname());
        final Date flightDate = Date.valueOf(ticket.getDateFlight());
        statement.setDate(INDEX_FLIGHT_DATE, flightDate);
        statement.setString(INDEX_DEST_CITY, ticket.getDestCity());
        statement.setLong(INDEX_ID, ticket.getTicketId());
    }

    private Ticket ticketsFromResultSet(final ResultSet resultSet) throws SQLException {
        final long ticketId = resultSet.getLong("id");
        final String name = resultSet.getString("name");
        final String surname = resultSet.getString("surname");
        final Date flightDate = resultSet.getDate("dateOfFlight");
        final String destination = resultSet.getString("destCity");
        return new Ticket(ticketId, name, surname, localDate(flightDate), destination);
    }

    private LocalDate localDate(final Date flightDate) {
        return flightDate.toLocalDate();
    }
}
