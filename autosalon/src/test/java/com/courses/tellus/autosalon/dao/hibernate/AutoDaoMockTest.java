package com.courses.tellus.autosalon.dao.hibernate;

import com.courses.tellus.autosalon.model.Auto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

public class AutoDaoMockTest {


    @Mock
    EntityManager entityManager;
    @Mock
    EntityTransaction transaction;
    @Mock
    Query query;
    @Mock
    Auto auto;

    private AutoDao autoDao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        autoDao = new AutoDao(entityManager);
    }

    @Test
    public void testGetAllWhenResultTrue() {
        List<Auto> autoList = new ArrayList<>();
        autoList.add(auto);
        when(entityManager.createQuery(anyString())).thenReturn(query);
        when(query.getResultList()).thenReturn(autoList);
        assertThat(autoDao.getAll().size(), is(1));
    }

    @Test
    public void testGetAllWhenResultFalse() {
        List<Auto> autoList = new ArrayList<>();
        when(entityManager.createQuery(anyString())).thenThrow(IllegalArgumentException.class);
        when(query.getResultList()).thenReturn(autoList);
        assertThat(autoDao.getAll(), is(Collections.emptyList()));
    }

    @Test
    public void testGetByIdWhenResultTrue(){
        when(entityManager.find(any(), anyString())).thenReturn(auto);
        assertThat(autoDao.getById(1L), is(Optional.of(auto)));
    }

    @Test
    public void testGetByIdWhenResultFalse(){
        when(entityManager.find(any(), anyString())).thenThrow(IllegalArgumentException.class);
        assertThat(autoDao.getById(1L), is(Optional.empty()));
    }

    @Test
    public void testInsertWhenResultTrue(){
        when(entityManager.getTransaction()).thenReturn(transaction);
        autoDao.insert(auto);
        verify(entityManager, times(1)).persist(auto);
    }

    @Test
    public void testInsertWhenResultFalse(){
        when(entityManager.getTransaction()).thenReturn(transaction);
        doThrow(IllegalArgumentException.class).when(entityManager).persist(anyObject());
        assertThat(autoDao.insert(auto), is(-1));
        verify(transaction).rollback();
    }

    @Test
    public void testUpdateWhenResultTrue(){
        when(entityManager.getTransaction()).thenReturn(transaction);
        autoDao.update(auto);
        verify(entityManager, times(1)).merge(auto);
    }

    @Test
    public void testUpdateWhenResultFalse(){
        when(entityManager.getTransaction()).thenReturn(transaction);
        doThrow(IllegalArgumentException.class).when(entityManager).merge(anyObject());
        assertThat(autoDao.update(auto), is(-1));
        verify(transaction).rollback();
    }

    @Test void testDeleteWhenResultTrue(){
        when(entityManager.getTransaction()).thenReturn(transaction);
        when(entityManager.find(any(), anyString())).thenReturn(auto);
        autoDao.delete(1L);
        verify(entityManager, times(1)).remove(auto);
    }

    @Test void testDeleteWhenResultFalse(){
        when(entityManager.getTransaction()).thenReturn(transaction);
        doThrow(IllegalArgumentException.class).when(entityManager).remove(anyLong());
        assertThat(autoDao.delete(1L), is(-1));
        verify(transaction).rollback();
    }
}