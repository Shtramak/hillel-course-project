package com.courses.tellus.repository.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;

import java.util.ArrayList;
import java.util.List;

import com.courses.tellus.model.Subject;
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

class SubjectRepoMockTest {

    @Mock private EntityManager entityManager;
    @Mock private EntityTransaction transaction;
    @Mock private Subject subject;
    @Mock private Query query;

    private SubjectRepository repository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        repository = new SubjectRepository(entityManager);
    }

    @Test
    void getAllTestAndReturnList() {
        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject());
        given(entityManager.createQuery(anyString())).willReturn(query);
        given(query.getResultList()).willReturn(subjects);
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void getAllTestAndThrowException() {
        given(entityManager.createQuery(anyString())).willThrow(IllegalArgumentException.class);
        assertEquals(0, repository.findAll().size());
    }

    @Test
    void getByIdTestAndReturnEntity() {
        given(entityManager.find(any(), anyLong())).willReturn(subject);
        assertTrue(repository.findById(1L).isPresent());
    }

    @Test
    void getByIdTestAndThrowException() {
        given(entityManager.find(any(), anyLong())).willThrow(IllegalArgumentException.class);
        assertFalse(repository.findById(1L).isPresent());
    }

    @Test
    void mergeTestAndReturnSuccessfulValue() {
        given(entityManager.getTransaction()).willReturn(transaction);
        assertEquals(1, repository.merge(subject));
        verify(entityManager, atLeastOnce()).merge(subject);
    }

    @Test
    void mergeTestAndThrowException() {
        given(entityManager.getTransaction()).willReturn(transaction);
        given(entityManager.merge(subject)).willThrow(TransactionRequiredException.class);
        assertEquals(0, repository.merge(subject));
        verify(transaction, atLeastOnce()).rollback();
    }

    @Test
    void persistTestAndReturnSuccessfulValue() {
        given(entityManager.getTransaction()).willReturn(transaction);
        assertEquals(1, repository.persist(subject));
        verify(entityManager, atLeastOnce()).persist(subject);
    }

    @Test
    void persistTestAndThrowException() {
        given(entityManager.getTransaction()).willReturn(transaction);
        willThrow(TransactionRequiredException.class).given(entityManager).persist(subject);
        assertEquals(0, repository.persist(subject));
        verify(transaction, atLeastOnce()).rollback();
    }

    @Test
    void removeTestAndReturnSuccessfulValue() {
        given(entityManager.getTransaction()).willReturn(transaction);
        given(entityManager.find(any(), anyLong())).willReturn(subject);
        assertEquals(1, repository.remove(1L));
        verify(entityManager, atLeastOnce()).remove(subject);
    }

    @Test
    void removeTestAndThrowException() {
        given(entityManager.getTransaction()).willReturn(transaction);
        given(entityManager.find(any(), anyLong())).willThrow(TransactionRequiredException.class);
        willThrow(TransactionRequiredException.class).given(entityManager).remove(subject);
        assertEquals(0, repository.remove(1L));
        verify(transaction, atLeastOnce()).rollback();
    }
}
