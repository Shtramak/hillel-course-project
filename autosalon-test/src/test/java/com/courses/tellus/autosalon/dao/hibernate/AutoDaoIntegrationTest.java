package com.courses.tellus.autosalon.dao.hibernate;

import com.courses.tellus.autosalon.config.hibernate.EntityFactory;
import com.courses.tellus.autosalon.model.Auto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AutoDaoIntegrationTest {

    private AutoDao autoDao;
    private Auto auto = new Auto(null, "BMW", "X7", 2012, "Germany", new BigDecimal(200000));
    private final Auto AUTO_IN_DATABASE = new Auto(5L, "BMW", "X7", 2012, "Germany", new BigDecimal(200000));

    private EntityManager entityManager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        entityManager = EntityFactory.getFactory().createEntityManager();
        autoDao = new AutoDao(entityManager);
    }

    @Test
    public void testGetAllWhenResultTrue(){
        assertThat(autoDao.getAll().size(), is(5));
    }

    @Test
    public void testGetAllWhenResultFalse(){
        assertNotEquals(1, autoDao.getAll().size());
    }

    @Test
    public void testInsertWhenResultTrue(){
        assertThat(autoDao.insert(auto), is(1));
    }

    @Test
    public void testInsertWhenResultFalse(){
        assertThat(autoDao.insert(null), is(-1));
    }

    @Test
    public void testUpdateWhenResultTrue(){
        AUTO_IN_DATABASE.setBrand("Toyota");
        AUTO_IN_DATABASE.setModel("Camry");
        assertThat(autoDao.update(AUTO_IN_DATABASE), is(1));
    }

    @Test
    public void testUpdateWhenResultFalse(){
        assertThat(autoDao.update(null), is(-1));
    }

    @Test
    public void testDeleteWhenResultTrue(){
        assertThat(autoDao.delete(1L), is(1));
    }

    @Test
    public void testDeleteWhenResultFalse(){
        assertThat(autoDao.delete(8L), is(-1));
    }

    @Test
    public void testGetByIdWhenresultTrue(){
        assertTrue(autoDao.getById(2L).isPresent());
    }

    @Test
    public void testGetByIdWhenresultFalse(){
        assertThat(autoDao.getById(null), is(Optional.empty()));
    }

}
