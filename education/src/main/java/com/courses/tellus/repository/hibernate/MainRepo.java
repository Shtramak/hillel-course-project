package com.courses.tellus.repository.hibernate;

import java.util.List;
import java.util.Optional;

public interface MainRepo<E> {

    /**
     * Method for getting all objects from database.
     *
     * @return list with all Object or empty list
     */
    List<E> findAll();

    /**
     * Method for returning object in option wrapper by unique id from database.
     *
     * @param entityId unique id for object query
     * @return Object in option or Option.empty() (if not exist in database or exception)
     */
    Optional<E> findById(Long entityId);

    /**
     * Method for updating object in database.
     *
     * @param entity updating object
     * @return number of successful operations
     */
    int merge(E entity);

    /**
     * Method for deleting object from database.
     *
     * @param entityId unique id for object query
     * @return number of successful operations
     */
    int remove(Long entityId);

    /**
     * Method for creating new object into database.
     *
     * @param entity object for inserting
     * @return number of successful operations
     */
    int persist(E entity);
}
