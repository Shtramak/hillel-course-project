package com.courses.tellus.autosalon.service.springrest;

import java.util.List;
import java.util.Optional;

import com.courses.tellus.autosalon.model.Autosalon;
import com.courses.tellus.autosalon.model.dto.AutosalonDto;

public interface AutosalonServiceRest {
    /**
     *Get all autosalon from database.
     * @return list autosalon.
     */
    List<Autosalon> getAll();

    /**
     * Add autosalon to database.
     * @param autosalonDto autosalon.
     * @return number of affected rows in database.
     */
    Autosalon insert(final AutosalonDto autosalonDto);

    /**
     * Update autosalon in database.
     * @param autosalonDto autosalon.
     * @return number of affected rows in database.
     */
    Autosalon update(final AutosalonDto autosalonDto);

    /**
     * Delete autosalon from database.
     * @param idAutosalon id autosalon.
     */
    void delete(final Long idAutosalon);

    /**
     * Get autosalon by id.
     * @param idAutosalon id autosalon.
     * @return autosalon.
     */
    Optional<Autosalon> getAutosalonById(final Long idAutosalon);
}
