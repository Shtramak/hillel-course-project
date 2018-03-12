package com.courses.airport.dao;

import com.courses.airport.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BasicDao<E> {
    /**
     * Method for returning object by unique id from database.
     *
     * @param entityId unique id for object query
     * @return Object or null (if not exist in database or exception)
     */

    Optional<E> findById(Long entityId) throws DaoException;

    /**
     * Method for returning all object from database.
     *
     * @return list with all Object or null if exception
     */
    List<E> findAll(final Long airportId) throws DaoException;

    /**
     * Method for updating object in database.
     *
     * @param entity updating object
     * @return true if operation successful or false if exception
     */
    int update(E entity) throws DaoException;

    /**
     * Method for deleting object from database.
     *
     * @param entityId unique id for object query
     * @return true if operation successful or false if exception
     */
    int removeById(Long entityId) throws DaoException;

    /**
     * Method for creating new object into database.
     *
     * @param entity object for inserting
     * @return true if operation successful or false if exception
     */
    int insert(E entity)throws DaoException;

    /**
     * Method for creating new own class Object.
     *
     * @param resultSet returned values from the database
     * @return new Object
     */
    E getNewObjectFromResultSet(ResultSet resultSet) throws SQLException;
}
