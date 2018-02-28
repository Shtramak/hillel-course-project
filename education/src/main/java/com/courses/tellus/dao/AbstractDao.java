package com.courses.tellus.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Supplier;

public interface AbstractDao<E, K> extends Supplier<Connection> {
    List<E> getAll();
    E getEntityById(K id);
    void update(E entity);
    void delete(K id);
    void create(E entity);
}

