package com.courses.tellus.dao.spring.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.courses.tellus.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

class StudentDaoMockTest {

    @Mock private JdbcTemplate jdbcTemplate;
    @InjectMocks private StudentDao studentDao;
    private Student student;
    @Mock private ResultSet mockResultSet;

    @BeforeEach
    void mockInit() throws Exception {
        MockitoAnnotations.initMocks(this);
        student = new Student(1L,"Givi", "Trump", "37773",
                "Street 23");
    }

    @Test
    void testGetAllWhenReturnEntities() throws Exception {
        List<Student> studentList = new ArrayList<>();
        List<Student> spy = spy(studentList);
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            RowMapper<Student> rm = (RowMapper<Student>) args[1];
            toEntity();
            Student actual = rm.mapRow(mockResultSet, 0);
            assertEquals(student, actual);
            return spy;
        });
        when(spy.size()).thenReturn(1);
        assertEquals(spy.size(), studentDao.getAll().size());
    }

    @Test
    void testGetAllWhenReturnFalse() throws Exception {
        List<Student> studentList = new ArrayList<>();
        List<Student> spy = spy(studentList);
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(spy);
        when(spy.size()).thenReturn(0);
        assertTrue(studentDao.getAll().size() == 0);
    }

    @Test
    void testGetByIdWhenReturnEntity() {
        final List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        List<Student> spy = spy(studentList);
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            RowMapper<Student> rm = (RowMapper<Student>) args[2];
            toEntity();
            Student actual = rm.mapRow(mockResultSet, 0);
            assertEquals(student, actual);
            return spy;
        });
        when(spy.size()).thenReturn(1);
        when(spy.get(anyInt())).thenReturn(student);
        assertEquals(student, studentDao.getById(1L).get());
    }

    @Test
    void testGetByIdWhenReturnFalse() {
        final List<Student> studentList = new ArrayList<>();
        List<Student> spy = spy(studentList);
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(spy);
        when(spy.size()).thenReturn(0);
        assertFalse(studentDao.getById(1L).isPresent());
    }

    @Test
    void testInsert() {
        when(studentDao.insert(student)).thenReturn(1);
        assertEquals(1, studentDao.insert(student));
    }

    @Test
    void testDelete() {
        when(studentDao.delete(1L)).thenReturn(1);
        assertEquals(1, studentDao.delete(1L));
    }

    @Test
    void testUpdate() {
        when(studentDao.update(student)).thenReturn(1);
        assertEquals(1, studentDao.update(student));
    }

    private void toEntity() throws SQLException {
       when(mockResultSet.getLong("student_id")).thenReturn(student.getStudentId());
       when(mockResultSet.getString("firstName")).thenReturn(student.getFirstName());
       when(mockResultSet.getString("lastName")).thenReturn(student.getLastName());
       when(mockResultSet.getString("student_card_number")).thenReturn(student.getStudentCardNumber());
       when(mockResultSet.getString("address")).thenReturn(student.getAddress());
}}
