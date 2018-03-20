package com.courses.tellus.airport.dao.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.airport.connection.jdbc.ConnectionFactory;
import com.courses.tellus.airport.dao.AirportDaoImpl;
import com.courses.tellus.airport.exception.DaoException;
import com.courses.tellus.airport.model.Airport;
import org.apache.log4j.Logger;

public class AirportDao  implements AirportDaoImpl<Airport> {

    private static final int INDEX_NAME = 1;
    private static final int INDEX_BIRTHDAY = 2;
    private static final int INDEX_PHONE = 3;
    private static final int INDEX_TERMINAL = 4;
    private static final int INDEX_ID = 5;
    private static final Logger LOGGER = Logger.getLogger(AirportDao.class);

    private final transient Connection connection;

    public AirportDao(final ConnectionFactory connectionFactory) throws DaoException {
        try {
            this.connection = connectionFactory.getConnection();
        } catch (SQLException e) {
            final String message = "Connection to database failed! Reason:" + e.getMessage();
            LOGGER.error(message);
            throw new DaoException(message, e);
        }
    }

    @Override
    public Optional<Airport> getById(final Long entityId) throws DaoException {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM airport WHERE id=" + entityId)) {

            if (resultSet.next()) {
                return Optional.of(getNewObjectFromResultSet(resultSet));
            }
            return Optional.empty();

        } catch (SQLException e) {
            final String message = "Selection failed! Reason: " + e.getMessage();
            LOGGER.error(message);
            throw new DaoException(message, e);

        }
    }

    @Override
    public List<Airport> getAll() throws DaoException {
        final String sql = "SELECT * FROM airport";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            final List<Airport> airports = new ArrayList<>();
            while (resultSet.next()) {
                airports.add(getNewObjectFromResultSet(resultSet));
            }
            return airports;
        } catch (SQLException e) {
            final String message = "Selection failed! Reason: " + e.getMessage();
            LOGGER.error(message);
            throw new DaoException(message, e);
        }
    }

    @Override
    public Integer update(final Airport entity) throws  DaoException {
                final String sql = "UPDATE airport "
                + "SET name=?, date_of_birth=?,phone_number=?, terminal=?"
                + "WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            setStatementValues(statement, entity);
            final int rowsAffected = statement.executeUpdate();
            return rowsAffected;
        } catch (SQLException e) {
            final String message = "Update failed! Reason: " + e.getMessage();
            LOGGER.error(message);
            throw new DaoException(message, e);
        }
    }

    @Override
    public Integer delete(final Long entityId) throws DaoException {
        final String sql = "DELETE FROM airport WHERE id=" + entityId;
        try (Statement statement = connection.createStatement()) {
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            final String message = "Remove failed! Reason: " + e.getMessage();
            LOGGER.error(message);
            throw new DaoException(message, e);
        }
    }

    @Override
    public Integer insert(final Airport entity) throws DaoException {

        final String sql = "INSERT INTO"
                + " airport (name, date_of_birth, phone_number, terminal, id)"
                + " VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            setStatementValues(statement, entity);
            return statement.executeUpdate();

        } catch (SQLException e) {
            final String message = "Insertion failed! Reason: " + e.getMessage();
            LOGGER.error(message);
            throw new DaoException(message, e);
        }
    }

    private Airport getNewObjectFromResultSet(final ResultSet resultSet) throws SQLException {
        final Long airportId = resultSet.getLong("id");
        final String name = resultSet.getString("name");
        final Date birthday = resultSet.getDate("date_of_birth");
        final String terminal = resultSet.getString("terminal");
        final String phoneNumber = resultSet.getString("phone_number");
        return new Airport(airportId, name, birthday.toLocalDate(), terminal, phoneNumber);
    }

    void setStatementValues(final PreparedStatement statement, final Airport airport) throws SQLException {
        statement.setString(INDEX_NAME, airport.getNameAirport());
        final Date dateOfBirth = Date.valueOf(airport.getDateOfBirth());
        statement.setDate(INDEX_BIRTHDAY, dateOfBirth);
        statement.setString(INDEX_PHONE, airport.getTelephone());
        statement.setString(INDEX_TERMINAL, airport.getNumberTerminal());
        statement.setLong(INDEX_ID, airport.getAirportId());
    }
}
