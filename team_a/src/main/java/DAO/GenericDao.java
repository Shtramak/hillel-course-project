package main.java.DAO;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface GenericDao <T, PK extends Serializable> {

    T create() throws SQLException;

    T persist(T object)  throws SQLException;

    T getByPK(int key) throws SQLException;

    void update(T object) throws SQLException;

    void delete(T object) throws SQLException;

    List<T> getAll() throws SQLException;
}
