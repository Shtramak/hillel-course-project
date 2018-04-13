package com.courses.tellus.dao;

import java.util.List;
import java.util.Optional;

public interface BasicDao<E> {

    /**
     * Method for returning all object from database.
     *
     * @return list with all Object or empty list if exception
     */
    List<E> getAll();

    /**
     * Method for returning object in option wrapper by unique id from database.
     *
     * @param entityId unique id for object query
     * @return Object in option or Option.empty() (if not exist in database or exception)
     */
    Optional<E> getById(Long entityId);

    /**
     * Method for updating object in database.
     *
     * @param entity updating object
     * @return number of successful operations
     */
    int update(E entity);

    /**
     * Method for deleting object from database.
     *
     * @param entityId unique id for object query
     * @return number of successful operations
     */
    int delete(Long entityId);

    /**
     * Method for creating new object into database.
     *
     * @param entity object for inserting
     * @return number of successful operations
     */
    int insert(E entity);
}