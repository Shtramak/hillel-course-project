package com.courses.tellus.autosalon.dao.springjdbc;

import com.courses.tellus.autosalon.dao.config.JdbcTemplatesConfigTest;
import com.courses.tellus.autosalon.model.Auto;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;
import static java.util.Optional.of;

@ContextConfiguration(classes = {JdbcTemplatesConfigTest.class, AutoDao.class})
@ExtendWith(SpringExtension.class)
public class AutoDaoImplIntegrationTest {

    @Autowired
    private AutoDao autoDao;

    private static final Auto NEW_AUTO = new Auto(6L, "BMW", "X6", 2017, "Germany", new BigDecimal(500000));
    private static final Auto AUTO_IS_IN_DATABASE = new Auto(2L, "BMW", "X3", 2013, "Germany", new BigDecimal(200000));

    @Test
    public void testGetAllWhenResultTrue() {
        List<Auto> autoList = autoDao.getAll();
        assertThat(autoList.size(), is(5));
    }

    @Test
    public void testGetAllWhenResultFalse(){
        List<Auto> autoList = autoDao.getAll();
        autoList.add(NEW_AUTO);
        assertNotEquals(5, autoList.size());
    }

    @Test
    public void testGetByIdWhenResultTrue(){
        assertTrue(of(AUTO_IS_IN_DATABASE).equals(autoDao.getById(2L)));
    }

    @Test
    public void testGetByIdWhenResultFalse(){
        assertFalse(of(NEW_AUTO).equals(autoDao.getById(2L)));
    }

    @Test
    public void testInsertWhenResultTrue(){
        assertThat(autoDao.insert(NEW_AUTO), is(1));
    }

    @Test
    public void testInsertWhenResultFalse(){
        assertFalse(autoDao.insert(NEW_AUTO).equals(0));
    }

    @Test
    public void testUpdateAutoWhenResultTrue() {
        assertThat(autoDao.update(AUTO_IS_IN_DATABASE), is(1));
    }

    @Test
    public void testUpdateAutoWhenResultFalse() {

        assertFalse(autoDao.update(NEW_AUTO).equals(0));
    }

    @Test
    public void testDeleteAutoWhenResultTrue(){
        assertThat(autoDao.delete(2L), is(1));
    }

    @Test
    public void testDeleteAutoWhenResultFalse(){
        assertThat(autoDao.delete(8L), is(0));
    }


}
