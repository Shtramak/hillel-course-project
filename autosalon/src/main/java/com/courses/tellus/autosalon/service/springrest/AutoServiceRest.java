package com.courses.tellus.autosalon.service.springrest;

import java.util.Optional;

import com.courses.tellus.autosalon.model.Auto;
import com.courses.tellus.autosalon.model.dto.AutoDto;

public interface AutoServiceRest {

    /**
     *Get all auto from database.
     * @return list auto.
     */
    Iterable<Auto> getAll();

    /**
     * Add auto to database.
     * @param autoDto auto.
     * @return number of affected rows in database.
     */
    Auto insert(final AutoDto autoDto);

    /**
     * Update auto in database.
     * @param autoDto auto.
     * @return number of affected rows in database.
     */
    Auto update(final AutoDto autoDto);

    /**
     * Delete auto from database.
     * @param idAuto id auto.
     */
    void delete(final Long idAuto);

    /**
     * Get auto by id.
     * @param idAuto id auto.
     * @return auto.
     */
    Optional<Auto> getAutoById(final Long idAuto);
}
