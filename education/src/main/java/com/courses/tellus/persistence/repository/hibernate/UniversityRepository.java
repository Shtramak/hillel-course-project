package com.courses.tellus.persistence.repository.hibernate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TransactionRequiredException;

import com.courses.tellus.entity.model.University;
import com.courses.tellus.persistence.BasicDao;
import org.apache.log4j.Logger;

public class UniversityRepository implements BasicDao<University> {

    private static final Logger LOGGER = Logger.getLogger(UniversityRepository.class);
    private final transient EntityManager entityManager;

    public UniversityRepository(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<University> getAll() {
        try {
            return entityManager.createQuery("SELECT university FROM Universities university").getResultList();
        } catch (IllegalArgumentException exception) {
            LOGGER.debug(exception.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<University> getById(final Long uniId) {
        try {
            return Optional.of(entityManager.find(University.class, uniId));
        } catch (IllegalArgumentException exception) {
            LOGGER.debug(exception.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public int insert(final University university) {
        final EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(university);
            transaction.commit();
            return 1;
        } catch (IllegalArgumentException | TransactionRequiredException exception) {
            transaction.rollback();
            LOGGER.debug(exception.getMessage());
            return 0;
        }
    }

    @Override
    public int update(final University university) {
        final EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(university);
            transaction.commit();
            return 1;
        } catch (IllegalArgumentException | TransactionRequiredException except) {
            transaction.rollback();
            LOGGER.debug(except.getCause(), except);
            return 0;
        }
    }

    @Override
    public int delete(final Long uniId) {
        final EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(entityManager.find(University.class, uniId));
            transaction.commit();
            return 1;
        } catch (IllegalArgumentException | TransactionRequiredException exception) {
            transaction.rollback();
            LOGGER.debug(exception.getMessage());
            return 0;
        }
    }
}
