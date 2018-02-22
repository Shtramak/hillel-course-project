package com.courses.tellus.dao;

import java.sql.SQLException;
import java.util.List;

public interface AbstractDao<E, K> {

    List<E> getAll() throws SQLException;

    E getEntityById(K id) throws SQLException;

    void update(E entity) throws SQLException;

    void delete(K id) throws SQLException;

    void create(E entity) throws SQLException;

}

