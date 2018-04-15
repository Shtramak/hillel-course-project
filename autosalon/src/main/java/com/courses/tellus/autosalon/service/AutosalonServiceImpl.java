package com.courses.tellus.autosalon.service;

import com.courses.tellus.autosalon.dao.springjdbc.AutosalonDao;
import com.courses.tellus.autosalon.model.Autosalon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutosalonServiceImpl implements AutosalonService {

    private final transient AutosalonDao autosalonDao;

    @Autowired
    public AutosalonServiceImpl(final AutosalonDao autosalonDao) {
        this.autosalonDao = autosalonDao;
    }

    @Override
    public List<Autosalon> getAll() {
        return null;
    }

    @Override
    public Integer insert(Autosalon autosalon) {
        return null;
    }
}
