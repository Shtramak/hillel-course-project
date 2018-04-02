package com.courses.tellus.autosalon.dao.jdbc;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.autosalon.config.jdbc.ConnectionFactory;
import com.courses.tellus.autosalon.model.Auto;
import org.h2.tools.RunScript;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AutoDaoIntegrationTest {

    private AutoDao autoDao;
    private static final Auto AUTO = new Auto(6L, "BMW", "X6", 2017, "Germany", new BigDecimal(500000));
    private static final Auto AUTO_WHEN_ONE_FIELD_NULL = new Auto(2L, "Toyota", null, 2015, "Japan", new BigDecimal(20000));

    @BeforeAll
    public static void before() throws Exception {
        RunScript.execute(ConnectionFactory.getInstance().getConnection(), new FileReader("src/test/resources/test.sql"));
    }

    @BeforeEach
    public void reInitAutodao() throws IOException {
        autoDao = new AutoDao(ConnectionFactory.getInstance());
    }

    @Test
    public void testAddAutoWhenResultTrue() {
        MatcherAssert.assertThat(autoDao.insert(AUTO), CoreMatchers.is(1));
    }

    @Test
    public void testAddAutoWhenResultFalse() {
        MatcherAssert.assertThat(autoDao.insert(AUTO_WHEN_ONE_FIELD_NULL), CoreMatchers.is(0));
    }

    @Test
    public void testQueryAutoWhenResultTrue() {
        List<Auto> autoList = autoDao.getAll();
        Assertions.assertTrue(autoList.size() == 5);
    }

    @Test
    public void testUpdateAutoWhenResultTrue() {
        MatcherAssert.assertThat(autoDao.update(AUTO), CoreMatchers.is(1));
    }

    @Test
    public void testUpdateAutoWhenResultFalse() {
        MatcherAssert.assertThat(autoDao.update(AUTO_WHEN_ONE_FIELD_NULL), CoreMatchers.is(0));
    }

    @Test
    public void testRemoveAutoWhenResultTrue() {
        MatcherAssert.assertThat(autoDao.delete(5L), CoreMatchers.is(1));
    }

    @Test
    public void testRemoveAutoWhenResultFalse() {
        Auto newAuto = new Auto();
        newAuto.setId(-1L);
        MatcherAssert.assertThat(autoDao.delete(newAuto.getId()), CoreMatchers.is(0));
    }

    @Test
    public void testGetAutoByIdWhenResultTrue() {
        Assertions.assertEquals(Optional.of(AUTO), autoDao.getById(6l));
    }

    @Test
    public void testGetAutoByIdWhenResultfalse() {
        Assertions.assertEquals(Optional.empty(), autoDao.getById(8l));
    }
}

