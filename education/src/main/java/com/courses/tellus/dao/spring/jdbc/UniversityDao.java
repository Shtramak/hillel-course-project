package com.courses.tellus.dao.spring.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.model.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class UniversityDao implements BasicDao<University> {

    private final transient JdbcTemplate jdbcTemplate;

    @Autowired
    public UniversityDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<University> getAll() {
        final List<University> universityList = jdbcTemplate.query("SELECT * FROM Universities",
                new UniversityMapper());
        if (universityList.size() >= 1) {
            return universityList;
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<University> getById(final Long entityId) {
        final University university = jdbcTemplate.queryForObject("SELECT * FROM Universities WHERE univer_id = ?",
                new Object[]{entityId}, new UniversityMapper());
        return Optional.of(university);
    }

    @Override
    public int update(final University university) {
        final String sql = "UPDATE Universities SET name_of_university = ?, address = ?, specialization = ?"
                + "WHERE univer_id=?";
        return jdbcTemplate.update(sql, university.getNameOfUniversity(), university.getAddress(),
                university.getSpecialization(), university.getUniId());
    }

    @Override
    public int delete(final Long univerId) {
        final String sql = "DELETE FROM Universities WHERE univer_id = ?";
        return jdbcTemplate.update(sql, univerId);
    }

    @Override
    public int insert(final University university) {
        final String sql = "INSERT INTO Universities(name_of_university, address, specialization) "
                + "VALUES(?, ?, ?)";
        return jdbcTemplate.update(sql, university.getNameOfUniversity(), university.getAddress(),
                university.getSpecialization());
    }

    class UniversityMapper implements RowMapper<University> {
        @Override
        public University mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
            final University university = new University();
            university.setUniId(resultSet.getLong("univer_id"));
            university.setNameOfUniversity(resultSet.getString("name_of_university"));
            university.setAddress(resultSet.getString("address"));
            university.setSpecialization(resultSet.getString("specialization"));
            return university;
        }
    }
}
