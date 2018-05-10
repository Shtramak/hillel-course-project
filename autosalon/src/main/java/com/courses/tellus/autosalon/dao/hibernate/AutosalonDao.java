package com.courses.tellus.autosalon.dao.hibernate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.courses.tellus.autosalon.dao.AutosalonDaoInterface;
import com.courses.tellus.autosalon.model.Autosalon;
import org.apache.log4j.Logger;

public class AutosalonDao implements AutosalonDaoInterface<Autosalon> {
    private static final Logger LOGGER = Logger.getLogger(AutosalonDao.class);

    private final transient EntityManager entityManager;

    public AutosalonDao(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Autosalon> getAll() {

        try {
            final Query query = entityManager.createQuery("select auto from Autosalon auto");
            return query.getResultList();
        } catch (IllegalArgumentException e) {
            LOGGER.debug(e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<Autosalon> getById(final Long entityId) {
        try {
            final Autosalon autosalon = entityManager.find(Autosalon.class, entityId);
            return Optional.of(autosalon);
        } catch (IllegalArgumentException e) {
            LOGGER.debug(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Integer update(final Autosalon autosalon) {
        final EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(autosalon);
            transaction.commit();
            return 1;
        } catch (IllegalArgumentException e) {
            transaction.rollback();
            LOGGER.debug(e.getMessage());
            return -1;
        }
    }

    @Override
    public Integer delete(final Long entityId) {
        final EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            final Autosalon autosalon = entityManager.find(Autosalon.class, entityId);
            entityManager.remove(autosalon);
            transaction.commit();
            return 1;
        } catch (IllegalArgumentException e) {
            transaction.rollback();
            LOGGER.debug(e.getMessage());
            return -1;
        }
    }

    @Override
    public Integer insert(final Autosalon autosalon) {
        final EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(autosalon);
            entityManager.getTransaction().commit();
            return 1;
        } catch (IllegalArgumentException e) {
            transaction.rollback();
            LOGGER.debug(e.getMessage());
            return -1;
        }
    }
}
