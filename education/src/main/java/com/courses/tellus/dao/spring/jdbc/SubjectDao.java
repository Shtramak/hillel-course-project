package com.courses.tellus.dao.spring.jdbc;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.entity.Subject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class SubjectDao implements BasicDao<Subject> {

    private final transient JdbcTemplate jdbcTemplate;

    public SubjectDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<List<Subject>> getAll() {
        final List<Subject> subjectList = jdbcTemplate.query("SELECT * FROM education.subject",
                (resultSet, rowNum) -> toEntity(resultSet));
        if (subjectList.size() > 0) {
            return Optional.of(subjectList);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Subject> getById(final Long entityId) {
        final String sql = "SELECT * FROM education.subject WHERE subject_id = ?";
        final List<Subject> subject = jdbcTemplate.query(sql, new Object[]{entityId},
                (resultSet, rowNum) -> toEntity(resultSet));
        if (subject.size() == 1) {
            return Optional.of(subject.get(0));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public int update(final Subject entity) {
        final String sql = "UPDATE education.subject SET name = ?, descr = ?,"
                + " valid = ?, date_of_creation = ? WHERE subject_id= ?";
        return jdbcTemplate.update(sql, entity.getName(), entity.getDescription(), entity.isValid(),
                new Date(entity.getDateOfCreation()), entity.getSubjectId());
    }

    @Override
    public int delete(final Long entityId) {
        final String sql = "DELETE FROM education.subject WHERE subject_id = ?";
        return jdbcTemplate.update(sql, entityId);
    }

    @Override
    public int insert(final Subject entity) {
        final String sql = "INSERT INTO education.subject(name, descr, valid, date_of_creation)"
                + " VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, entity.getName(), entity.getDescription(), entity.isValid(),
                new Date(entity.getDateOfCreation()));
    }

    @Override
    public Subject toEntity(final ResultSet resultSet) throws SQLException {
        final Subject subject = new Subject();
        subject.setSubjectId(resultSet.getLong("subject_id"));
        subject.setName(resultSet.getString("name"));
        subject.setDescription(resultSet.getString("descr"));
        subject.setValid(resultSet.getBoolean("valid"));
        subject.setDateOfCreation(resultSet.getDate("date_of_creation").getTime());
        return subject;
    }
}