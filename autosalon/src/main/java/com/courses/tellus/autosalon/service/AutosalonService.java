package com.courses.tellus.autosalon.service;

import com.courses.tellus.autosalon.dao.AutosalonDaoEntyty;
import com.courses.tellus.autosalon.exception.DaoException;
import com.courses.tellus.autosalon.model.Autosalon;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


public class AutosalonService {

    @Autowired
    private AutosalonDaoEntyty autosalonDaoEntyty;

    public Integer getInsert(final Autosalon autosalon) throws DaoException {
       return autosalonDaoEntyty.insert(autosalon);
    }

    public Integer getDelete(final Long num) throws DaoException {
        return autosalonDaoEntyty.delete(num);
    }

    public Integer getUpdate(final Autosalon autosalon) throws DaoException {
        return autosalonDaoEntyty.update(autosalon);
    }

    public List<Autosalon> getAll() throws DaoException {
       return autosalonDaoEntyty.getAll();
    }

    public Optional<Autosalon> getById(final Long num) throws DaoException {
        return autosalonDaoEntyty.getById(num);
    }

    public List<Autosalon> findPersonsByLastName(String firstName) {
        return null;
    }
}
