package com.courses.tellus.dao;

import com.courses.tellus.dbconnection.ConnectionFactory;
import com.courses.tellus.entity.University;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.*;

import java.io.FileReader;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;


public class UniversityIntegrationTest {

    private UniversityDao universityDao;
    private University university;

    @BeforeAll
    static void before() throws Exception {
        RunScript.execute(ConnectionFactory.getInstance().getConnection(),
                new FileReader("src/test/resources/univer_test_table.sql"));
    }

    @BeforeEach
    void beforeEach() {
        universityDao = new UniversityDao(ConnectionFactory.getInstance());
        university = new University(1L,"KPI","pr.Peremohy","Technical");
        universityDao.insert(university);
    }
    @Test
    public void testGetAllUniversities() {
        List<University> universities = universityDao.getAll();
        Assertions.assertTrue(!(universities.size() == 0));
    }

    @Test
    public void testGetUniversityByIdWhenReturnEntity() {
        University university = universityDao.getById(universityDao.getAll().get(0).getUniId());
        assertNotNull(university);
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
