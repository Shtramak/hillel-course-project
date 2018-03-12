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
import java.util.Optional;

import com.courses.airport.connection.ConnectionFactory;
import com.courses.airport.exception.DaoException;
import com.courses.airport.model.Airport;
import org.apache.log4j.Logger;

public class AirportDao  implements BasicDao<Airport>{

    private static final int INDEX_NAME = 1;
    private static final int INDEX_BIRTHDAY = 2;
    private static final int INDEX_PHONE = 3;
    private static final int INDEX_TERMINAL = 4;
    private static final int INDEX_ID = 5;
    private static final Logger LOGGER = Logger.getLogger(AirportDao.class);

    private final transient Connection connection;

    public AirportDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public Optional<Airport> findById(Long entityId) throws DaoException {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM customer WHERE id=" + entityId)) {

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
    public List<Airport> findAll(Long airportId) throws DaoException {
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
    public int update(Airport entity) throws  DaoException {
        if (entity == null) {
            throw new DaoException("Airport must be not null!");
        }
        final String sql = "UPDATE airport "
                + "SET name=?, date_of_birth=?,phone_number=?, terminal=?"
                + "WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            setStatementValues(statement, entity);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected;
        } catch (SQLException e) {
            final String message = "Update failed! Reason: " + e.getMessage();
            LOGGER.error(message);
            throw new DaoException(message, e);
        }
    }

    @Override
    public int removeById(Long entityId) throws DaoException {
        if (entityId < 0) {
            throw new DaoException("Airport id must be positive, but entered: " + entityId);
        }
        final String sql = "DELETE FROM airport WHERE id=" + entityId;
        try (Statement statement = connection.createStatement()) {
            final int rowsAffected = statement.executeUpdate(sql);
            return rowsAffected;
        } catch (SQLException e) {
            final String message = "Remove failed! Reason: " + e.getMessage();
            LOGGER.error(message);
            throw new DaoException(message, e);
        }
    }

    @Override
    public int insert(Airport entity) throws DaoException {

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

    @Override
    public Airport getNewObjectFromResultSet(ResultSet resultSet) throws SQLException {
        final Long airportId = resultSet.getLong("id");
        final String name = resultSet.getString("name");
        final Date birthday = resultSet.getDate("date_of_birth");
        final String terminal = resultSet.getString("terminal");
        final String phoneNumber = resultSet.getString("phone_number");
        return new Airport(airportId, name, localDate(birthday), terminal, phoneNumber);
    }

    //
//    /**
//     * Inserts entry about given airport to database.
//     *
//     * @param airport - parameter with airport data
//     * @return true if airport data was inserted to database
//     * @throws DaoException if insert operation fails with SQLException
//     */
//    public insert(final Airport airport) throws DaoException {
//        if (airportId < 0) {
//            throw new DaoException("Airport id must be positive, but entered: " + airportId);
//        }
//        final String sql = "DELETE FROM airport WHERE id=" + airportId;
//        try (Statement statement = connection.createStatement()) {
//            final int rowsAffected = statement.executeUpdate(sql);
//            return rowsAffected == 1;
//        } catch (SQLException e) {
//            final String message = "Remove failed! Reason: " + e.getMessage();
//            LOGGER.error(message);
//            throw new DaoException(message, e);
//        }
//    }
//
//    /**
//     * Returns <i>airport</i> from database entries by specified id.
//     *
//     * @param airportId airport id
//     * @return airport from database
//     * @throws DaoException if find operation fails with SQLException
//     */
//    public Airport findById(final long airportId) throws DaoException {
//        if (airportId < 0) {
//            throw new DaoException("Airport id must be positive, but entered: " + airportId);
//        }
//        final String sql = "SELECT * FROM airport WHERE id=" + airportId;
//        try (Statement statement = connection.createStatement();
//             ResultSet resultSet = statement.executeQuery(sql)) {
//            if (resultSet.next()) {
//                return airportFromResultSet(resultSet);
//            }
//            return null;
//        } catch (SQLException e) {
//            final String message = "Selection failed! Reason: " + e.getMessage();
//            LOGGER.error(message);
//            throw new DaoException(message, e);
//        }
//    }
//
//    /**
//     * Returns all <i>airport</i> from database entries.
//     *
//     * @return List of airports
//     * @throws DaoException if find operation fails with SQLException
//     */
//    public List<Airport> findAll() throws DaoException {
//        final String sql = "SELECT * FROM airport";
//        try (Statement statement = connection.createStatement();
//             ResultSet resultSet = statement.executeQuery(sql)) {
//            final List<Airport> airports = new ArrayList<>();
//            while (resultSet.next()) {
//                airports.add(airportFromResultSet(resultSet));
//            }
//            return airports;
//        } catch (SQLException e) {
//            final String message = "Selection failed! Reason: " + e.getMessage();
//            LOGGER.error(message);
//            throw new DaoException(message, e);
//        }
//    }
//
//    /**
//     * Updates  entry in database about given <i>airport</i>.
//     *
//     * @param airport - airport who info should be updated
//     * @return true if operation was success
//     * @throws DaoException if remove operation fails with SQLException
//     */
//    public boolean update(final Airport airport) throws DaoException {
//        if (airport == null) {
//            throw new DaoException("Airport must be not null!");
//        }
//        final String sql = "UPDATE airport "
//                + "SET name=?, date_of_birth=?,phone_number=?, terminal=?"
//                + "WHERE id=?";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            setStatementValues(statement, airport);
//            final int rowsAffected = statement.executeUpdate();
//            return rowsAffected == 1;
//        } catch (SQLException e) {
//            final String message = "Update failed! Reason: " + e.getMessage();
//            LOGGER.error(message);
//            throw new DaoException(message, e);
//        }
//    }
//
//    /**
//     * Removes entry about <i>airport</i> from database by specified id.
//     *
//     * @param airportId id number of airport
//     * @return true if airport was removed from database
//     * @throws DaoException if remove operation fails with SQLException
//     */
//    public boolean removeById(final long airportId) throws DaoException {
//        if (airportId < 0) {
//            throw new DaoException("Airport id must be positive, but entered: " + airportId);
//        }
//        final String sql = "DELETE FROM airport WHERE id=" + airportId;
//        try (Statement statement = connection.createStatement()) {
//            final int rowsAffected = statement.executeUpdate(sql);
//            return rowsAffected == 1;
//        } catch (SQLException e) {
//            final String message = "Remove failed! Reason: " + e.getMessage();
//            LOGGER.error(message);
//            throw new DaoException(message, e);
//        }
//    }
//
    void setStatementValues(final PreparedStatement statement, final Airport airport) throws SQLException {
        statement.setString(INDEX_NAME, airport.getNameAirport());
        final Date dateOfBirth = Date.valueOf(airport.getDateOfBirth());
        statement.setDate(INDEX_BIRTHDAY, dateOfBirth);
        statement.setString(INDEX_PHONE, airport.getTelephone());
        statement.setString(INDEX_TERMINAL, airport.getNumberTerminal());
        statement.setLong(INDEX_ID, airport.getAirportId());
    }
//
//    private Airport airportFromResultSet(final ResultSet resultSet) throws SQLException {
//        final long airportId = resultSet.getLong("id");
//        final String name = resultSet.getString("name");
//        final Date birthday = resultSet.getDate("date_of_birth");
//        final String terminal = resultSet.getString("terminal");
//        final String phoneNumber = resultSet.getString("phone_number");
//        return new Airport(airportId, name, localDate(birthday), terminal, phoneNumber);
//    }

    private LocalDate localDate(final Date birthday) {
        return birthday.toLocalDate();
    }
}
