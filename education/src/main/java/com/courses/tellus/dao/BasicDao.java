package com.courses.tellus.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface BasicDao<E, K> {

    /**
     * Method for returning all object from database.
     *
     * @return list with all Object
     */
    List<E> getAllEntity();

    /**
     * Method for returning object by unique id from database.
     *
     * @param entityId unique id for object query
     * @return Object
     */
    E getEntityById(K entityId);

    /**
     * Method for updating object in database.
     *
     * @param entity updating object
     */
    boolean update(E entity);

    /**
     * Method for deleting object from database.
     * @param entityId unique id for object query
     */
    boolean delete(K entityId);

    /**
     * Method for creating new object into database.
     *
     * @param entity object for inserting
     */
    boolean insert(E entity);

    /**
     * Method for creating new own class Object.
     *
     * @param resultSet returned values from the database
     * @return new Object
     */
    E getNewObjectFromResultSet(ResultSet resultSet) throws SQLException;
}