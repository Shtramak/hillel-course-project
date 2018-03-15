package com.courses.tellus.dao.spring.jdbc;


import com.courses.tellus.entity.University;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class UniversityDao implements BasicDao<University> {

    private JdbcTemplate jdbcTemplate;
    private final Logger LOGGER= Logger.getLogger(UniversityDao.class);

    public UniversityDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<List<University>> getAll() {
        List<University> universityList = jdbcTemplate.query("SELECT * FROM Universities",
                (resultSet, rowNum) -> getNewObjectFromResultSet(resultSet));
        if (universityList.size() >= 1) {
            return Optional.of(universityList);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<University> getById(Long entityId) {
        List<University> university = jdbcTemplate.query("SELECT * FROM Universities WHERE univer_id = ?",
                new Object[]{entityId},
                (resultSet, rowNum) -> getNewObjectFromResultSet(resultSet));
        if (university.size() == 1) {
            return Optional.of(university.get(0));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public int update(University university) {
        String sql = "UPDATE Uinversities SET name_of_university = ?, address = ?, specialization = ?"
                + "WHERE univer_id=?";
        return jdbcTemplate.update(sql, university.getNameOfUniversity(), university.getAddress(),
                university.getSpecialization(), university.getUniId());
    }

    @Override
    public int delete(Long univer_Id) {
        String sql = "DELETE FROM Universities WHERE univer_id = ?";
        return jdbcTemplate.update(sql, univer_Id);
    }

    @Override
    public int insert(University university) {
        String sql = "INSERT INTO Universities(name_of_university, address, specialization) "
                + "VALUES(?, ?, ?)";
        return jdbcTemplate.update(sql, university.getNameOfUniversity(), university.getAddress(),
                university.getSpecialization());
    }

    @Override
    public University getNewObjectFromResultSet (ResultSet resultSet) throws SQLException {
        final University university = new University();
        university.setUniId(resultSet.getLong("univer_id"));
        university.setNameOfUniversity(resultSet.getString("name_of_university"));
        university.setAddress(resultSet.getString("address"));
        university.setSpecialization(resultSet.getString("specialization"));
        return university;
    }
}
