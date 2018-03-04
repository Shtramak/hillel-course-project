package com.courses.tellus.autosalon.dao;

        import com.courses.tellus.autosalon.config.ConnectionFactory;
        import com.courses.tellus.autosalon.model.Auto;
        import org.h2.tools.RunScript;
        import org.hamcrest.CoreMatchers;
        import org.hamcrest.MatcherAssert;
        import org.junit.Assert;
        import org.junit.jupiter.api.Assertions;
        import org.junit.jupiter.api.BeforeAll;
        import org.junit.jupiter.api.Test;

        import java.io.FileReader;
        import java.io.IOException;
        import java.math.BigDecimal;
        import java.sql.SQLException;
        import java.util.List;

public class AutoDaoTest {

    private AutoDao autoDao ;

    public AutoDaoTest() throws IOException {
        this.autoDao = new AutoDao(ConnectionFactory.getInstance());
    }

    @BeforeAll
    public static void before() throws IOException, SQLException {
        RunScript.execute(ConnectionFactory.getInstance().getConnection(), new FileReader("src/test/resources/test.sql"));
    }

    /**
     * Testing method addAuto when result true.
     *
     */
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

    /**
     * Testing method addAuto when result false.
     *
     */
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

    /**
     * Testing method query avto when result true.
     *
     * @throws SQLException exception.
     */
    @Test
    public void testQueryAutoWhenResultTrue(){
        List<Auto> autoList = autoDao.queryAuto();
        Assertions.assertTrue(autoList.size() == 5);
    }

    /**
     * Testing method updateAuto when result true.
     *
     */
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

    /**
     * Testing method updateAuto when result false.
     *
     */
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


    /**
     * Testing method remove auto from Database by id when result true
     *
     */
    @Test
    public void testRemoveAutoWhenResultTrue() {
        MatcherAssert.assertThat(autoDao.removeAutoById(5L), CoreMatchers.is(1));
    }

    /**
     * Testing method remove auto from Database by id when result false
     *
     */
    @Test
    public void testRemoveAutoWhenResultFalse() {
        MatcherAssert.assertThat(autoDao.removeAutoById(-9L), CoreMatchers.is(0));
    }

    /**
     * Testing method get auto by id from DataBase
     *
     * @throws SQLException
     */
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

    /**
     * Testing method get auto by id from DataBase when result false
     *
     */
    @Test
    public void testGetAutoByIdWhenResultfalse() {
        Assertions.assertEquals(null, autoDao.getAutoById(8l));
    }
}

