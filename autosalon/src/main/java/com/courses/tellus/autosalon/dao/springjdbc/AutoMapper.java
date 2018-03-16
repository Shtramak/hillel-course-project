package com.courses.tellus.autosalon.dao.springjdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.courses.tellus.autosalon.model.Auto;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

public class AutoMapper implements RowMapper<Auto> {

    /**
     *Implementation RowMapper for Auto.
     *
     * @param resultSet to save.
     * @param rowNum to save.
     * @return auto.
     * @throws SQLException exception.
     */
    @Nullable
    @Override
    public Auto mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
        final Auto auto = new Auto();
        auto.setId(resultSet.getLong("ID"));
        auto.setBrand(resultSet.getString("AUTO_BRAND"));
        auto.setModel(resultSet.getString("AUTO_MODEL"));
        auto.setManufactYear(resultSet.getInt("MANUFACT_YEAR"));
        auto.setProducerCountry(resultSet.getString("COUNTRY"));
        auto.setPrice(resultSet.getBigDecimal("PRICE"));
        return auto;
    }
}
