package com.courses.tellus.dao;

import com.courses.tellus.db_connection.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDao<E, K> {

    public Connection conn = ConnectionUtils.getConnection();

    public abstract List<E> getAll();

    public abstract E getEntityById(K id);

    public abstract void update(E entity);

    public abstract void delete(K id);

    public abstract void create(E entity);

    /**
     * Close connection to the database after executing query;
     * @param ps
     */
    public static void closePrepareStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
