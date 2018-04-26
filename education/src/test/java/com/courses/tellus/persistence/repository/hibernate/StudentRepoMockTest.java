package com.courses.tellus.persistence.repository.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import java.util.ArrayList;
import java.util.List;

import com.courses.tellus.entity.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

class StudentRepoMockTest {

    @Mock private EntityManager entityManager;
    @Mock private EntityTransaction transaction;
    @Mock private Student student;
    @Mock private Query query;

    private StudentRepository repository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        repository = new StudentRepository(entityManager);
    }

    @Test
    void getAllTestAndReturnList() {
        List<Student> students = new ArrayList<>();
        students.add(new Student());
        given(entityManager.createQuery(anyString())).willReturn(query);
        given(query.getResultList()).willReturn(students);
        assertEquals(1, repository.getAll().size());
    }

    @Test
    void getAllTestAndThrowException() {
        given(entityManager.createQuery(anyString())).willThrow(IllegalArgumentException.class);
        assertEquals(0, repository.getAll().size());
    }

    @Test
    void getByIdTestAndReturnEntity() {
        given(entityManager.find(any(), anyLong())).willReturn(student);
        assertTrue(repository.getById(1L).isPresent());
    }

    @Test
    void getByIdTestAndThrowException() {
        given(entityManager.find(any(), anyLong())).willThrow(IllegalArgumentException.class);
        assertFalse(repository.getById(1L).isPresent());
    }

    @Test
    void mergeTestAndReturnSuccessfulValue() {
        given(entityManager.getTransaction()).willReturn(transaction);
        assertEquals(1, repository.update(student));
        verify(entityManager, atLeastOnce()).merge(student);
    }

    @Test
    void mergeTestAndThrowException() {
        given(entityManager.getTransaction()).willReturn(transaction);
        given(entityManager.merge(student)).willThrow(TransactionRequiredException.class);
        assertEquals(0, repository.update(student));
        verify(transaction, atLeastOnce()).rollback();
    }

    @Test
    void persistTestAndReturnSuccessfulValue() {
        given(entityManager.getTransaction()).willReturn(transaction);
        assertEquals(1, repository.insert(student));
        verify(entityManager, atLeastOnce()).persist(student);
    }

    @Test
    void persistTestAndThrowException() {
        given(entityManager.getTransaction()).willReturn(transaction);
        willThrow(TransactionRequiredException.class).given(entityManager).persist(student);
        assertEquals(0, repository.insert(student));
        verify(transaction, atLeastOnce()).rollback();
    }

    @Test
    void removeTestAndReturnSuccessfulValue() {
        given(entityManager.getTransaction()).willReturn(transaction);
        given(entityManager.find(any(), anyLong())).willReturn(student);
        assertEquals(1, repository.delete(1L));
        verify(entityManager, atLeastOnce()).remove(student);
    }

    @Test
    void removeTestAndThrowException() {
        given(entityManager.getTransaction()).willReturn(transaction);
        given(entityManager.find(any(), anyLong())).willThrow(TransactionRequiredException.class);
        willThrow(TransactionRequiredException.class).given(entityManager).remove(student);
        assertEquals(0, repository.delete(1L));
        verify(transaction, atLeastOnce()).rollback();
    }
}
