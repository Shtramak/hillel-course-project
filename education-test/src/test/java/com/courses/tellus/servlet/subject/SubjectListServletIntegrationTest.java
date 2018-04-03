package com.courses.tellus.servlet.subject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.courses.tellus.connection.jdbc.ConnectionFactory;
import com.courses.tellus.dao.jdbc.SubjectDao;
import com.courses.tellus.servlet.JettyServerRunner;
import org.eclipse.jetty.http.HttpStatus;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubjectListServletIntegrationTest {

    private static JettyServerRunner jettyServer = new JettyServerRunner();

    @BeforeAll
    static void init() throws Exception {
        RunScript.execute(ConnectionFactory.getInstance().getConnection(),
                new FileReader("src/test/resources/initial/h2/table/jdbc/subject_test_inject.sql"));
        System.out.println(new SubjectDao(ConnectionFactory.getInstance()).getAll());
        jettyServer.setUp();
    }

    @BeforeEach
    void startServer() throws Exception {
        jettyServer.startServer();
    }

    @AfterEach
    void stopServer() throws Exception {
        jettyServer.stopJetty();
    }

    @Test
    void testGet() throws Exception {
        HttpURLConnection connection;

        URL url = new URL("http://localhost:8080/subjectList");
        connection = (HttpURLConnection) url.openConnection();

        System.out.println(connection.getResponseMessage());
        System.out.println(new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine());
        assertEquals(HttpStatus.OK_200, connection.getResponseCode(), "Response code");
    }
}
