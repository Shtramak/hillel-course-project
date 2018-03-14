package com.courses.tellus.autosalon.dao.springjdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.autosalon.dao.AutosalonDaoInterface;
import com.courses.tellus.autosalon.model.Auto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AutoDaoImpl implements AutosalonDaoInterface<Auto> {

    private final transient JdbcTemplate jdbcTemplate;

    public AutoDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     *Return list of auto from DataBase.
     *
     * @return list of auto.
     */
    @Override
    public List<Auto> getAll() {
        final String sql = "select * from AUTO";
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> {
            return getAutoFromResultSet(resultSet);
        });
    }

    /**
     * Return auto from DataBase by id.
     *
     * @param autoId of the object to be inserted from database.
     * @return auto.
     */
    @Override
    public Optional<Auto> getById(final Long autoId) {
        final String sql = "SELECT * FROM AUTO WHERE ID= ?";
        final Auto auto = jdbcTemplate.queryForObject(sql, new Object[]{autoId}, (resultSet, rowNum) -> {
            return getAutoFromResultSet(resultSet);
        });
        return Optional.of(auto);
    }

    /**
     * Update auto to DataBase.
     *
     * @param auto of the object to be updated to database.
     * @return count of updated rows.
     */
    @Override
    public Integer update(final Auto auto) {
        final String sql =
                "UPDATE AUTO SET AUTO_BRAND = ?, AUTO_MODEL = ?, MANUFACT_YEAR = ?, COUNTRY = ?, PRICE = ? WHERE ID = ?";
        return jdbcTemplate.update(sql, auto.getBrand(), auto.getModel(), auto.getManufactYear(), auto.getProducerCountry(),
                auto.getPrice(), auto.getId());
    }

    /**
     *  Remove auto from DataBase by id.
     *
     * @param autoId of the object to be removed from database.
     * @return count of removed rows.
     */
    @Override
    public Integer delete(final Long autoId) {
        final String sql = "DELETE FROM AUTO WHERE ID = ?";
        return jdbcTemplate.update(sql, autoId);
    }

    /**
     * Add new auto to DataBase.
     *
     * @param auto of the object to be inserted to database.
     * @return count of added rows.
     */
    @Override
    public Integer insert(final Auto auto) {
        final String sql = "INSERT INTO AUTO(AUTO_BRAND, AUTO_MODEL, MANUFACT_YEAR, COUNTRY, PRICE) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, auto.getBrand(), auto.getModel(), auto.getManufactYear(),
                auto.getProducerCountry(), auto.getPrice());
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
}
