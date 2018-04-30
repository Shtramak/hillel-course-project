package com.courses.tellus.persistence.repository.hibernate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TransactionRequiredException;

import com.courses.tellus.entity.model.Student;
import com.courses.tellus.persistence.BasicDao;
import org.apache.log4j.Logger;

public class StudentRepository implements BasicDao<Student> {

    private static final Logger LOGGER = Logger.getLogger(StudentRepository.class);
    private final transient EntityManager entityManager;

    public StudentRepository(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Student> getAll() {
        try {
            return entityManager.createQuery("select student from Student student").getResultList();
        } catch (IllegalArgumentException exception) {
            LOGGER.debug(exception.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<Student> getById(final Long studentId) {
        try {
            return Optional.of(entityManager.find(Student.class, studentId));
        } catch (IllegalArgumentException exception) {
            LOGGER.debug(exception.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public int insert(final Student student) {
        final EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(student);
            transaction.commit();
            return 1;
        } catch (IllegalArgumentException | TransactionRequiredException exception) {
            transaction.rollback();
            LOGGER.debug(exception.getMessage());
            return 0;
        }
    }

    @Override
    public int update(final Student student) {
        final EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(student);
            transaction.commit();
            return 1;
        } catch (IllegalArgumentException | TransactionRequiredException except) {
            transaction.rollback();
            LOGGER.debug(except.getCause(), except);
            return 0;
        }
    }

    @Override
    public int delete(final Long studentId) {
        final EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(entityManager.find(Student.class, studentId));
            transaction.commit();
            return 1;
        } catch (IllegalArgumentException | TransactionRequiredException exception) {
            transaction.rollback();
            LOGGER.debug(exception.getMessage());
            return 0;
        }
    }
}
