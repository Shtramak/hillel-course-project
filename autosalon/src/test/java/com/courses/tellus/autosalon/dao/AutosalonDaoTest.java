package com.courses.tellus.autosalon.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
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

    @BeforeAll
    public static void before() throws IOException, SQLException {
        connection = ConnectionFactory.getInstance().getConnection();
        autosalonDao = new AutosalonDao(connection);
        RunScript.execute(connection, new FileReader("src/test/resources/test.sql"));
    }

    @Test
    public void testAddAutosalon() throws SQLException {
        Autosalon autosalon = new Autosalon();
        autosalon.setId(1L);
        autosalon.setName("Toyota");
        autosalon.setAddress("Japan");
        autosalon.setTelophone("444000");
        Assertions.assertEquals(1, autosalonDao.addAutosalon(autosalon));
    }

    @Test
    public void testFindAutosalonById() throws SQLException {
        Autosalon autosalon = new Autosalon(1L, "Geely", "China", "00000");
        Assertions.assertNotEquals(autosalon, autosalonDao.getAutoSalonById(autosalon.getId()));
    }

    @Test
    public void testFindAllAutosalon() throws SQLException {
        Assertions.assertEquals(autosalonDao.findAllAutosalon().size(), autosalonDao.findAllAutosalon().size());
    }

    @Test
    public void testRemoveAutoSalonId() throws SQLException {
        MatcherAssert.assertThat(autosalonDao.removeAutoSalonId(1L), CoreMatchers.is(1));
    }
    @Test
    public void testGetAutoByIdWhenResultfalse() {
        Assertions.assertEquals(null, autosalonDao.getAutoSalonById(18L));
    }
}
