package com.courses.tellus.persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.config.jdbc.ConnectionFactory;
import com.courses.tellus.entity.model.Subject;
import com.courses.tellus.persistence.BasicDao;
import org.apache.log4j.Logger;

public class SubjectDao implements BasicDao<Subject> {

    private static final Logger LOGGER = Logger.getLogger(SubjectDao.class);
    private final transient ConnectionFactory connectionFactory;

    public SubjectDao(final ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public List<Subject> getAll() {
        final List<Subject> subjectList = new ArrayList<>();
        try (Connection conn = connectionFactory.getConnection()) {
            final PreparedStatement preState = conn.prepareStatement("SELECT * FROM SUBJECT");
            final ResultSet resultSet = preState.executeQuery();
            while (resultSet.next()) {
                subjectList.add(getNewObjectFromResultSet(resultSet));
            }
            return subjectList;
        } catch (SQLException except) {
            LOGGER.error(except.getCause(), except);
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<Subject> getById(final Long entityId) {
        try (Connection conn = connectionFactory.getConnection()) {
            final PreparedStatement preState = conn
                    .prepareStatement("SELECT * FROM SUBJECT a WHERE a.subject_id = ?");
            preState.setLong(OrderUtils.FIRST_STATEMENT.getOrder(), entityId);
            final ResultSet resultSet = preState.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getNewObjectFromResultSet(resultSet));
            }
            return Optional.empty();
        } catch (SQLException except) {
            LOGGER.error(except.getCause(), except);
            return Optional.empty();
        }
    }

    @Override
    public int update(final Subject subject) {
        try (Connection conn = connectionFactory.getConnection()) {
            final PreparedStatement preState = conn.prepareStatement(
                    "UPDATE SUBJECT SET name = ?, descr = ?, valid = ?, date_of_creation = ? WHERE subject_id= ?");
            preState.setString(OrderUtils.FIRST_STATEMENT.getOrder(), subject.getName());
            preState.setString(OrderUtils.SECOND_STATEMENT.getOrder(), subject.getDescription());
            preState.setBoolean(OrderUtils.THIRD_STATEMENT.getOrder(), subject.isValid());
            preState.setDate(OrderUtils.FOURTH_STATEMENT.getOrder(), Date.valueOf(subject.getDateOfCreation()));
            preState.setLong(OrderUtils.FIFTH_STATEMENT.getOrder(), subject.getSubjectId());
            return preState.executeUpdate();
        } catch (SQLException except) {
            LOGGER.error(except.getCause(), except);
            return 0;
        }
    }

    @Override
    public int delete(final Long entityId) {
        try (Connection conn = connectionFactory.getConnection()) {
            final PreparedStatement preState = conn.prepareStatement("DELETE FROM SUBJECT WHERE subject_id = ?");
            preState.setLong(OrderUtils.FIRST_STATEMENT.getOrder(), entityId);
            return preState.executeUpdate();
        } catch (SQLException except) {
            LOGGER.error(except.getCause(), except);
            return 0;
        }
    }

    @Override
    public int insert(final Subject subject) {
        try (Connection conn = connectionFactory.getConnection()) {
            final PreparedStatement preState = conn.prepareStatement(
                    "INSERT INTO SUBJECT(name, descr, valid, date_of_creation) VALUES (?, ?, ?, ?)");
            preState.setString(OrderUtils.FIRST_STATEMENT.getOrder(), subject.getName());
            preState.setString(OrderUtils.SECOND_STATEMENT.getOrder(), subject.getDescription());
            preState.setBoolean(OrderUtils.THIRD_STATEMENT.getOrder(), subject.isValid());
            preState.setDate(OrderUtils.FOURTH_STATEMENT.getOrder(), Date.valueOf(subject.getDateOfCreation()));
            return preState.executeUpdate();
        } catch (SQLException except) {
            LOGGER.error(except.getCause(), except);
            return 0;
        }
    }

    private Subject getNewObjectFromResultSet(final ResultSet resultSet) throws SQLException {
        final Subject subject = new Subject();
        subject.setSubjectId(resultSet.getLong("subject_id"));
        subject.setName(resultSet.getString("name"));
        subject.setDescription(resultSet.getString("descr"));
        subject.setValid(resultSet.getBoolean("valid"));
        subject.setDateOfCreation(resultSet.getDate("date_of_creation").toLocalDate());
        return subject;
    }
}