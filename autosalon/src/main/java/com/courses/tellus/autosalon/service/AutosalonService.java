package com.courses.tellus.autosalon.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.autosalon.dao.AutosalonDaoEntyty;
import com.courses.tellus.autosalon.dao.springjdbc.AutosalonDao;
import com.courses.tellus.autosalon.exception.DaoException;
import com.courses.tellus.autosalon.model.Autosalon;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class AutosalonService {

    private static final Logger LOGGER = Logger.getLogger(AutosalonDao.class);

    @Autowired
    private transient AutosalonDaoEntyty salonDaoEntyty;

    /**
     * Insert Autosalon on database.
     *
     *
     * @return salonDaoEntyty.getInsert().
     */

    public Integer getInsert(final Autosalon autosalon) {
        try {
            return salonDaoEntyty.insert(autosalon);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            return -1;
        }
    }

    /**
     * Delete Autosalon byId in database.
     *
     *
     * @return salonDaoEntyty.getDelete().
     */

    public Integer getDelete(final Long num) {
        try {
            return salonDaoEntyty.delete(num);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            return -1;
        }
    }

    /**
     * Update Autosalon in database.
     *
     *
     * @return salonDaoEntyty.getUpdate().
     */

    public Integer getUpdate(final Autosalon autosalon) {
        try {
            return salonDaoEntyty.update(autosalon);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            return -1;
        }
    }

    /**
     * GetAll Autosalon on database.
     *
     *
     * @return salonDaoEntyty.getAll().
     */

    public List<Autosalon> getAll() {
        try {
            return salonDaoEntyty.getAll();
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * Searche Autosalon byId in database.
     *
     *
     * @return salonDaoEntyty.getById().
     */

    public Optional<Autosalon> getById(final Long num) {
        try {
            return salonDaoEntyty.getById(num);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            return Optional.empty();
        }
    }

    /**
     * Find Autosalon By Name in database.
     *
     *
     * @return salonDaoEntyty.findAutosalonByName().
     */

    public Optional<Autosalon> findAutosalonByName(final String name) {
        return salonDaoEntyty.findAutosalonByName(name);
    }
}
