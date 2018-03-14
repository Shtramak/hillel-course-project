package com.courses.tellus.autosalon.dao.springjdbc;

import com.courses.tellus.autosalon.dao.AutosalonDaoInterface;
import com.courses.tellus.autosalon.exception.DaoException;
import com.courses.tellus.autosalon.model.Auto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AutoDaoImpl implements AutosalonDaoInterface<Auto>{

    @Override
    public List<Auto> getAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<Auto> getById(Long entityId) throws DaoException {
        return null;
    }

    @Override
    public Integer update(Auto entity) throws DaoException {
        return null;
    }

    @Override
    public Integer delete(Long entityId) throws DaoException {
        return null;
    }

    @Override
    public Integer insert(Auto entity) throws DaoException {
        return null;
    }
}
