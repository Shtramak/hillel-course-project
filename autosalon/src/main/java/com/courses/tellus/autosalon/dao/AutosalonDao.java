package com.courses.tellus.autosalon.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.courses.tellus.autosalon.config.ConnectionFactory;
import com.courses.tellus.autosalon.model.Autosalon;
import com.courses.tellus.autosalon.util.AutosalonEnum;

public class AutosalonDao {

    private final ConnectionFactory connectionFactory;

    public AutosalonDao(final ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    /**
     * Autosalon to be saved in database.
     */

    public int addAutosalon(final Autosalon autosalon) {
        try (Connection conection = connectionFactory.getConnection()){
            final PreparedStatement preparedStatement = conection.prepareStatement(
                    "INSERT INTO infoSalon (name,address,telephone) VALUES(?,?,?)");
            preparedStatement.setString(AutosalonEnum.FIRST_ENUM.getStatement(), autosalon.getName());
            preparedStatement.setString(AutosalonEnum.SECOND_ENUM.getStatement(), autosalon.getAddress());
            preparedStatement.setString(AutosalonEnum.THIRD_ENUM.getStatement(), autosalon.getTelophone());
            preparedStatement.execute();
            return 1;
        } catch (SQLException e) {
            return 0;
        }
    }

    /**
     * Autosalon search by id in database.
     *
     * @param identifier to search
     * @return autosalon
     */

    public Autosalon getAutoSalonById(final Long identifier) {
        Autosalon autoSalon = null;
        try (Connection conection = connectionFactory.getConnection()) {
            final PreparedStatement preparedStatement = conection.prepareStatement(
                    "SELECT*FROM infoSalon WHERE id='" + identifier + "'");
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                autoSalon = new Autosalon(resultSet.getLong("id"), resultSet.getString("name"),
                        resultSet.getString("address"), resultSet.getString("telephone"));
            }
        } catch (SQLException e) {
            return null;
        }
        return autoSalon;
    }

    /**
     * Find all Autosalon in database.
     *
     * @return Autosalon list
     */

    public List<Autosalon> findAllAutosalon() throws SQLException {
        Connection conection = connectionFactory.getConnection();
        List<Autosalon> list = new ArrayList<Autosalon>();
        Autosalon autoSalon = new Autosalon();
        final PreparedStatement preparedStatement = conection.prepareStatement("SELECT*FROM infoSalon");
        final ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            autoSalon.setId(resultSet.getLong("id"));
            autoSalon.setName(resultSet.getString("name"));
            autoSalon.setAddress(resultSet.getString("address"));
            autoSalon.setTelophone(resultSet.getString("telephone"));
            list.add(autoSalon);
        }
        return list;
    }

    /**
     * Autosalon delete by id in database.
     *
     * @param identifier to remove
     */

    public int removeAutoSalonId(final int identifier) {
        try (Connection conection = connectionFactory.getConnection()) {
            final PreparedStatement preparedStatement = conection.prepareStatement(
                    "DELETE FROM infoSalon WHERE id='" + identifier + "'");
            preparedStatement.executeUpdate();
            return 1;
        } catch (SQLException e) {
            return 0;
        }
    }
}
