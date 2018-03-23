package com.courses.tellus.autosalon.service;

import java.util.List;
import java.util.Optional;

import com.courses.tellus.autosalon.dao.AutosalonDaoEntyty;
import com.courses.tellus.autosalon.exception.DaoException;
import com.courses.tellus.autosalon.model.Autosalon;
import org.springframework.beans.factory.annotation.Autowired;

public class AutosalonService {

    @Autowired
    private transient AutosalonDaoEntyty salonDaoEntyty;

    /**
     * Insert Autosalon on database.
     *
     *
     * @return salonDaoEntyty.getInsert().
     */

    public Integer getInsert(final Autosalon autosalon) throws DaoException {
       return salonDaoEntyty.insert(autosalon);
    }

    /**
     * Delete Autosalon byId in database.
     *
     *
     * @return salonDaoEntyty.getDelete().
     */

    public Integer getDelete(final Long num) throws DaoException {
        return salonDaoEntyty.delete(num);
    }

    /**
     * Update Autosalon in database.
     *
     *
     * @return salonDaoEntyty.getUpdate().
     */

    public Integer getUpdate(final Autosalon autosalon) throws DaoException {
        return salonDaoEntyty.update(autosalon);
    }

    /**
     * GetAll Autosalon on database.
     *
     *
     * @return salonDaoEntyty.getAll().
     */

    public List<Autosalon> getAll() throws DaoException {
       return salonDaoEntyty.getAll();
    }

    /**
     * Searche Autosalon byId in database.
     *
     *
     * @return salonDaoEntyty.getById().
     */

    public Optional<Autosalon> getById(final Long num) throws DaoException {
        return salonDaoEntyty.getById(num);
    }

    /**
     * Find Autosalon By Name in database.
     *
     *
     * @return salonDaoEntyty.findAutosalonByLastName().
     */

    public List<Autosalon> findAutosalonByLastName(final String firstName) {
        return null;
    }
}
