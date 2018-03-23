package com.courses.tellus.autosalon.dao;

import java.util.List;

import com.courses.tellus.autosalon.model.Autosalon;

public interface AutosalonDaoEntyty extends AutosalonDaoInterface<Autosalon> {

    /**
     * Find Autosalon By Name in database.
     *
     *
     * @return autosalonService.findAutosalonByLastName().
     */

    List<Autosalon> findAutosalonByLastName(String firstName);
}
