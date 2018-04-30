package com.courses.tellus.service.rest;

import java.util.List;
import java.util.Optional;

public interface RestService<T, D> {

    List<T> getAll();

    Optional<T> getEntityById(final Long entityId);

    boolean insert(final D entity);

    boolean delete(final Long entityId);

    boolean update(final D entity);
}
