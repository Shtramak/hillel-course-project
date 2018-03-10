package com.courses.tellus.dbconnection;


import java.io.FileReader;
import java.util.GregorianCalendar;

import com.courses.tellus.dao.SubjectDao;
import com.courses.tellus.entity.Subject;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.*;

/**
 * Created for checking by checkstyle
 */
class ConnectionFactoryCoverageTest {

    private static SubjectDao subjectDao;

    @BeforeAll
    static void init() throws Exception {
        RunScript.execute(ConnectionFactory.getInstance().getConnection(),
                new FileReader("src/test/resources/subject_db_table.sql"));
        subjectDao = new SubjectDao(ConnectionFactory.getInstance());
    }

    @Test
    void tesDataBaseConnectionSuccess() {
        Subject subject = new Subject("Biology", "Lessons about building of humans", true,
                new GregorianCalendar(1996,5,12));
        Assertions.assertEquals(1, subjectDao.insert(subject));
    }
}
