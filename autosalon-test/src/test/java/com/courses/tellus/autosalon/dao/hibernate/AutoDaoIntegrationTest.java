package com.courses.tellus.autosalon.dao.hibernate;

import com.courses.tellus.autosalon.config.hibernate.EntityFactory;
import com.courses.tellus.autosalon.model.Auto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AutoDaoIntegrationTest {

    private AutoDao autoDao;
    private final Auto AUTO = new Auto(5L, "BMW", "X7", 2012, "Germany", new BigDecimal(200000));

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
    public void testDeleteWhenresultTrue(){
        assertThat(autoDao.delete(1L), is(1));
    }

    @Test
    public void testInsert(){
        assertThat(autoDao.update(AUTO), is(1));
    }

}
