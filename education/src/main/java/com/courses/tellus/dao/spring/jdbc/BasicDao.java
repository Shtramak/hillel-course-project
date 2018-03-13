package com.courses.tellus.dao.spring.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BasicDao<E> {

    /**
     * Method for returning all object from database.
     *
     * @return list with all Object or false if exception
     */
    Optional<List<E>> getAll();

    /**
     * Method for returning object by unique id from database.
     *
     * @param entityId unique id for object query
     * @return Object or false (if not exist in database or exception)
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

    /**
     * Method for creating new own class Object.
     *
     * @param resultSet returned values from the database
     * @return new Object
     */
    E getNewObjectFromResultSet(ResultSet resultSet) throws SQLException;
}