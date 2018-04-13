package com.courses.tellus.autosalon.service;

import java.util.List;
import java.util.Optional;

import com.courses.tellus.autosalon.dao.springjdbc.AutoDao;
import com.courses.tellus.autosalon.model.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutoServiceImpl implements AutoService {

    private final transient AutoDao autoDao;

    @Autowired
    public AutoServiceImpl(final AutoDao autoDao) {
        this.autoDao = autoDao;
    }

    @Override
    public List<Auto> getAll() {
        return autoDao.getAll();
    }

    @Override
    public Integer insert(final Auto auto) {
        return autoDao.insert(auto);
    }

    @Override
    public Integer update(final Auto auto) {
        return autoDao.update(auto);
    }

    @Override
    public Integer delete(final Long idAuto) {
        return autoDao.delete(idAuto);
    }

    @Override
    public Optional<Auto> getAutoById(final Long idAuto) {
        return autoDao.getById(idAuto);
    }
}
