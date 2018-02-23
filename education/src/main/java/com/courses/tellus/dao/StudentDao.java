package com.courses.tellus.dao;

import com.courses.tellus.entity.Student;


import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao implements AbstractDao<Student, Long> {

    private DataSource dataSource;

    public StudentDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Student> getAll() throws SQLException {
        List<Student> pampers = new ArrayList<>();
        try (Connection connection = get();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Pampers")) {
            while (resultSet.next())
                pampers.add(new Student(
                        resultSet.getLong("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("uniqueRegistrationNumber"),
                        resultSet.getString("address")));
        }
        return pampers;
    }

    @Override
    public Student getEntityById(Long id) throws SQLException {
        try (Connection connection = get();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Pampers WHERE id=?")) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? new Student(
                        resultSet.getLong("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("uniqueRegistrationNumber"),
                        resultSet.getString("address")) : null;
            }
        }
    }

    @Override
    public void update(Student entity) throws SQLException {
        try (Connection connection = get();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO Student (firstName, lastName, uniqueRegistrationNumber, address) " +
                             "VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setString(3, entity.getUniqueRegistrationNumber());
            statement.setString(4, entity.getAddress());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                generatedKeys.next();
                entity.setId(generatedKeys.getInt(1));
            }
        }
    }

      /*
     private long id;
    private String firstName;
    private String lastName;
    private String uniqueRegistrationNumber;
    private String address;
     */

    @Override
    public void delete(Long id) throws SQLException {
        try (Connection connection = get();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Student WHERE id =?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    @Override
    public void create(Student entity) throws SQLException {
        try (Connection connection = get();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE Student SET firstName=?, lastName=?, uniqueRegistrationNumber=?, address=? WHERE id=?")) {
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setString(3, entity.getUniqueRegistrationNumber());
            preparedStatement.setString(4, entity.getAddress());
            preparedStatement.setLong(5, entity.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public Connection get() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
