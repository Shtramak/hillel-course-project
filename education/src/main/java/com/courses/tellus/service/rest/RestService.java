package com.courses.tellus.service.rest;

import java.util.List;
import java.util.Optional;

public interface RestService<T, D> {

    /**
     * Method for getting all subjects from database.
     *
     * @return list of subjects or empty list
     */
    List<T> getAll();

    /**
     * Method for getting subject from DB by id.
     *
     * @param entityId id of the subject to select from DB
     * @return an Optional with a present value if the specified value
     *         is non-null, otherwise an empty Optional
     */
    Optional<T> getEntityById(final Long entityId);

    /**
     * Method for inserting new subject into database.
     *
     * @param entity for inserting
     * @return statement of transaction
     */
    boolean insert(final D entity);

    /**
     * Method for deleting subject from database by id.
     *
     * @param entityId id of the subject to remove from database
     * @return statement of transaction
     */
    boolean delete(final Long entityId);

    /**
     * Method for updating new subject into database.
     *
     * @param entityId for check on existing
     * @param entity for updating
     * @return statement of transaction
     */
    boolean update(final Long entityId, final D entity);
}
