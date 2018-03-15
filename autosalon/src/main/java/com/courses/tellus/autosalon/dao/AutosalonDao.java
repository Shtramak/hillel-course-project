package com.courses.tellus.autosalon.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.autosalon.config.ConnectionFactory;
import com.courses.tellus.autosalon.model.Autosalon;
import org.apache.log4j.Logger;

public class AutosalonDao implements AutosalonDaoInterface<Autosalon> {

    private static final int INDEX_NAME = 1;
    private static final int INDEX_ADDRESS = 2;
    private static final int INDEX_TELEPHONE = 3;
    private final transient ConnectionFactory connectionFactory;
    private static final Logger LOGGER = Logger.getLogger(CustomerDao.class);

    public AutosalonDao(final ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
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

    /**
     * Find all Autosalon in database.
     *
     * @return Autosalon list
     */

    @Override
    public List<Autosalon> getAll() {
        final List<Autosalon> list = new ArrayList<Autosalon>();
        try (PreparedStatement preparedStatement = connectionFactory.getConnection().prepareStatement("SELECT*FROM infoSalon");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                list.add(autosalonFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.debug(e.getMessage());
        }
        return list;
    }

    /**
     * Autosalon search by id in database.
     *
     * @param identifier to search
     * @return autosalon
     */

    @Override
    public Optional<Autosalon> getById(final Long identifier) {
        try (PreparedStatement preparedStatement = connectionFactory.getConnection().prepareStatement(
                "SELECT*FROM infoSalon WHERE id='" + identifier + "'");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return Optional.of(autosalonFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.debug(e.getMessage());
        }
        return Optional.empty();
    }

    /**
     * Updating auto to DataBase.
     *
     * @param autosalon to save.
     * @return 1 if true else 0.
     */

    @Override
    public Integer update(final Autosalon autosalon) {
        try (PreparedStatement preparedStatement = connectionFactory.getConnection().prepareStatement(
                "UPDATE infoSalon SET name = ?, address = ?, telephone = ? WHERE id = ?")) {
            getPreparedStatement(preparedStatement, autosalon);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.debug(e.getMessage());
        }
        return 0;
    }

    /**
     * Autosalon delete by id in database.
     *
     * @param identifier to remove
     */

    @Override
    public Integer delete(final Long identifier) {
        try (PreparedStatement preparedStatement = connectionFactory.getConnection().prepareStatement(
                "DELETE FROM infoSalon WHERE id='" + identifier + "'")) {
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.debug(e.getMessage());
        }
        return 0;
    }

    /**
     * Autosalon to be saved in database.
     *
     * @param autosalon to insert
     */

    @Override
    public Integer insert(final Autosalon autosalon) {
        try (PreparedStatement preparedStatement = connectionFactory.getConnection().prepareStatement(
                "INSERT INTO infoSalon (name,address,telephone) VALUES(?,?,?)")) {
            getPreparedStatement(preparedStatement, autosalon);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.debug(e.getMessage());
        }
        return 0;
    }
}