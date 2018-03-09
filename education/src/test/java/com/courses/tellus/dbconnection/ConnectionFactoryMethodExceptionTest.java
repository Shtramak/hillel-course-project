package com.courses.tellus.dbconnection;


import java.io.FileReader;

import com.courses.tellus.dao.SubjectDao;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.*;

class ConnectionFactoryMethodExceptionTest {

    //private static ConnectionFactory mockConnectionFactory;
    private static ConnectionFactory connectionFactory;
    private static SubjectDao subjectDao;

    @BeforeAll
    static void init() throws Exception {
        RunScript.execute(ConnectionFactory.getInstance().getConnection(),
                new FileReader("src/test/resources/subject_db_table.sql"));
        subjectDao = new SubjectDao(ConnectionFactory.getInstance());
        connectionFactory = ConnectionFactory.getInstance();
        subjectDao = new SubjectDao(connectionFactory);
    }

    @Test
    void tesDataBaseConnectionSuccess() {
        boolean check = subjectDao.delete(1L);
        Assertions.assertTrue(check);
    }
}
