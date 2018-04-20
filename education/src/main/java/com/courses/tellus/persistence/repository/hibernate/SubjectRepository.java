package com.courses.tellus.persistence.repository.hibernate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TransactionRequiredException;

import com.courses.tellus.entity.model.Subject;
import com.courses.tellus.persistence.BasicDao;
import org.apache.log4j.Logger;

public class SubjectRepository implements BasicDao<Subject> {

    private static final Logger LOGGER = Logger.getLogger(SubjectRepository.class);
    private final transient EntityManager entityManager;

    public SubjectRepository(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Subject> getAll() {
        try {
            return entityManager.createQuery("select subject from Subject subject").getResultList();
        } catch (IllegalArgumentException exception) {
            LOGGER.debug(exception.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<Subject> getById(final Long subjectId) {
        try {
            return Optional.of(entityManager.find(Subject.class, subjectId));
        } catch (IllegalArgumentException exception) {
            LOGGER.debug(exception.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public int insert(final Subject subject) {
        final EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(subject);
            transaction.commit();
            return 1;
        } catch (IllegalArgumentException | TransactionRequiredException exception) {
            transaction.rollback();
            LOGGER.debug(exception.getMessage());
            return 0;
        }
    }

    @Override
    public int update(final Subject subject) {
        final EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(subject);
            transaction.commit();
            return 1;
        } catch (IllegalArgumentException | TransactionRequiredException except) {
            transaction.rollback();
            LOGGER.debug(except.getCause(), except);
            return 0;
        }
    }

    @Override
    public int delete(final Long subjectId) {
        final EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(entityManager.find(Subject.class, subjectId));
            transaction.commit();
            return 1;
        } catch (IllegalArgumentException | TransactionRequiredException exception) {
            transaction.rollback();
            LOGGER.debug(exception.getMessage());
            return 0;
        }
    }
}
