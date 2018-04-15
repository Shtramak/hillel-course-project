package com.courses.tellus.autosalon.service;

import com.courses.tellus.autosalon.model.Autosalon;

import java.util.List;
import java.util.Optional;

public interface AutosalonService {
    /**
     *Get all autosalon from database.
     * @return list autosalon.
     */
    List<Autosalon> getAll();

    /**
     * Add autosalon to database.
     * @param autosalon autosalon.
     * @return number of affected rows in database.
     */
    Integer insert(final Autosalon autosalon);

    /**
     * Update autosalon in database.
     * @param autosalon autosalon.
     * @return number of affected rows in database.
     */
    Integer update(final Autosalon autosalon);

    /**
     * Delete autosalon from database.
     * @param idAutosalon id autosalon.
     * @return number of affected rows in database.
     */
    Integer delete(final Long idAutosalon);

    /**
     * Get auto by id.
     * @param idAutosalon id auto.
     * @return auto.
     */
    Optional<Autosalon> getAutosalonById(final Long idAutosalon);
}
