package com.courses.tellus.dao;


import com.courses.tellus.dbconnection.ConnectionFactory;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileReader;

public class SubjectDaoTest {

    @BeforeAll
    static void before() throws Exception{
        RunScript.execute(ConnectionFactory.getInstance().getConnection(),
                new FileReader("src/test/resources/db_creation.sql"));
    }
}
