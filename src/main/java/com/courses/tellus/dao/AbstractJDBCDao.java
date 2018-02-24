//package main.java.com.courses.tellus.dao;
//
//import java.io.Serializable;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.List;
//
//public abstract class AbstractJDBCDao <T, PK extends Serializable> implements GenericDao<T, PK> {
//
//    private Connection connection;
//
//
//    public abstract String getSelectQuery();
//
//
//    protected abstract List<T> parseResultSet(ResultSet rs);
//
//    @Override
//    public T getByPK(int key) throws PersistException {
//        List<T> list;
//        String sql = getSelectQuery();
//        sql += " WHERE id = ?";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setInt(1, key);
//            ResultSet rs = statement.executeQuery();
//            list = parseResultSet(rs);
//        } catch (Exception e) {
//            throw new PersistException(e);
//        }
//        if (list == null || list.size() == 0) {
//            return null;
//        }
//        if (list.size() > 1) {
//            throw new PersistException("Received more than one record.");
//        }
//        return list.iterator().next();
//    }
//
//    @Override
//    public List<T> getAll() throws PersistException {
//        List<T> list;
//        String sql = getSelectQuery();
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            ResultSet rs = statement.executeQuery();
//            list = parseResultSet(rs);
//        } catch (Exception e) {
//            throw new PersistException(e);
//        }
//        return list;
//    }
//
//
//
//    public AbstractJDBCDao(Connection connection) {
//        this.connection = connection;
//    }
//}
