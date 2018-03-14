package com.courses.tellus.autosalon.dao.springjdbc;

import java.util.List;
import java.util.Optional;

import com.courses.tellus.autosalon.dao.AutosalonDaoInterface;
import com.courses.tellus.autosalon.exception.DaoException;
import org.springframework.stereotype.Component;

@Component
public class CustomerDao implements AutosalonDaoInterface {
    @Override
    public List getAll() throws DaoException {
        return null;
    }

    @Override
    public Optional getById(final Long entityId) throws DaoException {
        return null;
    }

    @Override
    public Integer update(final Object entity) throws DaoException {
        return null;
    }

    @Override
    public Integer delete(final Long entityId) throws DaoException {
        return null;
    }

    @Override
    public Integer insert(final Object entity) throws DaoException {
        return null;
    }
}