package com.courses.tellus.persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.config.jdbc.ConnectionFactory;
import com.courses.tellus.entity.model.Student;
import com.courses.tellus.persistence.BasicDao;
import org.apache.log4j.Logger;

public class StudentDao implements BasicDao<Student> {

    private static final Logger LOGGER = Logger.getLogger(StudentDao.class);
    private final transient ConnectionFactory connectionFactory;

    public StudentDao(final ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public List<Student> getAll() {
        final List<Student> studentList = new ArrayList<>();
        try (Connection conn = connectionFactory.getConnection()) {
            final PreparedStatement preState = conn.prepareStatement("SELECT * FROM STUDENT");
            final ResultSet resultSet = preState.executeQuery();
            while (resultSet.next()) {
                studentList.add(getNewObjectFromResultSet(resultSet));
            }
            return studentList;
        } catch (SQLException except) {
            LOGGER.error(except.getCause(), except);
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<Student> getById(final Long entityId) {
        try (Connection conn = connectionFactory.getConnection()) {
            final PreparedStatement preState = conn
                    .prepareStatement("SELECT * FROM STUDENT a WHERE a.student_id = ?");
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
    public int update(final Student student) {
        try (Connection conn = connectionFactory.getConnection()) {
            final PreparedStatement preState = conn
                    .prepareStatement("UPDATE STUDENT SET firstName = ?, lastName = ?, student_card_number = ?,"
                            + " address = ? WHERE student_id = ?");
            preState.setString(OrderUtils.FIRST_STATEMENT.getOrder(), student.getFirstName());
            preState.setString(OrderUtils.SECOND_STATEMENT.getOrder(), student.getLastName());
            preState.setString(OrderUtils.THIRD_STATEMENT.getOrder(), student.getStudentCardNumber());
            preState.setString(OrderUtils.FOURTH_STATEMENT.getOrder(), student.getAddress());
            preState.setLong(OrderUtils.FIFTH_STATEMENT.getOrder(), student.getStudentId());
            return preState.executeUpdate();
        } catch (SQLException except) {
            LOGGER.error(except.getCause(), except);
            return 0;
        }
    }

    @Override
    public int delete(final Long entityId) {
        try (Connection conn = connectionFactory.getConnection()) {
            final PreparedStatement preState = conn.prepareStatement("DELETE FROM STUDENT WHERE student_id = ?");
            preState.setLong(OrderUtils.FIRST_STATEMENT.getOrder(), entityId);
            return preState.executeUpdate();
        } catch (SQLException except) {
            LOGGER.error(except.getCause(), except);
            return 0;
        }
    }

    @Override
    public int insert(final Student student) {
        try (Connection conn = connectionFactory.getConnection())  {
            final PreparedStatement preState = conn.prepareStatement(
                    "INSERT INTO STUDENT(firstName, lastName, student_card_number, address) VALUES (?, ?, ?, ?)");
            preState.setString(OrderUtils.FIRST_STATEMENT.getOrder(), student.getFirstName());
            preState.setString(OrderUtils.SECOND_STATEMENT.getOrder(), student.getLastName());
            preState.setString(OrderUtils.THIRD_STATEMENT.getOrder(), student.getStudentCardNumber());
            preState.setString(OrderUtils.FOURTH_STATEMENT.getOrder(), student.getAddress());
            return preState.executeUpdate();
        } catch (SQLException except) {
            LOGGER.error(except.getCause(), except);
            return 0;
        }
    }

    private Student getNewObjectFromResultSet(final ResultSet resultSet) throws SQLException {
        final Student student = new Student();
        student.setStudentId(resultSet.getLong("student_id"));
        student.setFirstName(resultSet.getString("firstName"));
        student.setLastName(resultSet.getString("lastName"));
        student.setStudentCardNumber(resultSet.getString("student_card_number"));
        student.setAddress(resultSet.getString("address"));
        return student;
    }
}