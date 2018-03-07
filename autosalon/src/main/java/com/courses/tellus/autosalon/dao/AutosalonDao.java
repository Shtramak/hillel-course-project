package com.courses.tellus.autosalon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.courses.tellus.autosalon.model.Autosalon;
import org.apache.log4j.Logger;

public class AutosalonDao {

    private static final int INDEX_NAME = 1;
    private static final int INDEX_ADDRESS = 2;
    private static final int INDEX_TELEPHONE = 3;
    private final transient Connection connection;
    private static final Logger LOGGER = Logger.getLogger(CustomerDao.class);

    public AutosalonDao(final Connection connection) {
        this.connection = connection;
    }

    /**
     * Autosalon to be saved in database.
     */

    public int addAutosalon(final Autosalon autosalon) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO infoSalon (name,address,telephone) VALUES(?,?,?)")) {
            getPreparedStatement(preparedStatement, autosalon);
            preparedStatement.execute();
            return 1;
        } catch (SQLException e) {
            final String message = "Add failed! Reason: " + e.getMessage();
            LOGGER.error(message);
            return 0;
        }
    }

    /**
     * Autosalon search by id in database.
     *
     * @param identifier to search
     * @return autosalon
     */

    public Autosalon getAutoSalonById(final int identifier) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT*FROM infoSalon WHERE id='" + identifier + "'");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            final boolean flag = resultSet.next();
            if (flag) {
                return autosalonFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            final String message = "Selection failed! Reason: " + e.getMessage();
            LOGGER.error(message);
            return null;
        }
        return null;
    }

    /**
     * Find all Autosalon in database.
     *
     * @return Autosalon list
     */

    public List<Autosalon> findAllAutosalon() {
        final List<Autosalon> list = new ArrayList<Autosalon>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT*FROM infoSalon");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                list.add(autosalonFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            final String message = "Selection failed! Reason: " + e.getMessage();
            LOGGER.error(message);
            return null;
        }
        return list;
    }

    /**
     * Autosalon delete by id in database.
     *
     * @param identifier to remove
     */

    public int removeAutoSalonId(final int identifier) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM infoSalon WHERE id='" + identifier + "'")) {
            preparedStatement.executeUpdate();
            return 1;
        } catch (SQLException e) {
            final String message = "Remove failed! Reason: " + e.getMessage();
            LOGGER.error(message);
            return 0;
        }
    }

    /**
     * Autosalon search by id in database.
     *
     * @param preparedStatement to search
     * @return preparedStatement
     */

    public PreparedStatement getPreparedStatement(
            final PreparedStatement preparedStatement, final Autosalon autosalon) throws SQLException {
        preparedStatement.setString(INDEX_NAME, autosalon.getName());
        preparedStatement.setString(INDEX_ADDRESS, autosalon.getAddress());
        preparedStatement.setString(INDEX_TELEPHONE, autosalon.getTelophone());
        return preparedStatement;
    }

    private Autosalon autosalonFromResultSet(final ResultSet resultSet) throws SQLException {
        final Long getId = resultSet.getLong("id");
        final String getName = resultSet.getString("name");
        final String getAddress = resultSet.getString("address");
        final String getTelephone = resultSet.getString("telephone");
        return new Autosalon(getId, getName, getAddress, getTelephone);
    }

}
