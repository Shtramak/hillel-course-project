package com.courses.tellus.autosalon.dao.jdbc;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Optional;

import com.courses.tellus.autosalon.config.jdbc.ConnectionFactory;
import com.courses.tellus.autosalon.dao.jdbc.AutosalonDao;
import com.courses.tellus.autosalon.model.Autosalon;
import org.h2.tools.RunScript;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AutosalonDaoIntegrationTest {

    private static ConnectionFactory connectionFactory;
    private static AutosalonDao autosalonDao;

    @BeforeAll
    public static void before() throws IOException, SQLException {
        connectionFactory = ConnectionFactory.getInstance();
        autosalonDao = new AutosalonDao(connectionFactory);
    }

    @BeforeEach
    void setUp() throws Exception {
        RunScript.execute(connectionFactory.getConnection(), new FileReader("src/test/resources/test.sql"));
    }

    @Test
    public void testInsertAutosalon() throws SQLException {
        Autosalon autosalon = new Autosalon(1L, "Toyota", "Japan", "444000");
        MatcherAssert.assertThat(autosalonDao.insert(autosalon), CoreMatchers.is(1));
    }

    @Test
    public void testInsertWhenTableInfoSalonNotExistsReturn0() throws Exception {
        Autosalon autosalon = new Autosalon(1L, "Toyota", "Japan", "444000");
        RunScript.execute(connectionFactory.getConnection(), new FileReader("src/test/resources/dropTables.sql"));
        MatcherAssert.assertThat(autosalonDao.insert(autosalon), CoreMatchers.is(0));
    }

    @Test
    public void testGetAutosalonById() throws SQLException {
        Autosalon autosalon = new Autosalon(1L, "Geely", "China", "00000");
        Assertions.assertEquals(Optional.of(autosalon).toString(), autosalonDao.getById(1L).toString());
    }

    @Test
    public void testGetAllAutosalon() throws SQLException {
        Assertions.assertEquals(autosalonDao.getAll().size(), autosalonDao.getAll().size());
    }

    @Test
    public void testGetAllWhenTableInfoSalonNotExistsReturnEmptyList() throws Exception {
        RunScript.execute(connectionFactory.getConnection(), new FileReader("src/test/resources/dropTables.sql"));
        MatcherAssert.assertThat(autosalonDao.getAll(), CoreMatchers.is(Collections.emptyList()));
    }

    @Test
    public void testDeleteAutoSalonId() throws SQLException {
        MatcherAssert.assertThat(autosalonDao.delete(1L), CoreMatchers.is(1));
    }

    @Test
    public void testDeleteWhenTableInfoSalonNotExistsReturn0() throws Exception {
        RunScript.execute(connectionFactory.getConnection(), new FileReader("src/test/resources/dropTables.sql"));
        MatcherAssert.assertThat(autosalonDao.delete(1L), CoreMatchers.is(0));
    }

    @Test
    public void testGetAutoByIdWhenResultfalse() {
        Assertions.assertEquals(Optional.empty(), autosalonDao.getById(18L));
    }

    @Test
    public void testUpdateAutoWhenResultTrue() {
        Autosalon autosalon = new Autosalon(5L, "Toyota", "Japan", "444000");
        MatcherAssert.assertThat(autosalonDao.update(autosalon), CoreMatchers.is(0));
    }
}