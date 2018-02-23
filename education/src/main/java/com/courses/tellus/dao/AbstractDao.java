package com.courses.tellus.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Supplier;

public interface AbstractDao<E, K> extends Supplier<Connection> {

    List<E> getAll() throws SQLException;

    E getEntityById(K id) throws SQLException;

    void update(E entity) throws SQLException;

    void delete(K id) throws SQLException;

    void create(E entity) throws SQLException;

}

