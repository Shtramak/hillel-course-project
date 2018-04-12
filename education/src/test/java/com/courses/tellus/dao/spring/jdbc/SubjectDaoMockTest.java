package com.courses.tellus.dao.spring.jdbc;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import com.courses.tellus.entity.Subject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.spy;

class SubjectDaoMockTest {

    @Mock private JdbcTemplate jdbcTemplate;
    @InjectMocks private SubjectDao subjectDao;
    private Subject subject;
    @Mock private ResultSet mockResSet;

    @BeforeEach
    void mockInit() throws Exception {
        MockitoAnnotations.initMocks(this);
        subject = new Subject(1L, "Law", "Lesson about orders of Ukraine", true,
                "12/5/2000");
    }

    @Test
    void testGetAllAndReturnEntityListInOptional() throws Exception {
        List<Subject> subjectList = new ArrayList<>();
        List<Subject> spy = spy(subjectList);
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            RowMapper<Subject> rm = (RowMapper<Subject>) args[1];
            toEntity();
            Subject actual = rm.mapRow(mockResSet, 0);
            assertEquals(subject, actual);
            return spy;
        });
        when(spy.size()).thenReturn(1);
        Assertions.assertEquals(spy.size(), subjectDao.getAll().size());
    }

    @Test
    void testGetAllAndReturnFalseByOptional() throws Exception {
        List<Subject> subjectList = new ArrayList<>();
        List<Subject> spy = spy(subjectList);
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(spy);
        when(spy.size()).thenReturn(0);
        Assertions.assertTrue(subjectDao.getAll().size() == 0);
    }

    @Test
    void testGetByIdAndReturnEntityInOptional() {
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(RowMapper.class)))
                .thenAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            RowMapper<Subject> rm = (RowMapper<Subject>) args[2];
            toEntity();
            Subject actual = rm.mapRow(mockResSet, 0);
            assertEquals(subject, actual);
            return subject;
        });
        assertEquals(subject, subjectDao.getById(1L).get());
    }

    @Test
    void testGetByIdAndReturnFalseInOptional() {
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(RowMapper.class)))
                .thenThrow(new EmptyResultDataAccessException(0));
        assertThrows(EmptyResultDataAccessException.class, () -> subjectDao.getById(10L));
    }

    @Test
    void testInsert() {
        when(subjectDao.insert(subject)).thenReturn(1);
        Assertions.assertTrue(subjectDao.insert(subject) == 1);
    }

    @Test
    void testDelete() {
        when(subjectDao.delete(1L)).thenReturn(1);
        assertEquals(1, subjectDao.delete(1L));
    }

    @Test
    void testUpdate() {
        when(subjectDao.update(subject)).thenReturn(1);
        Assertions.assertTrue(subjectDao.update(subject) == 1);
    }

    private void toEntity() throws SQLException {
        Date mockSqlDate = mock(Date.class);
        when(mockResSet.getLong("subject_id")).thenReturn(subject.getSubjectId());
        when(mockResSet.getString("name")).thenReturn(subject.getName());
        when(mockResSet.getString("descr")).thenReturn(subject.getDescription());
        when(mockResSet.getBoolean("valid")).thenReturn(subject.isValid());
        when(mockResSet.getDate("date_of_creation")).thenReturn(mockSqlDate);
        when(mockSqlDate.toLocalDate()).thenReturn(subject.getDateOfCreation());
    }
}