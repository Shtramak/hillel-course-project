package com.courses.tellus.autosalon.dao;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.autosalon.config.ConnectionFactory;
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
    private Auto auto;
    private Auto autoWhenOneFieldNull;

    @BeforeAll
    public static void before() throws IOException, SQLException {
        RunScript.execute(ConnectionFactory.getInstance().getConnection(), new FileReader("src/test/resources/test.sql"));
    }

    @BeforeEach
    public void reInitAutodao() throws IOException {
        autoDao = new AutoDao(ConnectionFactory.getInstance());

        auto = new Auto();
        auto.setId(6L);
        auto.setBrand("BMW");
        auto.setModel("X5");
        auto.setManufactYear(1027);
        auto.setProducerCountry("Germany");
        auto.setPrice(new BigDecimal(500000));

        autoWhenOneFieldNull = new Auto();
        autoWhenOneFieldNull.setId(2L);
        autoWhenOneFieldNull.setBrand("Toyota");
        autoWhenOneFieldNull.setModel(null);
        autoWhenOneFieldNull.setManufactYear(2015);
        autoWhenOneFieldNull.setProducerCountry("Japan");
        autoWhenOneFieldNull.setPrice(new BigDecimal(200000));
    }

    @Test
    public void testAddAutoWhenResultTrue() {
        MatcherAssert.assertThat(autoDao.insert(auto), CoreMatchers.is(1));
    }

    @Test
    public void testAddAutoWhenResultFalse() {
        MatcherAssert.assertThat(autoDao.insert(autoWhenOneFieldNull), CoreMatchers.is(0));
    }

    @Test
    public void testQueryAutoWhenResultTrue() {
        List<Auto> autoList = autoDao.getAll();
        Assertions.assertTrue(autoList.size() == 5);
    }

    @Test
    public void testUpdateAutoWhenResultTrue() {
        MatcherAssert.assertThat(autoDao.update(auto), CoreMatchers.is(1));
    }

    @Test
    public void testUpdateAutoWhenResultFalse() {
        MatcherAssert.assertThat(autoDao.update(autoWhenOneFieldNull), CoreMatchers.is(0));
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
        Assertions.assertEquals(Optional.of(auto), autoDao.getById(6l));
    }

    @Test
    public void testGetAutoByIdWhenResultfalse() {
        Assertions.assertEquals(Optional.empty(), autoDao.getById(8l));
    }
}