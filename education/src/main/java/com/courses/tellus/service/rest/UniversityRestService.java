package com.courses.tellus.service.rest;

import java.util.List;
import java.util.Optional;

public interface UniversityRestService<T, D> {

    /**
     * Method for getting all subjects from database.
     *
     * @return list of subjects or empty list
     */

    List<T> getAll();

    /**
     * Method for getting university from DB by id.
     *
     * @param entityId id of the university to select from DB
     * @return an Optional with a present value if the specified value
     *         is non-null, otherwise an empty Optional
     */

    Optional<T> getById(final Long entityId);

    /**
     * Method for inserting new university into database.
     *
     * @param entity  for inserting
    */
    void insert(final D entity);

    /**
     * Method for deleting university from database by id.
     *
     * @param entityId id of the university to remove from database
     */

    void delete(final Long entityId);

    /**
     * Method for updatingting university from database by id.
     *
     * @param entity to update
     */

    void update(final D entity);
}
