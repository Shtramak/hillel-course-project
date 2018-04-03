package com.courses.tellus.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.courses.tellus.exception.jdbc.DatabaseConnectionException;
import com.courses.tellus.exception.jdbc.EntityIdNotFoundException;

public interface BasicDao<E> {

    /**
     * Method for returning all object from database in Option wrapper.
     *
     * @return list with all Object or empty Optional if exception
     */
    List<E> getAll() throws DatabaseConnectionException;

    /**
     * Method for returning object by unique id from database in Option wrapper.
     *
     * @param entityId unique id for object query
     * @return Object in Option wrapper or empty Optional (if not exist in database or exception)
     */
    E getById(Long entityId) throws DatabaseConnectionException, EntityIdNotFoundException;

    /**
     * Method for updating object in database.
     *
     * @param entity updating object
     * @return number of successful operations
     */
    int update(E entity) throws DatabaseConnectionException;

    /**
     * Method for deleting object from database.
     *
     * @param entityId unique id for object query
     * @return number of successful operations
     */
    int delete(Long entityId) throws DatabaseConnectionException;

    /**
     * Method for creating new object into database.
     *
     * @param entity object for inserting
     * @return number of successful operations
     */
    int insert(E entity) throws DatabaseConnectionException;

    /**
     * Method for creating new own class Object.
     *
     * @param resultSet returned values from the database
     * @return new Object
     */
    E getNewObjectFromResultSet(ResultSet resultSet) throws SQLException;
}