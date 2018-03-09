package com.courses.tellus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.courses.tellus.dbconnection.ConnectionFactory;
import com.courses.tellus.entity.Subject;
import org.apache.log4j.Logger;

public class SubjectDao implements BasicDao<Subject> {

    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class);
    private final transient ConnectionFactory connectionFactory;

    public SubjectDao(final ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public List<Subject> getAllEntity() {
        ResultSet resultSet;
        final List<Subject> subjectList = new ArrayList<>();
        try (Connection conn = connectionFactory.getConnection()) {
            final PreparedStatement preState = conn.prepareStatement("SELECT * FROM SUBJECT");
            resultSet = preState.executeQuery();
            while (resultSet.next()) {
                subjectList.add(getNewObjectFromResultSet(resultSet));
            }
        } catch (SQLException except) {
            LOGGER.error(except);
            return null;
        }
        return subjectList;
    }

    @Override
    public Subject getEntityById(final Long entityId) {
        ResultSet resultSet;
        try (Connection conn = connectionFactory.getConnection()) {
            final PreparedStatement preState = conn.prepareStatement("SELECT * FROM SUBJECT a WHERE a.id = ?");
            preState.setLong(OrderUtils.FIRST_STATEMENT.getOrder(), entityId);
            resultSet = preState.executeQuery();
            if (resultSet.next()) {
                return getNewObjectFromResultSet(resultSet);
            }
        } catch (SQLException except) {
            LOGGER.error(except);
            return null;
        }
        return null;
    }

    @Override
    public boolean update(final Subject subject) {
        try (Connection conn = connectionFactory.getConnection()) {
            final PreparedStatement preState = conn.prepareStatement(
                    "UPDATE SUBJECT SET name = ?, descr = ?, valid = ?, date_of_creation = ? WHERE id = ?");
            preState.setString(OrderUtils.FIRST_STATEMENT.getOrder(), subject.getName());
            preState.setString(OrderUtils.SECOND_STATEMENT.getOrder(), subject.getDescription());
            preState.setBoolean(OrderUtils.THIRD_STATEMENT.getOrder(), subject.isValid());
            preState.setDate(OrderUtils.FOURTH_STATEMENT.getOrder(), new java.sql.Date(subject.getDateOfCreation()));
            preState.setLong(OrderUtils.FIFTH_STATEMENT.getOrder(), subject.getSubjectId());
            preState.executeUpdate();
        } catch (SQLException except) {
            LOGGER.error(except);
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(final Long entityId) {
        try (Connection conn = connectionFactory.getConnection()) {
            final PreparedStatement preState = conn.prepareStatement("DELETE FROM SUBJECT WHERE id = ?");
            preState.setLong(OrderUtils.FIRST_STATEMENT.getOrder(), entityId);
            preState.executeUpdate();
        } catch (SQLException except) {
            LOGGER.error(except);
            return false;
        }
        return true;
    }

    @Override
    public boolean insert(final Subject subject) {
        try (Connection conn = connectionFactory.getConnection()) {
            final PreparedStatement preState = conn.prepareStatement(
                    "INSERT INTO PUBLIC.SUBJECT(name, descr, valid, date_of_creation) VALUES (?, ?, ?, ?)");
            preState.setString(OrderUtils.FIRST_STATEMENT.getOrder(), subject.getName());
            preState.setString(OrderUtils.SECOND_STATEMENT.getOrder(), subject.getDescription());
            preState.setBoolean(OrderUtils.THIRD_STATEMENT.getOrder(), subject.isValid());
            preState.setDate(OrderUtils.FOURTH_STATEMENT.getOrder(),
                    new java.sql.Date(subject.getDateOfCreation()));
            preState.executeUpdate();
        } catch (SQLException except) {
            LOGGER.error(except);
            return false;
        }
        return true;
    }

    @Override
    public Subject getNewObjectFromResultSet(final ResultSet resultSet) throws SQLException {
        final Subject subject = new Subject();
        subject.setSubjectId(resultSet.getLong("id"));
        subject.setName(resultSet.getString("name"));
        subject.setDescription(resultSet.getString("descr"));
        subject.setValid(resultSet.getBoolean("valid"));
        subject.setDateOfCreation(resultSet.getDate("date_of_creation").getTime());
        return subject;
    }
}
