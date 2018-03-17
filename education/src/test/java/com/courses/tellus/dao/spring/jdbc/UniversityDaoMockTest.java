package com.courses.tellus.dao.spring.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.courses.tellus.entity.University;
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

class UniversityDaoMockTest {

    @Mock private JdbcTemplate jdbcTemplate;
    @InjectMocks private UniversityDao universityDao;
    private University university;
    @Mock private ResultSet mockResultSet;

    @BeforeEach
    void mockInit() throws Exception {
        MockitoAnnotations.initMocks(this);
        university = new University(1L, "KPI", "pr.Peremohy",
                "Technical");
    }

    @Test
    void testGetAllWhenReturnEntities() throws Exception {
        List<University> univerList = new ArrayList<>();
        List<University> spy = spy(univerList);
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            RowMapper<University> rm = (RowMapper<University>) args[1];
            toEntity();
            University actual = rm.mapRow(mockResultSet, 0);
            assertEquals(university, actual);
            return spy;
        });
        when(spy.size()).thenReturn(1);
       assertEquals(spy.size(), universityDao.getAll().size());
    }

    @Test
    void testGetAllWhenReturnFalse() throws Exception {
        List<University> univerList = new ArrayList<>();
        List<University> spy = spy(univerList);
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(spy);
        when(spy.size()).thenReturn(0);
        assertTrue(universityDao.getAll().size() == 0);
    }

    @Test
    void testGetByIdWhenReturnEntity() {
        final List<University> univerList = new ArrayList<>();
        univerList.add(university);
        List<University> spy = spy(univerList);
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            RowMapper<University> rm = (RowMapper<University>) args[2];
            toEntity();
            University actual = rm.mapRow(mockResultSet, 0);
            assertEquals(university, actual);
            return spy;
        });
        when(spy.size()).thenReturn(1);
        when(spy.get(anyInt())).thenReturn(university);
        assertEquals(university, universityDao.getById(1L).get());
    }

    @Test
    void testGetByIdWhenReturnFalse() {
        final List<University> univerList = new ArrayList<>();
        List<University> spy = spy(univerList);
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(spy);
        when(spy.size()).thenReturn(0);
        assertFalse(universityDao.getById(1L).isPresent());
    }

    @Test
    void testInsert() {
        when(universityDao.insert(university)).thenReturn(1);
        assertEquals(1, universityDao.insert(university));
    }

    @Test
    void testDelete() {
        when(universityDao.delete(1L)).thenReturn(1);
        assertEquals(1, universityDao.delete(1L));
    }

    @Test
    void testUpdate() {
        when(universityDao.update(university)).thenReturn(1);
        assertEquals(1, universityDao.update(university));
    }

    private void toEntity() throws SQLException {
        when(mockResultSet.getLong("univer_id")).thenReturn(university.getUniId());
        when(mockResultSet.getString("name_of_university")).thenReturn(university.getNameOfUniversity());
        when(mockResultSet.getString("address")).thenReturn(university.getAddress());
        when(mockResultSet.getString("specialization")).thenReturn(university.getSpecialization());
    }
}
