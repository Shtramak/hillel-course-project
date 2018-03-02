package com.courses.tellus.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Supplier;

public interface BasicDao<E, K> extends Supplier<Connection> {

    /**
     * Method for returning all object from database.
     *
     * @return list with all Object
     */
    List<E> getAllObject() throws SQLException;

    /**
     * Method for returning object by unique id from database.
     *
     * @param entityId unique id for object query
     * @return Object
     */
    E getEntityById(K entityId) throws SQLException;

    /**
     * Method for updating object in database.
     *
     * @param entity updating object
     */
    void update(E entity) throws SQLException;

    /**
     * Method for deleting object from database.
     * @param entityId unique id for object query
     */
    void delete(K entityId) throws SQLException;

    /**
     * Method for creating new object into database.
     *
     * @param entity object for inserting
     */
    void create(E entity) throws SQLException;

    /**
     * Method for creating new own class Object.
     *
     * @param resultSet returned values from the database
     * @return new Object
     */
    E getNewObjectFromResultSet(ResultSet resultSet) throws SQLException;
}
