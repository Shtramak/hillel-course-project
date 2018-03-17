package com.courses.tellus.dao.spring.jdbc;

import com.courses.tellus.entity.Student;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class StudentDao implements BasicDao<Student> {

    private JdbcTemplate jdbcTemplate;
    private final Logger LOGGER= Logger.getLogger(StudentDao.class);

    public StudentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<List<Student>> getAll() {
        List<Student> studentList = jdbcTemplate.query("SELECT * FROM education.Student",
                (resultSet, rowNum) -> toEntity(resultSet));
        if (studentList.size() >= 1) {
            return Optional.of(studentList);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Student> getById(Long entityId) {
        List<Student> students = jdbcTemplate.query("SELECT * FROM education.Student WHERE student_id = ?",
                new Object[]{entityId},
                (resultSet, rowNum) -> toEntity(resultSet));
        if (students.size() == 1) {
            return Optional.of(students.get(0));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public int update(Student student) {
        String sql = "UPDATE education.Student SET firstName = ?, lastName = ?, student_card_number = ?,"
                + " address = ? WHERE student_id = ?";
        return jdbcTemplate.update(sql, student.getFirstName(), student.getLastName(),
                student.getStudentCardNumber(), student.getAddress(), student.getStudentId());
    }

    @Override
    public int delete(Long student_Id) {
        String sql = "DELETE FROM education.Student WHERE student_id = ?";
        return jdbcTemplate.update(sql, student_Id);
    }

    @Override
    public int insert(Student student) {
        String sql = "INSERT INTO education.Student(firstName, lastName, student_card_number, address) " +
                "VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, student.getFirstName(), student.getLastName(),
                student.getStudentCardNumber(), student.getAddress());
    }

    @Override
    public Student toEntity(ResultSet resultSet) throws SQLException {
        final Student student = new Student();
        student.setStudentId(resultSet.getLong("student_id"));
        student.setFirstName(resultSet.getString("firstName"));
        student.setLastName(resultSet.getString("lastName"));
        student.setStudentCardNumber(resultSet.getString("student_card_number"));
        student.setAddress(resultSet.getString("address"));
        return student;
    }
}
