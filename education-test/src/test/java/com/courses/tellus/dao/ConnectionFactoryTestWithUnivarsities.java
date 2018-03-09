package com.courses.tellus.dao;

import com.courses.tellus.dao.UniversityDao;
import com.courses.tellus.dbconnection.ConnectionFactory;
import com.courses.tellus.entity.University;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileReader;

public class ConnectionFactoryTestWithUnivarsities {

    private static ConnectionFactory connectionFactory;
    private static UniversityDao universityDao;
    private University university;

    @BeforeAll
    static void init() throws Exception {
        RunScript.execute(ConnectionFactory.getInstance().getConnection(),
                new FileReader("src/test/resources/testTable.sql"));
        universityDao = new UniversityDao(ConnectionFactory.getInstance());
        connectionFactory = ConnectionFactory.getInstance();
        universityDao = new UniversityDao(connectionFactory);
    }
    @BeforeEach
    void initUniversity() throws Exception {
        university = new University(1L, "KPI", "pr.Peremohy", "Technical");
    }
    @Test
    void tesDataBaseConnection() {
        Assertions.assertTrue( universityDao.insert(university));
    }
}
