package com.courses.tellus.autosalon.dao.hibernate;

import com.courses.tellus.autosalon.exception.DaoException;
import com.courses.tellus.autosalon.model.Auto;
import com.courses.tellus.autosalon.model.Autosalon;
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
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class AutosalonDaoMockTest {
    @Mock
    EntityManager entityManager;
    @Mock
    EntityTransaction transaction;
    @Mock
    Query query;
    @Mock
    Autosalon autosalon;

    private AutosalonDao autosalonDao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        autosalonDao = new AutosalonDao(entityManager);
    }

    @Test
    public void testGetAllWhenResultTrue() throws DaoException {
        List<Autosalon> autosalonList = new ArrayList<>();
        autosalonList.add(autosalon);
        when(entityManager.createQuery(anyString())).thenReturn(query);
        when(query.getResultList()).thenReturn(autosalonList);
        assertThat(autosalonDao.getAll().size(), is(1));
    }

    @Test
    public void testGetAllWhenResultFalse() throws DaoException {
        List<Auto> autoList = new ArrayList<>();
        when(entityManager.createQuery(anyString())).thenThrow(IllegalArgumentException.class);
        when(query.getResultList()).thenReturn(autoList);
        assertThat(autosalonDao.getAll(), is(Collections.emptyList()));
    }

    @Test
    public void testGetByIdWhenResultTrue() throws DaoException {
        when(entityManager.find(any(), anyString())).thenReturn(autosalon);
        assertThat(autosalonDao.getById(1L), is(Optional.of(autosalon)));
    }

    @Test
    public void testGetByIdWhenResultFalse() throws DaoException {
        when(entityManager.find(any(), anyString())).thenThrow(IllegalArgumentException.class);
        assertThat(autosalonDao.getById(1L), is(Optional.empty()));
    }

    @Test
    public void testInsertWhenResultTrue() {
        when(entityManager.getTransaction()).thenReturn(transaction);
        autosalonDao.insert(autosalon);
        verify(entityManager, times(1)).persist(autosalon);
    }

    @Test
    public void testInsertWhenResultFalse() {
        when(entityManager.getTransaction()).thenReturn(transaction);
        doThrow(IllegalArgumentException.class).when(entityManager).persist(anyObject());
        assertThat(autosalonDao.insert(autosalon), is(-1));
        verify(transaction).rollback();
    }

    @Test
    public void testUpdateWhenResultTrue() {
        when(entityManager.getTransaction()).thenReturn(transaction);
        autosalonDao.update(autosalon);
        verify(entityManager, times(1)).merge(autosalon);
    }

    @Test
    public void testUpdateWhenResultFalse() {
        when(entityManager.getTransaction()).thenReturn(transaction);
        doThrow(IllegalArgumentException.class).when(entityManager).merge(anyObject());
        assertThat(autosalonDao.update(autosalon), is(-1));
        verify(transaction).rollback();
    }

    @Test void testDeleteWhenResultTrue(){
        when(entityManager.getTransaction()).thenReturn(transaction);
        when(entityManager.find(any(), anyString())).thenReturn(autosalon);
        autosalonDao.delete(1L);
        verify(entityManager, times(1)).remove(autosalon);
    }

    @Test void testDeleteWhenResultFalse(){
        when(entityManager.getTransaction()).thenReturn(transaction);
        doThrow(IllegalArgumentException.class).when(entityManager).remove(anyLong());
        assertThat(autosalonDao.delete(1L), is(-1));
        verify(transaction).rollback();
    }
}
