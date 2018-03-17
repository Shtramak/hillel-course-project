package com.courses.tellus.dao.spring.jdbc;


import com.courses.tellus.entity.University;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class UniversityDao implements BasicDao<University> {

    private JdbcTemplate jdbcTemplate;

    public UniversityDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<List<University>> getAll() {
        List<University> universityList = jdbcTemplate.query("SELECT * FROM education.Universities",
                (resultSet, rowNum) -> toEntity(resultSet));
        if (universityList.size() >= 1) {
            return Optional.of(universityList);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<University> getById(Long entityId) {
        List<University> university = jdbcTemplate.query("SELECT * FROM education.Universities WHERE univer_id = ?",
                new Object[]{entityId},
                (resultSet, rowNum) -> toEntity(resultSet));
        if (university.size() == 1) {
            return Optional.of(university.get(0));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public int update(University university) {
        String sql = "UPDATE education.Universities SET name_of_university = ?, address = ?, specialization = ?"
                + "WHERE univer_id=?";
        return jdbcTemplate.update(sql, university.getNameOfUniversity(), university.getAddress(),
                university.getSpecialization(), university.getUniId());
    }

    @Override
    public int delete(Long univer_Id) {
        String sql = "DELETE FROM education.Universities WHERE univer_id = ?";
        return jdbcTemplate.update(sql, univer_Id);
    }

    @Override
    public int insert(University university) {
        String sql = "INSERT INTO education.Universities(name_of_university, address, specialization) "
                + "VALUES(?, ?, ?)";
        return jdbcTemplate.update(sql, university.getNameOfUniversity(), university.getAddress(),
                university.getSpecialization());
    }

    @Override
    public University toEntity(ResultSet resultSet) throws SQLException {
        final University university = new University();
        university.setUniId(resultSet.getLong("univer_id"));
        university.setNameOfUniversity(resultSet.getString("name_of_university"));
        university.setAddress(resultSet.getString("address"));
        university.setSpecialization(resultSet.getString("specialization"));
        return university;
    }
}
