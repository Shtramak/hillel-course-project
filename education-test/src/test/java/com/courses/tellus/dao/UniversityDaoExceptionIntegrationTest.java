package com.courses.tellus.dao;

import com.courses.tellus.dbconnection.ConnectionFactory;
import com.courses.tellus.entity.University;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileReader;


public class UniversityDaoExceptionIntegrationTest {

    private static ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
    private static UniversityDao universityDao;
    private static University university;

    @BeforeAll
    static void init() throws Exception {
        RunScript.execute(ConnectionFactory.getInstance().getConnection(),
                new FileReader("src/test/resources/DropTableUniversities.sql"));
        universityDao = new UniversityDao(connectionFactory);
        university = new University(1L,"KPI",
                "pr.Peremohy","Technical");
    }

    @Test
    public void testGetAllUniversitiesWhenReturnNull() {
        Assertions.assertNull(universityDao.getAll());
    }

    @Test
    public void testGetUniversityByIdWhenReturnNull() {
        Assertions.assertNull(universityDao.getById(0L));
    }

    @Test
    public void testUpdateUniversitySqlException() {
        Assertions.assertEquals(0, universityDao.update(university));
    }

    @Test
    public void testDeleteUniversitySqlException() {
        Assertions.assertEquals(0, universityDao.delete(university.getUniId()));
    }

    @Test
    public void testInsertUniversitySqlException() {
        Assertions.assertEquals(0, universityDao.insert(university));
    }
}
