package com.courses.tellus.autosalon.config.springjdbc;

import com.courses.tellus.autosalon.exception.DaoException;
import com.courses.tellus.autosalon.model.Autosalon;
import com.courses.tellus.autosalon.service.AutosalonService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class AutosalonConfig {

    @Autowired
    public AutosalonService autosalonService;

    public Integer getInsert() throws DaoException {
       return autosalonService.getInsert(new Autosalon("Audi", "Germany", "443809"));
    }

    public List<Autosalon> getAll() throws DaoException {
        return autosalonService.getAll();
    }

    public Optional<Autosalon> getById() throws DaoException {
        return autosalonService.getById(1L);
    }

    public Integer getDelete() throws DaoException {
        return autosalonService.getDelete(1L);
    }

    public Integer getUpdate() throws DaoException {
        return autosalonService.getUpdate(new Autosalon("Audi", "Germany", "443809"));
    }

    public List<Autosalon> findPersonsByLastName() {
        return autosalonService.findPersonsByLastName("Geely");
    }
}
