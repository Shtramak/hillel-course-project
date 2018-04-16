package com.courses.tellus.autosalon.dao.hibernate;

import com.courses.tellus.autosalon.dao.AutosalonDaoInterface;
import com.courses.tellus.autosalon.model.Auto;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AutoDao implements AutosalonDaoInterface<Auto> {

    private static final Logger LOGGER = Logger.getLogger(AutoDao.class);

    private EntityManager entityManager;

    public AutoDao() {
    }

    public AutoDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Auto> getAll() {
        try {
            Query query = entityManager.createQuery("select auto from Auto auto");
            return query.getResultList();
        } catch (DataAccessException e) {
            LOGGER.debug(e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<Auto> getById(Long idAuto) {
        try {
            Auto auto = entityManager.find(Auto.class, idAuto);
            return Optional.of(auto);
        } catch (DataAccessException e) {
            LOGGER.debug(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Integer update(Auto auto) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(auto);
            transaction.commit();
            return 1;
        } catch (RuntimeException e){
            transaction.rollback();
            LOGGER.debug(e.getMessage());
            return -1;
        }
    }

    @Override
    public Integer delete(Long idAuto) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Auto auto = entityManager.find(Auto.class, idAuto);
            entityManager.remove(auto);
            transaction.commit();
            return 1;
        } catch (RuntimeException e){
            transaction.rollback();
            LOGGER.debug(e.getMessage());
            return -1;
        }
    }

    @Override
    public Integer insert(Auto auto) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(auto);
            transaction.commit();
            return 1;
        } catch (RuntimeException e){
            transaction.rollback();
            LOGGER.debug(e.getMessage());
            return -1;
        }
    }
}
