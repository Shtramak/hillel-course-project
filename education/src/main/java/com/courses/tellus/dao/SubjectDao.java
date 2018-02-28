package com.courses.tellus.dao;

import com.courses.tellus.db_connection.ConnectionFactory;
import com.courses.tellus.entity.Subject;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SubjectDao implements AbstractDao<Subject, Integer> {

    ConnectionFactory connectionFactory = null;

    @Override
    public List<Subject> getAll(){
        connectionFactory = new ConnectionFactory();
        List<Subject> subjectList = new ArrayList<>();
        try (Connection conn = get()) {
            final PreparedStatement ps = conn.prepareStatement("SELECT * FROM subject");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("descr");
                boolean valid = rs.getBoolean("valid");
                Date date = new Date(rs.getDate("date_of_creation").getTime());
                subjectList.add(new Subject(id, name, description, valid, date));
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
            final PreparedStatement ps = conn.prepareStatement("SELECT * FROM subject a WHERE a.id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                subject = new Subject();
                subject.setId(rs.getInt("id"));
                subject.setName(rs.getString("name"));
                subject.setDescription(rs.getString("descr"));
                subject.setValid(rs.getBoolean("valid"));
                subject.setDateOfCreation(new Date(rs.getDate("date_of_creation").getTime()));
            }
        } catch (SQLException except) {
            except.printStackTrace();
        }
        return subject;
    }

    @Override
    public void update (Subject subject) {
        connectionFactory = new ConnectionFactory();
        try (Connection conn = get()) {
            final PreparedStatement ps = conn.prepareStatement("UPDATE subject SET name = ?, descr = ?, valid = ?, date_of_creation = ? WHERE id = ?");
            ps.setString(1, subject.getName());
            ps.setString(2, subject.getDescription());
            ps.setBoolean(3, subject.isValid());
            ps.setDate(4, new java.sql.Date(subject.getDateOfCreation().getTime()));
            ps.setInt(5, subject.getId());
            ps.executeUpdate();
        } catch (SQLException except) {
            except.printStackTrace();
        }
    }

    @Override
    public void delete (Integer id) {
        connectionFactory = new ConnectionFactory();
        try (Connection conn = get()) {
            final PreparedStatement ps = conn.prepareStatement("DELETE FROM subject WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException except) {
            except.printStackTrace();
        }
    }

    @Override
    public void create (Subject subject) {
        connectionFactory = new ConnectionFactory();
        try (Connection conn = get()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO subject(name, descr, valid, date_of_creation) VALUES (?, ?, ?, ?)");
            ps.setString(1, subject.getName());
            ps.setString(2, subject.getDescription());
            ps.setBoolean(3, subject.isValid());
            ps.setDate(4, new java.sql.Date(subject.getDateOfCreation().getTime()));
            ps.executeUpdate();
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
