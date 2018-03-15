package com.courses.tellus.dao.spring.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import com.courses.tellus.connection.spring.jdbc.JDBCTeamplateConfiguration;
import com.courses.tellus.entity.University;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ContextConfiguration(classes = {JDBCTeamplateConfiguration.class, UniversityDao.class})
@ExtendWith(SpringExtension.class)
class UniversityDaoMockTest {

    @Mock
    private UniversityDao universityDao;
    private University university;


    @BeforeEach
    void mockInit() throws Exception{
        MockitoAnnotations.initMocks(this);
        university = new University(1L, "KPI", "pr.Peremohy",
                "Technical");
    }

    @Test
    void testGetAll() {
        List<University> universities = new ArrayList<>();
        List<University> spyList = spy(universities);
        when(universityDao.getAll()).thenReturn(Optional.of(spyList));
        assertNotNull(spyList);
    }

    @Test
    void testGetById() {
        when(universityDao.getById(1L)).thenReturn(Optional.of(university));
        assertEquals(Optional.of(university), universityDao.getById(1L));
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
}