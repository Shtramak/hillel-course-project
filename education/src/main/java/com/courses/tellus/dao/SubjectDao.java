package com.courses.tellus.dao;

import com.courses.tellus.dbconnection.ConnectionFactory;
import com.courses.tellus.entity.Subject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectDao implements AbstractDao<Subject, Integer> {

    private ConnectionFactory connectionFactory = null;

    @Override
    public List<Subject> getAll() {
        connectionFactory = new ConnectionFactory();
        List<Subject> subjectList = new ArrayList<>();
        try (Connection conn = get()) {
            final PreparedStatement preState = conn.prepareStatement("SELECT * FROM subject");
            ResultSet resultSet = preState.executeQuery();
            while (resultSet.next()) {
                int subjId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("descr");
                boolean valid = resultSet.getBoolean("valid");
                Date date = new Date(resultSet.getDate("date_of_creation").getTime());
                subjectList.add(new Subject(subjId, name, description, valid, date));
            }
        } catch (SQLException except) {
            except.printStackTrace();
        }
        return subjectList;
    }

    @Override
    public Subject getEntityById(Integer id) {
        connectionFactory = new ConnectionFactory();
        Subject subject = null;
        try (Connection conn = get()) {
            final PreparedStatement preState = conn.prepareStatement("SELECT * FROM subject a WHERE a.id = ?");
            preState.setInt(1, id);
            ResultSet resultSet = preState.executeQuery();
            if (resultSet.next()) {
                subject = new Subject();
                subject.setId(resultSet.getInt("id"));
                subject.setName(resultSet.getString("name"));
                subject.setDescription(resultSet.getString("descr"));
                subject.setValid(resultSet.getBoolean("valid"));
                subject.setDateOfCreation(new Date(resultSet.getDate("date_of_creation").getTime()));
            }
        } catch (SQLException except) {
            except.printStackTrace();
        }
        return subject;
    }

    @Override
    public void update(Subject subject) {
        connectionFactory = new ConnectionFactory();
        try (Connection conn = get()) {
            final PreparedStatement preState = conn.prepareStatement(
                    "UPDATE subject SET name = ?, descr = ?, valid = ?, date_of_creation = ? WHERE id = ?");
            preState.setString(1, subject.getName());
            preState.setString(2, subject.getDescription());
            preState.setBoolean(3, subject.isValid());
            preState.setDate(4, new java.sql.Date(subject.getDateOfCreation().getTime()));
            preState.setInt(5, subject.getId());
            preState.executeUpdate();
        } catch (SQLException except) {
            except.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        connectionFactory = new ConnectionFactory();
        try (Connection conn = get()) {
            final PreparedStatement preState = conn.prepareStatement("DELETE FROM subject WHERE id = ?");
            preState.setInt(1, id);
            preState.executeUpdate();
        } catch (SQLException except) {
            except.printStackTrace();
        }
    }

    @Override
    public void create(Subject subject) {
        connectionFactory = new ConnectionFactory();
        try (Connection conn = get()) {
            PreparedStatement preState = conn.prepareStatement(
                    "INSERT INTO subject(name, descr, valid, date_of_creation) VALUES (?, ?, ?, ?)");
            preState.setString(1, subject.getName());
            preState.setString(2, subject.getDescription());
            preState.setBoolean(3, subject.isValid());
            preState.setDate(4, new java.sql.Date(subject.getDateOfCreation().getTime()));
            preState.executeUpdate();
        } catch (SQLException except) {
            except.printStackTrace();
        }
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
