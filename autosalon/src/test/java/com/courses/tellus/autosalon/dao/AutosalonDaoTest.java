package com.courses.tellus.autosalon.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.courses.tellus.autosalon.config.ConnectionFactory;
import com.courses.tellus.autosalon.model.Autosalon;
import org.h2.tools.RunScript;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AutosalonDaoTest {

    private static Connection connection;
    private static AutosalonDao autosalonDao;
    private static Autosalon autosalon;

    @BeforeAll
    public static void before() throws IOException, SQLException {
        connection = ConnectionFactory.getInstance().getConnection();
        autosalonDao = new AutosalonDao(connection);
        autosalon = new Autosalon(1L, "Geely", "China", "00000");
        RunScript.execute(connection, new FileReader("src/test/resources/test.sql"));
    }

    @Test
    public void testAddAutosalon() throws SQLException {
        Assertions.assertEquals(1, autosalonDao.addAutosalon(autosalon));
    }

    @Test
    public void testFindAotusalonById() throws SQLException {
        Assertions.assertNotEquals(autosalon, autosalonDao.getAutoSalonById(1));
    }

    @Test
    public void testFindAllAutosalon() throws SQLException {
        Assertions.assertEquals(autosalonDao.findAllAutosalon().size(), autosalonDao.findAllAutosalon().size());
    }

    @Test
    public void testRemoveAutoSalonId() throws SQLException {
        MatcherAssert.assertThat(autosalonDao.removeAutoSalonId(1), CoreMatchers.is(1));
    }
}
