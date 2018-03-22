package com.courses.tellus.dao.jdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileReader;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.dao.jdbc.UniversityDao;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.courses.tellus.connection.jdbc.ConnectionFactory;
import com.courses.tellus.entity.University;


public class UniversityIntegrationTest {

    private UniversityDao universityDao;
    private University university;

    @BeforeEach
    void beforeEach() throws Exception {
        RunScript.execute(ConnectionFactory.getInstance().getConnection(),
                new FileReader("src/test/resources/initial/h2/table/jdbc/univer_test_table.sql"));
        universityDao = new UniversityDao(ConnectionFactory.getInstance());
        university = new University(1L,"KPI","pr.Peremohy","Technical");
        universityDao.insert(university);
    }

    @AfterEach
    void clearTable() throws Exception {
        RunScript.execute(ConnectionFactory.getInstance().getConnection(),
                new FileReader("src/test/resources/initial/h2/util/trunc.sql"));
    }

    @Test
    public void testGetAllUniversities() {
        Optional<List<University>> universities = universityDao.getAll();
        assertTrue(universities.isPresent());
    }

    @Test
    public void testGetUniversityByIdWhenReturnEntity() {
        Optional<University> university = universityDao.getById(this.university.getUniId());
        assertTrue(university.isPresent());
    }

    @Test
    public void testUpdateUniversity() {
        University university1 = new University(1L,"kpi",
                "Universitet","Hz");
        assertEquals(1, universityDao.update(university1));
    }

    @Test
    public void testDeleteUniversity() {
       assertEquals(1, universityDao.delete(university.getUniId()));
    }

    @Test
    public void testInsertUniversity() {
        assertEquals(1,universityDao.insert(university));
    }
}