package com.courses.tellus.autosalon.dao.hibernate;

import com.courses.tellus.autosalon.model.Auto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class AutoDaoMockTest {


    @Mock
    EntityManager entityManager;

    @Mock
    EntityManagerFactory factory;


    Auto auto = new Auto(1L, "BMW", "X6", 2017, "Germany", new BigDecimal(50000));

    private AutoDao autoDao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        factory = Persistence.createEntityManagerFactory("autosalon");
        entityManager = factory.createEntityManager();
        autoDao = new AutoDao(entityManager);
    }

    @Test
    public void testGetAll(){
        List<Auto> autoList = new ArrayList<>();
        autoList.add(auto);
        when(entityManager.createQuery(anyString())).thenReturn((Query) autoList);
        assertThat(autoDao.getAll().size(), is(1));
    }

    @Test
    public void testInsert(){
        when(entityManager.merge(anyObject())).thenReturn(1);
        assertThat(autoDao.insert(auto), is(1));

    }
}
