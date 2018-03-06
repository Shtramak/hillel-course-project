package com.courses.tellus.autosalon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.courses.tellus.autosalon.config.ConnectionFactory;
import com.courses.tellus.autosalon.model.Auto;
import org.apache.log4j.Logger;

public class AutoDao {

    private static final Logger LOGGER = Logger.getLogger(AutoDao.class);
    private final transient ConnectionFactory connFactory;

    private static final Integer AUTO_BRAND = 1;
    private static final Integer AUTO_MODEL = 2;
    private static final Integer MANUFACT_YEAR = 3;
    private static final Integer COUNTRY = 4;
    private static final Integer PRICE = 5;
    private static final Integer ID_AUTO = 6;

    public AutoDao(final ConnectionFactory connFactory) {
        this.connFactory = connFactory;
    }

    /**
     * Extract auto from resultSet.
     *
     * @param resultSet to save.
     * @return auto.
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
     * Set Statement values.
     *
     * @param pstm to save.
     * @param auto to save.
     */
    private void setStatementValues(final PreparedStatement pstm, final Auto auto) throws SQLException {
            pstm.setString(AUTO_BRAND, auto.getBrand());
            pstm.setString(AUTO_MODEL, auto.getModel());
            pstm.setInt(MANUFACT_YEAR, auto.getManufactYear());
            pstm.setString(COUNTRY, auto.getProducerCountry());
            pstm.setBigDecimal(PRICE, auto.getPrice());
            pstm.setLong(ID_AUTO, auto.getId());
    }


    /**
     * Return list of auto from DataBase.
     *
     * @return list of auto.
     */
    public List<Auto> queryAuto() {
        final List<Auto> autoList = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = connFactory.getConnection()) {
            final PreparedStatement pstm = connection.prepareStatement("select * from AUTO");
            resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                final Auto auto = getAutoFromResultSet(resultSet);
                autoList.add(auto);
            }
        } catch (SQLException e) {
            LOGGER.debug(e.getMessage());
        }
        return autoList;
    }

    /**
     * Return auto from database by id.
     *
     * @param idAuto to save.
     * @return auto.
     */
    public Auto getAutoById(final Long idAuto) {
        ResultSet resultSet = null;
        try (Connection connection = connFactory.getConnection()) {
            final PreparedStatement pstm = connection.prepareStatement(
                    "select ID, AUTO_BRAND, AUTO_MODEL, MANUFACT_YEAR, COUNTRY, PRICE from AUTO  where ID = ?");
            pstm.setLong(1, idAuto);
            resultSet = pstm.executeQuery();
            if (resultSet.next()) {
                return getAutoFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.debug(e.getMessage());
        }
        return null;
    }

    /**
     * Adding new auto to DataBase.
     *
     * @param auto to save.
     * @return result. If result true return 1 else return 0.
     */
    public int addAuto(final Auto auto) {
        try (Connection connection = connFactory.getConnection()) {
            final PreparedStatement pstm = connection.prepareStatement(
                    "insert into AUTO(AUTO_BRAND, AUTO_MODEL, MANUFACT_YEAR, COUNTRY, PRICE)values(?, ?, ?, ?, ?)");
            pstm.setString(AUTO_BRAND, auto.getBrand());
            pstm.setString(AUTO_MODEL, auto.getModel());
            pstm.setInt(MANUFACT_YEAR, auto.getManufactYear());
            pstm.setString(COUNTRY, auto.getProducerCountry());
            pstm.setBigDecimal(PRICE, auto.getPrice());
            return pstm.executeUpdate();
        } catch (SQLException e) {
            LOGGER.debug(e.getMessage());
        }
        return 0;
    }

    /**
     * Updating auto to DataBase.
     *
     * @param auto to save.
     * @return 1 if true else 0.
     */
    public int updateAuto(final Auto auto) {
        try (Connection connection = connFactory.getConnection()) {
            final PreparedStatement pstm = connection.prepareStatement(
                    "update AUTO set AUTO_BRAND = ?, AUTO_MODEL = ?, MANUFACT_YEAR = ?, COUNTRY = ?, PRICE = ? where ID = ?");
            setStatementValues(pstm, auto);
            return pstm.executeUpdate();
        } catch (SQLException e) {
            LOGGER.debug(e.getMessage());
        }
        return 0;
    }

    /**
     * Remove auto from DataBase by id.
     *
     * @param idAuto to save.
     * @return 1 if true else 0.
     */
    public int removeAutoById(final Long idAuto) {
        try (Connection connection = connFactory.getConnection()) {
            final PreparedStatement pstm = connection.prepareStatement("delete from AUTO where ID = ?");
            pstm.setLong(1, idAuto);
            final int rowsDeleted = pstm.executeUpdate();
            if (rowsDeleted > 0) {
                return 1;
            }
        } catch (SQLException e) {
            LOGGER.debug(e.getMessage());
        }
        return 0;
    }
}
