package com.courses.tellus.persistence.repository.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;

import java.util.ArrayList;
import java.util.List;

import com.courses.tellus.entity.model.University;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

class UniversityRepositoryMockTest {

    @Mock private EntityManager entityManager;
    @Mock private EntityTransaction transaction;
    @Mock private University university;
    @Mock private Query query;

    private UniversityRepository repository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        repository = new UniversityRepository(entityManager);
    }

    @Test
    void getAllTestAndReturnList() {
        List<University> universities = new ArrayList<>();
        universities.add(new University());
        given(entityManager.createQuery(anyString())).willReturn(query);
        given(query.getResultList()).willReturn(universities);
        assertEquals(1, repository.getAll().size());
    }

    @Test
    void getAllTestAndThrowException() {
        given(entityManager.createQuery(anyString())).willThrow(IllegalArgumentException.class);
        assertEquals(0, repository.getAll().size());
    }

    @Test
    void getByIdTestAndReturnEntity() {
        given(entityManager.find(any(), anyLong())).willReturn(university);
        assertTrue(repository.getById(1L).isPresent());
    }

    @Test
    void getByIdTestAndThrowException() {
        given(entityManager.find(any(), anyLong())).willThrow(IllegalArgumentException.class);
        assertFalse(repository.getById(1L).isPresent());
    }

    @Test
    void updateTestAndReturnSuccessfulValue() {
        given(entityManager.getTransaction()).willReturn(transaction);
        assertEquals(1, repository.update(university));
        verify(entityManager, atLeastOnce()).merge(university);
    }

    @Test
    void updateTestAndThrowException() {
        given(entityManager.getTransaction()).willReturn(transaction);
        given(entityManager.merge(university)).willThrow(TransactionRequiredException.class);
        assertEquals(0, repository.update(university));
        verify(transaction, atLeastOnce()).rollback();
    }

    @Test
    void insertTestAndReturnSuccessfulValue() {
        given(entityManager.getTransaction()).willReturn(transaction);
        assertEquals(1, repository.insert(university));
        verify(entityManager, atLeastOnce()).persist(university);
    }

    @Test
    void insertTestAndThrowException() {
        given(entityManager.getTransaction()).willReturn(transaction);
        willThrow(TransactionRequiredException.class).given(entityManager).persist(university);
        assertEquals(0, repository.insert(university));
        verify(transaction, atLeastOnce()).rollback();
    }

    @Test
    void deleteTestAndReturnSuccessfulValue() {
        given(entityManager.getTransaction()).willReturn(transaction);
        given(entityManager.find(any(), anyLong())).willReturn(university);
        assertEquals(1, repository.delete(1L));
        verify(entityManager, atLeastOnce()).remove(university);
    }

    @Test
    void deleteTestAndThrowException() {
        given(entityManager.getTransaction()).willReturn(transaction);
        given(entityManager.find(any(), anyLong())).willThrow(TransactionRequiredException.class);
        willThrow(TransactionRequiredException.class).given(entityManager).remove(university);
        assertEquals(0, repository.delete(1L));
        verify(transaction, atLeastOnce()).rollback();
    }
}
