package com.courses.tellus.autosalon.config.springjdbc;

import java.util.List;
import java.util.Optional;

import com.courses.tellus.autosalon.exception.DaoException;
import com.courses.tellus.autosalon.model.Autosalon;
import com.courses.tellus.autosalon.service.AutosalonService;
import org.springframework.beans.factory.annotation.Autowired;

public class AutosalonConfig {

    @Autowired
    private transient AutosalonService autosalonService;

    /**
     * Insert Autosalon on database.
     *
     *
     * @return autosalonService.getInsert().
     */

    public Integer getInsert() throws DaoException {
       return autosalonService.getInsert(new Autosalon("Audi", "Germany", "443809"));
    }

    /**
     * GetAll Autosalon on database.
     *
     *
     * @return autosalonService.getAll().
     */

    public List<Autosalon> getAll() throws DaoException {
        return autosalonService.getAll();
    }

    /**
     * Searche Autosalon byId in database.
     *
     *
     * @return autosalonService.getById().
     */

    public Optional<Autosalon> getById() throws DaoException {
        return autosalonService.getById(1L);
    }

    /**
     * Delete Autosalon byId in database.
     *
     *
     * @return autosalonService.getDelete().
     */

    public Integer getDelete() throws DaoException {
        return autosalonService.getDelete(1L);
    }

    /**
     * Update Autosalon in database.
     *
     *
     * @return autosalonService.getUpdate().
     */

    public Integer getUpdate() throws DaoException {
        return autosalonService.getUpdate(new Autosalon("Audi", "Germany", "443809"));
    }

    /**
     * Find Autosalon By Name in database.
     *
     *
     * @return autosalonService.findAutosalonByLastName().
     */

    public List<Autosalon> findAutosalonByLastName() {
        return autosalonService.findAutosalonByLastName("Geely");
    }
}
