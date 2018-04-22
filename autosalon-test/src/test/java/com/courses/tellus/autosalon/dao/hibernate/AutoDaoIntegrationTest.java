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

public class AutoDaoIntegrationTest {

    private AutoDao autoDao;
    private final Auto NEW_AUTO = new Auto(null, "Toyota", "Camry", 2017, "Japan", new BigDecimal(25000));;
    private final Auto AUTO_IS_IN_DATABASE = new Auto(1L, "Toyota", "Camry", 2017, "Japan", new BigDecimal(25000));;
    private EntityManager entityManager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        entityManager = EntityFactory.getFactory().createEntityManager();
        autoDao = new AutoDao(entityManager);

    }

    @Test
    public void testInsertWhenResultTrue() {
        assertThat(autoDao.insert(NEW_AUTO), is(1));
    }

    @Test
    public void testInsertWhenResultFalse() {
        assertThat(autoDao.insert(AUTO_IS_IN_DATABASE), is(-1));
    }

    @Test
    public void testUpdateWhenResultTrue() {
        assertThat(autoDao.update(AUTO_IS_IN_DATABASE), is(1));
    }

    @Test
    public void testUpdateWhenResultFalse() {
        assertThat(autoDao.update(null), is(-1));
    }

    @Test
    public void testGetByIdWhenResultFalse(){
        assertThat(autoDao.getById(1L), is(Optional.empty()));
    }

    @Test
    public void testDeleteWhenResultFalse(){
        assertThat(autoDao.delete(2L), is(-1));
    }
}
