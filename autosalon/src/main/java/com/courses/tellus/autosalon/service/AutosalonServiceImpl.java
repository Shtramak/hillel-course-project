package com.courses.tellus.autosalon.service;

import java.util.List;

import com.courses.tellus.autosalon.dao.springjdbc.AutosalonDao;
import com.courses.tellus.autosalon.model.Autosalon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutosalonServiceImpl implements AutosalonService {

    private final transient AutosalonDao autosalonDao;

    @Autowired
    public AutosalonServiceImpl(final AutosalonDao autosalonDao) {
        this.autosalonDao = autosalonDao;
    }

    @Override
    public List<Autosalon> getAll() {
        return autosalonDao.getAll();
    }

    @Override
    public Integer insert(final Autosalon autosalon) {
        return autosalonDao.insert(autosalon);
    }
}
