package com.courses.tellus.dao.spring.jdbc;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;


import com.courses.tellus.connection.spring.jdbc.JDBCTeamplateConfiguration;
import com.courses.tellus.dao.jdbc.SubjectDao;
import com.courses.tellus.entity.Subject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ContextConfiguration(classes = {JDBCTeamplateConfiguration.class, SubjectDao.class})
@ExtendWith(SpringExtension.class)
class SubjectDaoMockTest {

    @Mock private SubjectDao subjectDao;
    private Subject subject;
    @Mock private JdbcTemplate jdbcTemplate;
    @Mock private RowMapper<Subject> rowMapper;

    @BeforeEach
    void mockInit() throws Exception{
        MockitoAnnotations.initMocks(this);
        subject = new Subject(1L, "Law", "Lesson about orders of Ukraine", true,
                new GregorianCalendar(2000, 5, 12));
    }

    @Test
    void testGetAll() {
        List<Subject> subjectList = new ArrayList<>();
        List<Subject> spyList = spy(subjectList);
        when(subjectDao.getAll()).thenReturn(Optional.of(spyList));
        Assertions.assertNotNull(spyList);
    }

    @Test
    void testGetById() {
        final List<Subject> resultList = new ArrayList<>();
        resultList.add(subject);
        when(jdbcTemplate.query(anyString(), any(RowMapper.class)));
        System.out.println(resultList.size());
        System.out.println(resultList.get(0).toString());
        assertEquals(subject, subjectDao.getById(1L));
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
}