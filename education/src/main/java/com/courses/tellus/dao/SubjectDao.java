package com.courses.tellus.dao;

import com.courses.tellus.db_connection.ConnectionUtils;
import com.courses.tellus.entity.Subject;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SubjectDao implements AbstractDao<Subject, Integer> {

    private Connection conn;

    public SubjectDao() {
        conn = get();
    }

    public Connection getConnForClose() {
        return conn;
    }

    @Override
    public List<Subject> getAll() throws SQLException {

        String sql = "SELECT * FROM subject";
        List<Subject> subjectList = new ArrayList<>();

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String description = rs.getString("descr");
            boolean valid = rs.getBoolean("valid");
            Date date = new Date(rs.getDate("date_of_creation").getTime());

            subjectList.add(new Subject(id, name, description, valid, date));
        }

        return subjectList;
    }

    @Override
    public Subject getEntityById(Integer id) throws SQLException {

        String sql = "SELECT * FROM subject a WHERE a.id = ?";
        Subject subject = null;

        PreparedStatement ps = conn.prepareStatement(sql);
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

        return subject;
    }

    @Override
    public void update (Subject subject) throws SQLException {

        String sql = "UPDATE subject SET name = ?, descr = ?, valid = ?, date_of_creation = ? WHERE id = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, subject.getName());
        ps.setString(2, subject.getDescription());
        ps.setBoolean(3, subject.isValid());
        ps.setDate(4, new java.sql.Date(subject.getDateOfCreation().getTime()));
        ps.setInt(5, subject.getId());

        ps.executeUpdate();
    }

    @Override
    public void delete (Integer id) throws SQLException {

        String sql = "DELETE FROM subject WHERE id = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    @Override
    public void create (Subject subject) throws SQLException {

        String sql = "INSERT INTO subject(name, descr, valid, date_of_creation) VALUES (?, ?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, subject.getName());
        ps.setString(2, subject.getDescription());
        ps.setBoolean(3, subject.isValid());
        ps.setDate(4, new java.sql.Date(subject.getDateOfCreation().getTime()));

        ps.executeUpdate();
    }

    @Override
    public Connection get() {
        return ConnectionUtils.getConnection();
    }
}
