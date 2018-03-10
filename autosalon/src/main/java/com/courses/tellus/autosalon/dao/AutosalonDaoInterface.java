package com.courses.tellus.autosalon.dao;

import java.util.List;

public interface AutosalonDaoInterface<E> {

    /**
     * Method for returning all object from database.
     *
     * @return list with all Object or null if exception
     */
    List<E> getAll();

    /**
     * Method for returning object by unique id from database.
     *
     * @param id unique id for object query
     * @return Object or null (if not exist in database or exception)
     */
    E getById(Long id);

    /**
     * Method for updating object in database.
     *
     * @param entity updating object
     * @return number of affected rows int database
     */
    Integer update(E entity);

    /**
     * Method for deleting object from database.
     *
     * @param id unique id for object query
     * @return number of affected rows int database
     */
    Integer delete(Long id);

    /**
     * Method for creating new object into database.
     *
     * @param entity object for inserting
     * @return number of affected rows int database
     */
    Integer insert(E entity);
}