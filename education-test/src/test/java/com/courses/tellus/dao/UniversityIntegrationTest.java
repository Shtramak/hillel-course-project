package com.courses.tellus.dao;

import com.courses.tellus.dbconnection.ConnectionFactory;
import com.courses.tellus.entity.University;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.*;

import java.io.FileReader;
import java.util.List;

public class UniversityIntegrationTest {
    private UniversityDao universityDao;
    private University university;

    @BeforeAll
    static void before() throws Exception {
        RunScript.execute(ConnectionFactory.getInstance().getConnection(),
                new FileReader("src/test/resources/testTable.sql"));
    }

    @BeforeEach
    void beforeEach() {
        universityDao = new UniversityDao(ConnectionFactory.getInstance());
        university = new University(1L,"KPI","pr.Peremohy","Technical");
        universityDao.insert(university);
    }


    @Test
    public void testGetAllObjects() {
        List<University> universities = universityDao.getAllEntity();
        Assertions.assertTrue(!(universities.size() == 0));
    }

    @Test
    public void testGetEntityByIdWhenReturnEntity() {
        University university = universityDao.getEntityById(1L);
        Assertions.assertTrue(university != null);
    }


    @Test
    public void testUpdateUniversity() {
        University university1 = new University(1L,"kpi",
                "Universitet","Hz");
        Assertions.assertTrue(universityDao.update(university1));
    }

    @Test
    public void testDeleteUniversity() {
        boolean result = universityDao.delete(1L);
        Assertions.assertTrue(result);
    }

    @Test
    public void testCreateUniversity() {
        boolean result = universityDao.insert(university);
        Assertions.assertTrue(result);
    }
}
