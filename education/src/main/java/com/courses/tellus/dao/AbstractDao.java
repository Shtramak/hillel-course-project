package com.courses.tellus.dao;

import java.util.List;

public interface AbstractDao<E, K> {

    List<E> getAll();

    E getEntityById(K id);

    void update(E entity);

    void delete(K id);

    void create(E entity);

}

