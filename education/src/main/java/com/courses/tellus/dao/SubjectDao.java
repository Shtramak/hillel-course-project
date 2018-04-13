package com.courses.tellus.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.model.Subject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class SubjectDao implements BasicDao<Subject> {

    private final transient JdbcTemplate jdbcTemplate;

    public SubjectDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Subject> getAll() {
        final List<Subject> subjectList = jdbcTemplate.query("SELECT * FROM subject",
                new SubjectMapper());
        if (subjectList.size() > 0) {
            return subjectList;
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<Subject> getById(final Long entityId) {
        final String sql = "SELECT * FROM subject WHERE subject_id = ?";
        final Subject subject = jdbcTemplate.queryForObject(sql, new Object[]{entityId}, new SubjectMapper());
        return Optional.of(subject);
    }

    @Override
    public int update(final Subject entity) {
        final String sql = "UPDATE subject SET name = ?, descr = ?,"
                + " valid = ?, date_of_creation = ? WHERE subject_id= ?";
        return jdbcTemplate.update(sql, entity.getName(), entity.getDescription(), entity.isValid(),
                new Date(entity.getDateOfCreation()), entity.getSubjectId());
    }

    @Override
    public int delete(final Long entityId) {
        final String sql = "DELETE FROM subject WHERE subject_id = ?";
        return jdbcTemplate.update(sql, entityId);
    }

    @Override
    public int insert(final Subject entity) {
        final String sql = "INSERT INTO subject(name, descr, valid, date_of_creation)"
                + " VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, entity.getName(), entity.getDescription(), entity.isValid(),
                new Date(entity.getDateOfCreation()));
    }

    class SubjectMapper implements RowMapper<Subject> {
        @Override
        public Subject mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
                final Subject subject = new Subject();
                subject.setSubjectId(resultSet.getLong("subject_id"));
                subject.setName(resultSet.getString("name"));
                subject.setDescription(resultSet.getString("descr"));
                subject.setValid(resultSet.getBoolean("valid"));
                subject.setDateOfCreation(resultSet.getDate("date_of_creation").getTime());
                return subject;
        }
    }
}