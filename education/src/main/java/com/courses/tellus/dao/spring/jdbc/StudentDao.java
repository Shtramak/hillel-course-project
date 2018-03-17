package com.courses.tellus.dao.spring.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.entity.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class StudentDao implements BasicDao<Student> {

    private final transient JdbcTemplate jdbcTemplate;

    public StudentDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<List<Student>> getAll() {
        final List<Student> studentList = jdbcTemplate.query("SELECT * FROM education.Student",
                (resultSet, rowNum) -> toEntity(resultSet));
        if (studentList.size() >= 1) {
            return Optional.of(studentList);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Student> getById(final Long entityId) {
        final List<Student> students = jdbcTemplate.query("SELECT * FROM education.Student WHERE student_id = ?",
                new Object[]{entityId}, (resultSet, rowNum) -> toEntity(resultSet));
        if (students.size() == 1) {
            return Optional.of(students.get(0));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public int update(final Student student) {
        final String sql = "UPDATE education.Student SET firstName = ?, lastName = ?, student_card_number = ?,"
                + " address = ? WHERE student_id = ?";
        return jdbcTemplate.update(sql, student.getFirstName(), student.getLastName(),
                student.getStudentCardNumber(), student.getAddress(), student.getStudentId());
    }

    @Override
    public int delete(final Long studentId) {
        final String sql = "DELETE FROM education.Student WHERE student_id = ?";
        return jdbcTemplate.update(sql, studentId);
    }

    @Override
    public int insert(final Student student) {
        final String sql = "INSERT INTO education.Student(firstName, lastName, student_card_number, address) "
                + "VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, student.getFirstName(), student.getLastName(),
                student.getStudentCardNumber(), student.getAddress());
    }

    @Override
    public Student toEntity(final ResultSet resultSet) throws SQLException {
        final Student student = new Student();
        student.setStudentId(resultSet.getLong("student_id"));
        student.setFirstName(resultSet.getString("firstName"));
        student.setLastName(resultSet.getString("lastName"));
        student.setStudentCardNumber(resultSet.getString("student_card_number"));
        student.setAddress(resultSet.getString("address"));
        return student;
    }
}
