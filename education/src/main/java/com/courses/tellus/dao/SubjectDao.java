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

public class SubjectDao implements BasicDao<Subject, Integer> {

    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class);
    private final transient ConnectionFactory connectionFactory;

    public SubjectDao(final ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public List<Subject> getAllObject() throws SQLException {
        final List<Subject> subjectList = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection conn = get()) {
            resultSet = conn.prepareStatement("SELECT * FROM subject").executeQuery();
                while (resultSet.next()) {
                    subjectList.add(getNewObjectFromResultSet(resultSet));
                }
        } finally {
            resultSet.close();
        }
        return subjectList;
    }

    @Override
    public Subject getEntityById(final Integer entityId) throws SQLException {
        ResultSet resultSet = null;
        try (Connection conn = get()) {
            final PreparedStatement preState = conn.prepareStatement("SELECT * FROM subject a WHERE a.id = ?");
            preState.setInt(OrderUtil.FIFTH_STATEMENT.getOrder(), entityId);
            resultSet = preState.executeQuery();
            if (resultSet.next()) {
                return getNewObjectFromResultSet(resultSet);
            }
        } finally {
            resultSet.close();
        }
        return null;
    }

    @Override
    public void update(final Subject subject) throws SQLException {
        try (Connection conn = get()) {
            final PreparedStatement preState = conn.prepareStatement(
                    "UPDATE subject SET name = ?, descr = ?, valid = ?, date_of_creation = ? WHERE id = ?");
            preState.setString(OrderUtil.FIRST_STATEMENT.getOrder(), subject.getName());
            preState.setString(OrderUtil.SECOND_STATEMENT.getOrder(), subject.getDescription());
            preState.setBoolean(OrderUtil.THIRD_STATEMENT.getOrder(), subject.isValid());
            preState.setDate(OrderUtil.FOURTH_STATEMENT.getOrder(),
                    new java.sql.Date(subject.getDateOfCreation()));
            preState.setInt(OrderUtil.FIFTH_STATEMENT.getOrder(), subject.getSubjectId());
            preState.executeUpdate();
        }
    }

    @Override
    public void delete(final Integer entityId) throws SQLException {
        try (Connection conn = get()) {
            final PreparedStatement preState = conn.prepareStatement("DELETE FROM subject WHERE id = ?");
            preState.setInt(OrderUtil.FIRST_STATEMENT.getOrder(), entityId);
            preState.executeUpdate();
        }
    }

    @Override
    public void create(final Subject subject) throws SQLException {
        try (Connection conn = get()) {
            final PreparedStatement preState = conn.prepareStatement(
                    "INSERT INTO subject(name, descr, valid, date_of_creation) VALUES (?, ?, ?, ?)");
            preState.setString(OrderUtil.FIRST_STATEMENT.getOrder(), subject.getName());
            preState.setString(OrderUtil.SECOND_STATEMENT.getOrder(), subject.getDescription());
            preState.setBoolean(OrderUtil.THIRD_STATEMENT.getOrder(), subject.isValid());
            preState.setDate(OrderUtil.FOURTH_STATEMENT.getOrder(),
                    new java.sql.Date(subject.getDateOfCreation()));
            preState.executeUpdate();
        }
    }

    @Override
    public Subject getNewObjectFromResultSet(final ResultSet resultSet) throws SQLException {
        final Subject subject = new Subject();
        subject.setSubjectId(resultSet.getInt("id"));
        subject.setName(resultSet.getString("name"));
        subject.setDescription(resultSet.getString("descr"));
        subject.setValid(resultSet.getBoolean("valid"));
        subject.setDateOfCreation(resultSet.getDate("date_of_creation").getTime());
        return subject;
    }

    @Override
    public Connection get() {
        try {
            return connectionFactory.getConnection();
        } catch (SQLException except) {
            LOGGER.error(except.getMessage());
        }
        return null;
    }
}
