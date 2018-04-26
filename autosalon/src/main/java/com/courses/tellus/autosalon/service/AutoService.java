package com.courses.tellus.autosalon.service;

import java.util.List;
import java.util.Optional;

import com.courses.tellus.autosalon.model.Auto;

public interface AutoService {

    /**
     *Get all auto from database.
     * @return list auto.
     */
    List<Auto> getAll();

    /**
     * Add auto to database.
     * @param auto auto.
     * @return number of affected rows in database.
     */
    Integer insert(final Auto auto);

    /**
     * Update auto in database.
     * @param auto auto.
     * @return number of affected rows in database.
     */
    Integer update(final Auto auto);

    /**
     * Delete auto from database.
     * @param idAuto id auto.
     * @return number of affected rows in database.
     */
    Integer delete(final Long idAuto);

    /**
     * Get auto by id.
     * @param idAuto id auto.
     * @return auto.
     */
    Optional<Auto> getAutoById(final Long idAuto);
}
