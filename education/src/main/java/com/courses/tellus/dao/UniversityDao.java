package com.courses.tellus.dao;

import com.courses.tellus.db_connection.ConnectionFactory;
import com.courses.tellus.entity.University;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UniversityDao implements BasicDao<University , Integer> {

    private Connection connection =get();
    private transient ConnectionFactory connectionFactory;

    public UniversityDao(ConnectionFactory connectionFactory) {
    this.connectionFactory=connectionFactory;
    }

    @Override
    public List<University> getAllObject()  {
        List<University> listOfUniversities = new ArrayList<University>();
        try (PreparedStatement ps=connection.prepareStatement("SELECT*FROM Universities")){
            try(ResultSet rSet=ps.executeQuery()) {
                while (rSet.next()) {
                    getNewObjectFromResultSet(rSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfUniversities;
    }

    @Override
    public University getEntityById(Integer entityId)  {

        University universityById = new University();
        try (PreparedStatement ps=connection.prepareStatement("SELECT*FROM Universities WHERE id=?")) {
            ps.setInt(1, entityId);
            ps.executeQuery();
            try (ResultSet rSet = ps.executeQuery()) {
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
    public int update(University university) {
        try (PreparedStatement ps= connection.prepareStatement
                ("UPDATE Universities SET" + " nameOfUniversity = ? , address =?, specialization =?" +
                        "WHERE id= ?")) {

            ps.setString(1, university.getNameOfUniversity());
            ps.setString(2, university.getAddress());
            ps.setString(3, university.getSpecialization());
            ps.setInt(4,university.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(Integer entityId)  {
        try (PreparedStatement ps=connection.prepareStatement("DELETE FROM Universities " +
                "WHERE id=?"))
        {
            ps.setInt(1,entityId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  0;
    }

    @Override
    public int create(University entity) {
        University university;
        try (PreparedStatement ps = connection.prepareStatement
                    ("INSERT INTO Universities(nameOfUniversity,address,specialization)" +
                            "VALUES (?,?,?)")) {
                university = (University) entity;
                String nameOfUniversity = university.getNameOfUniversity();
                String address = university.getAddress();
                String specialization = university.getSpecialization();
                ps.setString(1, nameOfUniversity);
                ps.setString(2, address);
                ps.setString(3, specialization);
                ps.executeUpdate();
            } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    @Override
    public University getNewObjectFromResultSet(ResultSet resultSet)  {
        final University university = new University();
        try {
            university.setNameOfUniversity(resultSet.getString("nameOfUniversity"));
            university.setAddress(resultSet.getString("address"));
            university.setSpecialization(resultSet.getString("specialization"));
            university.setId(resultSet.getInt("Id"));
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
