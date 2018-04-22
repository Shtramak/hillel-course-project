package com.courses.tellus.autosalon.dao.hibernate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.courses.tellus.autosalon.dao.AutosalonDaoInterface;
import com.courses.tellus.autosalon.model.Auto;
import org.apache.log4j.Logger;

public class AutoDao implements AutosalonDaoInterface<Auto> {

    private static final Logger LOGGER = Logger.getLogger(AutoDao.class);

    private final transient EntityManager entityManager;

    public AutoDao(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Auto> getAll() {
        try {
            final Query query = entityManager.createQuery("select auto from Auto auto");
            return query.getResultList();
        } catch (IllegalArgumentException e) {
            LOGGER.debug(e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<Auto> getById(final Long idAuto) {
        try {
            final Auto auto = entityManager.find(Auto.class, idAuto);
            return Optional.of(auto);
        } catch (IllegalArgumentException e) {
            LOGGER.debug(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Integer update(final Auto auto) {
        final EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(auto);
            transaction.commit();
            return 1;
        } catch (IllegalArgumentException e) {
            transaction.rollback();
            LOGGER.debug(e.getMessage());
            return -1;
        }
    }

    @Override
    public Integer delete(final Long idAuto) {
        final EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            final Auto auto = entityManager.find(Auto.class, idAuto);
            entityManager.remove(auto);
            transaction.commit();
            return 1;
        } catch (IllegalArgumentException e) {
            transaction.rollback();
            LOGGER.debug(e.getMessage());
            return -1;
        }
    }

    @Override
    public Integer insert(final Auto auto) {
        final EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(auto);
            return 1;
        } catch (IllegalArgumentException e) {
            transaction.rollback();
            LOGGER.debug(e.getMessage());
            return -1;
        }
    }
}
