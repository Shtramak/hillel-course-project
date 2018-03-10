package com.courses.tellus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.courses.tellus.dbconnection.ConnectionFactory;
import com.courses.tellus.entity.University;
import org.apache.log4j.Logger;

public class UniversityDao implements BasicDao<University> {

    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class);
    private final transient ConnectionFactory connectionFactory;

    public UniversityDao(final ConnectionFactory connectionFactory) {
            this.connectionFactory = connectionFactory;
        }

    @Override
    public List<University> getAll() {
        ResultSet resultSet;
        final List<University> universities = new ArrayList<>();
        try (Connection conn = connectionFactory.getConnection()) {
            final PreparedStatement preState = conn.prepareStatement("SELECT*FROM Universities");
            resultSet = preState.executeQuery();
            while (resultSet.next()) {
                universities.add(getNewObjectFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
        }
        return universities;
    }

    @Override
    public University getById(final Long entityId) {
        ResultSet resultSet;
        try (Connection conn = connectionFactory.getConnection()) {
            final PreparedStatement preState = conn.prepareStatement("SELECT*FROM Universities WHERE id=?");
            preState.setLong(OrderUtils.FIRST_STATEMENT.getOrder(), entityId);
            resultSet = preState.executeQuery();
            if (resultSet.next()) {
                return getNewObjectFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
        }
        return null;
    }

    @Override
    public int update(final University university) {
        try (Connection conn = connectionFactory.getConnection()) {
            final PreparedStatement preState = conn.prepareStatement("UPDATE Universities SET nameOfUniversity = ?,"
                    + " address =?, specialization =? WHERE id= ?");
            preState.setString(OrderUtils.FIRST_STATEMENT.getOrder(), university.getNameOfUniversity());
            preState.setString(OrderUtils.SECOND_STATEMENT.getOrder(), university.getAddress());
            preState.setString(OrderUtils.THIRD_STATEMENT.getOrder(), university.getSpecialization());
            preState.setLong(OrderUtils.FOURTH_STATEMENT.getOrder(), university.getUniId());
            return preState.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
            return 0;
        }
    }

    @Override
    public int delete(final Long entityId) {
        try (Connection conn = connectionFactory.getConnection()) {
            final PreparedStatement preState = conn.prepareStatement("DELETE FROM Universities WHERE id=?");
            preState.setLong(OrderUtils.FIRST_STATEMENT.getOrder(), entityId);
            return preState.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
            return 0;
        }
    }

    @Override
    public int insert(final University university) {
        try (Connection conn = connectionFactory.getConnection()) {
            final PreparedStatement preState = conn.prepareStatement(
                    "INSERT INTO Universities(nameOfUniversity, address, specialization)"
                            + "VALUES (?,?,?)");
            preState.setString(OrderUtils.FIRST_STATEMENT.getOrder(), university.getNameOfUniversity());
            preState.setString(OrderUtils.SECOND_STATEMENT.getOrder(), university.getAddress());
            preState.setString(OrderUtils.THIRD_STATEMENT.getOrder(), university.getSpecialization());
            return preState.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
            return 0;
        }
    }

    @Override
    public University getNewObjectFromResultSet(final ResultSet resultSet) throws SQLException {
        final University university = new University();
        university.setUniId(resultSet.getLong("id"));
        university.setNameOfUniversity(resultSet.getString("nameOfUniversity"));
        university.setAddress(resultSet.getString("address"));
        university.setSpecialization(resultSet.getString("specialization"));
        return university;
    }
}