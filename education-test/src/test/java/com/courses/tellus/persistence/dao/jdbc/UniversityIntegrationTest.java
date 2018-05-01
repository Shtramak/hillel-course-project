package com.courses.tellus.persistence.dao.jdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileReader;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.entity.model.University;
import com.courses.tellus.config.jdbc.ConnectionFactory;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    @Test
    void testGetAllUniversities() throws Exception {
        List<University> universities = universityDao.getAll();
        assertEquals(1, universities.size());
    }

    @Test
    public void testGetUniversityByIdWhenReturnEntity() throws Exception {
        Optional<University> university = universityDao.getById(this.university.getUniId());
        assertTrue(university.isPresent());
    }

    @Test
    public void testUpdateUniversity() throws Exception {
        University university = new University(1L,"kpi",
                "Universitet","Hz");
        assertEquals(1, universityDao.update(university));
    }

    @Test
    public void testDeleteUniversity() throws Exception {
       assertEquals(1, universityDao.delete(university.getUniId()));
    }

    @Test
    public void testInsertUniversity() throws Exception {
        assertEquals(1,universityDao.insert(university));
    }
}
