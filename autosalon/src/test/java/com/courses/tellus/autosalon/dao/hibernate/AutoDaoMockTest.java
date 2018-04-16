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
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

public class AutoDaoMockTest {


    @Mock
    EntityManager entityManager;

    @Mock
    EntityManagerFactory factory;

    @Mock
    Query query;


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
    public void testGetAll() {
        List<Auto> autoList = new ArrayList<>();
        autoDao.insert(auto);
        when(query.getResultList()).thenReturn(autoList);
        assertThat(autoDao.getAll().size(), is(1));
    }

    @Test
    public void testGetById(){
        autoDao.insert(auto);
        when(entityManager.find(Auto.class, anyLong())).thenReturn(auto);
        assertThat(autoDao.getById(1L), is(1));
    }
}