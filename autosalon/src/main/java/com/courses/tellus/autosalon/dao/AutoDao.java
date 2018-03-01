package com.courses.tellus.autosalon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.courses.tellus.autosalon.config.ConnectionFactory;
import com.courses.tellus.autosalon.model.Auto;
import com.courses.tellus.autosalon.utils.AutoUtil;

public class AutoDao {

    private final transient ConnectionFactory connFactory;

    public AutoDao(final ConnectionFactory connFactory) {
        this.connFactory = connFactory;
    }

    /**
     * Extract auto from resultSet.
     *
     * @param resultSet to save.
     * @return auto.
     * @throws SQLException exception.
     */
    private Auto getAutoFromResultSet(final ResultSet resultSet) throws SQLException {
        final Auto auto = new Auto();
        auto.setId(resultSet.getLong("ID"));
        auto.setBrand(resultSet.getString("AUTO_BRAND"));
        auto.setModel(resultSet.getString("AUTO_MODEL"));
        auto.setManufactYear(resultSet.getInt("MANUFACT_YEAR"));
        auto.setProducerCountry(resultSet.getString("COUNTRY"));
        auto.setPrice(resultSet.getBigDecimal("PRICE"));
        return auto;
    }

    /**
     * Return list of auto from DataBase.
     *
     * @return list of auto.
     */
    public List<Auto> queryAuto() throws SQLException {
        final List<Auto> autoList = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = connFactory.getConnection()) {
            final PreparedStatement pstm = connection.prepareStatement("select * from AUTO");
            resultSet = pstm.executeQuery();
            while (resultSet.next()) {
              final Auto auto = getAutoFromResultSet(resultSet);
              autoList.add(auto);
            }
        } finally {
            resultSet.close();
        }
        return autoList;
    }

    /**
     * Return auto from database by id.
     *
     * @param idAuto to save.
     * @return auto.
     * @throws SQLException exception.
     */
    public Auto getAutoById(final Long idAuto) throws SQLException {
        ResultSet resultSet = null;
        try (Connection connection = connFactory.getConnection()) {
            final PreparedStatement pstm = connection.prepareStatement(
                    "select ID, AUTO_BRAND, AUTO_MODEL, MANUFACT_YEAR, COUNTRY, PRICE from AUTO  where ID = ?");
            pstm.setLong(AutoUtil.FIRST_STATEMENT.getStatement(), idAuto);
            resultSet = pstm.executeQuery();
            if (resultSet.next()) {
                 return getAutoFromResultSet(resultSet);
            }

        } finally {
            resultSet.close();
        }
        return null;
    }

    /**
     * Adding new auto to DataBase.
     *
     * @param auto to save.
     * @return 1.
     * @throws SQLException exception.
     */
    public int addAuto(final Auto auto) throws SQLException {
        try (Connection connection = connFactory.getConnection()) {
            final PreparedStatement pstm = connection.prepareStatement(
                    "insert into AUTO(AUTO_BRAND, AUTO_MODEL, MANUFACT_YEAR, COUNTRY, PRICE)values(?, ?, ?, ?, ?)");
            pstm.setString(AutoUtil.FIRST_STATEMENT.getStatement(), auto.getBrand());
            pstm.setString(AutoUtil.SECOND_STATEMENT.getStatement(), auto.getModel());
            pstm.setInt(AutoUtil.THIRD_STATEMENT.getStatement(), auto.getManufactYear());
            pstm.setString(AutoUtil.FOURTH_STATEMENT.getStatement(), auto.getProducerCountry());
            pstm.setBigDecimal(AutoUtil.FIFTH_STATEMENT.getStatement(), auto.getPrice());
            return pstm.executeUpdate();
        }
    }

    /**
     * Updating auto to DataBase.
     *
     * @param auto to save.
     * @return 1.
     * @throws SQLException exception.
     */
    public int updateAuto(final Auto auto) throws SQLException {
        try (Connection connection = connFactory.getConnection()) {
            final PreparedStatement pstm = connection.prepareStatement(
                    "update AUTO set AUTO_BRAND = ?, AUTO_MODEL = ?, MANUFACT_YEAR = ?, COUNTRY = ?, PRICE = ? where ID = ?");
            pstm.setString(AutoUtil.FIRST_STATEMENT.getStatement(), auto.getBrand());
            pstm.setString(AutoUtil.SECOND_STATEMENT.getStatement(), auto.getModel());
            pstm.setInt(AutoUtil.THIRD_STATEMENT.getStatement(), auto.getManufactYear());
            pstm.setString(AutoUtil.FOURTH_STATEMENT.getStatement(), auto.getProducerCountry());
            pstm.setBigDecimal(AutoUtil.FIFTH_STATEMENT.getStatement(), auto.getPrice());
            pstm.setLong(AutoUtil.SIXTH_STATEMENT.getStatement(), auto.getId());
            return pstm.executeUpdate();
        }
    }

    /**
     * Remove auto from DataBase by id.
     *
     * @param idAuto to save.
     * @return 1.
     * @throws SQLException exseption.
     */
    public int removeAutoById(final Long idAuto) throws SQLException {
        try (Connection connection = connFactory.getConnection()) {
            final PreparedStatement pstm = connection.prepareStatement("delete from AUTO where ID = ?");
            pstm.setLong(AutoUtil.FIRST_STATEMENT.getStatement(), idAuto);
            return pstm.executeUpdate();
        }
    }

}
