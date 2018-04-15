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

}
