package com.courses.tellus.autosalon.dao;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import com.courses.tellus.autosalon.config.ConnectionFactory;
import com.courses.tellus.autosalon.model.Auto;
import org.h2.tools.RunScript;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AutoDaoIntegrationTest {

    private AutoDao autoDao;

    public AutoDaoIntegrationTest() throws IOException {
        this.autoDao = new AutoDao(ConnectionFactory.getInstance());
    }

    @BeforeAll
    public static void before() throws IOException, SQLException {
        RunScript.execute(ConnectionFactory.getInstance().getConnection(), new FileReader("src/test/resources/test.sql"));
    }

    @Test
    public void testAddAutoWhenResultTrue() {
        Auto auto = new Auto();
        auto.setBrand("BMW");
        auto.setModel("X5");
        auto.setManufactYear(2016);
        auto.setProducerCountry("Germany");
        auto.setPrice(new BigDecimal(500000));

        MatcherAssert.assertThat(autoDao.addAuto(auto), CoreMatchers.is(1));
    }

    @Test
    public void testAddAutoWhenResultFalse() {
        Auto auto = new Auto();
        auto.setBrand("BMW");
        auto.setModel(null);
        auto.setManufactYear(2016);
        auto.setProducerCountry("Germany");
        auto.setPrice(new BigDecimal(500000));

        MatcherAssert.assertThat(autoDao.addAuto(auto), CoreMatchers.is(0));
    }

    @Test
    public void testQueryAutoWhenResultTrue() {
        List<Auto> autoList = autoDao.queryAuto();
        Assertions.assertTrue(autoList.size() == 5);
    }

    @Test
    public void testUpdateAutoWhenResultTrue() {
        Auto newAuto = new Auto();
        newAuto.setId(2L);
        newAuto.setBrand("Toyota");
        newAuto.setModel("Camry");
        newAuto.setManufactYear(2015);
        newAuto.setProducerCountry("Japan");
        newAuto.setPrice(new BigDecimal(200000));

        MatcherAssert.assertThat(autoDao.updateAuto(newAuto), CoreMatchers.is(1));
    }

    @Test
    public void testUpdateAutoWhenResultFalse() {
        Auto newAuto = new Auto();
        newAuto.setId(2L);
        newAuto.setBrand("Toyota");
        newAuto.setModel(null);
        newAuto.setManufactYear(2015);
        newAuto.setProducerCountry("Japan");
        newAuto.setPrice(new BigDecimal(200000));

        MatcherAssert.assertThat(autoDao.updateAuto(newAuto), CoreMatchers.is(0));
    }

    @Test
    public void testRemoveAutoWhenResultTrue() {
        MatcherAssert.assertThat(autoDao.removeAutoById(5L), CoreMatchers.is(1));
    }

    @Test
    public void testRemoveAutoWhenResultFalse() {
        Auto newAuto = new Auto();
        newAuto.setId(-1L);
        MatcherAssert.assertThat(autoDao.removeAutoById(newAuto.getId()), CoreMatchers.is(0));
    }

    @Test
    public void testGetAutoById() {
        Auto newAuto = new Auto();
        newAuto.setId(2L);
        newAuto.setBrand("BMW");
        newAuto.setModel("X3");
        newAuto.setManufactYear(2013);
        newAuto.setProducerCountry("Germany");
        newAuto.setPrice(new BigDecimal(200000));

        Assertions.assertEquals(newAuto, autoDao.getAutoById(2l));
    }

    @Test
    public void testGetAutoByIdWhenResultfalse() {
        Assertions.assertEquals(null, autoDao.getAutoById(8l));
    }
}
