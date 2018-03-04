package com.courses.tellus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.courses.tellus.dbconnection.ConnectionFactory;
import com.courses.tellus.entity.University;

public class UniversityDao implements BasicDao<University, Integer> {

    private transient Connection connection = get();
    private transient ConnectionFactory connectionFactory;

    public UniversityDao(final ConnectionFactory connectionFactory) {
    this.connectionFactory = connectionFactory;
    }

    @Override
    public List<University> getAllObject() {
      final List<University> universityList = new ArrayList<University>();
        try (PreparedStatement prepSt = connection.prepareStatement("SELECT*FROM Universities")) {
            try (ResultSet rSet = prepSt.executeQuery()) {
                while (rSet.next()) {
                    getNewObjectFromResultSet(rSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return universityList;
    }

    @Override
    public University getEntityById(final Integer entityId)  {
        final University universityById = new University();
        try (PreparedStatement prepSt = connection.prepareStatement("SELECT*FROM Universities WHERE id = ?")) {
            prepSt.setInt(OrderUtil.FIRST_STATEMENT.getOrder(), entityId);
            prepSt.executeQuery();
            try (ResultSet rSet = prepSt.executeQuery()) {
                while (rSet.next()) {
                    getNewObjectFromResultSet(rSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return universityById;
    }

    @Override
    public int update(final University university) {
        try (PreparedStatement prepSt = connection.prepareStatement("UPDATE Universities SET"
                + " nameOfUniversity = ?, address = ?, specialization = ?"
                + "WHERE id = ?")) {
            prepSt.setString(OrderUtil.FIRST_STATEMENT.getOrder(), university.getNameOfUniversity());
            prepSt.setString(OrderUtil.SECOND_STATEMENT.getOrder(), university.getAddress());
            prepSt.setString(OrderUtil.THIRD_STATEMENT.getOrder(), university.getSpecialization());
            prepSt.setInt(OrderUtil.FOURTH_STATEMENT.getOrder(), university.getUniId());
            prepSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(final Integer entityId)  {
        try (PreparedStatement prepSt = connection.prepareStatement("DELETE FROM Universities "
                + "WHERE id = ?")) {
            prepSt.setInt(OrderUtil.FIRST_STATEMENT.getOrder(), entityId);
            prepSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  0;
    }

    @Override
    public int create(final University university) {
        try (PreparedStatement prepSt = connection.prepareStatement("INSERT INTO Universities(nameOfUniversity,"
                + "address,specialization)"
                + "VALUES (?,?,?)")) {
              final String nameOfUniversity = university.getNameOfUniversity();
              final String address = university.getAddress();
              final String specialization = university.getSpecialization();
                prepSt.setString(OrderUtil.FIRST_STATEMENT.getOrder(), nameOfUniversity);
                prepSt.setString(OrderUtil.SECOND_STATEMENT.getOrder(), address);
                prepSt.setString(OrderUtil.THIRD_STATEMENT.getOrder(), specialization);
                prepSt.executeUpdate();
            } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public University getNewObjectFromResultSet(final ResultSet resultSet)  {
        final University university = new University();
        try {
            university.setNameOfUniversity(resultSet.getString("nameOfUniversity"));
            university.setAddress(resultSet.getString("address"));
            university.setSpecialization(resultSet.getString("specialization"));
            university.setUniId(resultSet.getInt("Id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return university;
    }

    @Override
    public Connection get() {
        try {
            return connectionFactory.getConnection();
        } catch (SQLException e) {
        e.printStackTrace();
        }
        return null;
    }
}
