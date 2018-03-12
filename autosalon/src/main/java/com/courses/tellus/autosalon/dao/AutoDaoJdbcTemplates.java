package com.courses.tellus.autosalon.dao;

import com.courses.tellus.autosalon.model.Auto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class AutoDaoJdbcTemplates implements AutosalonDaoInterface<Auto> {

    private JdbcTemplate jdbcTemplate;

    public AutoDaoJdbcTemplates(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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

    @Override
    public List<Auto> getAll() {
        return jdbcTemplate.query("select * from auto", (resultSet, rowNum) -> {
            return getAutoFromResultSet(resultSet);
        });
    }

    @Override
    public Optional<Auto> getById(Long entityId) {
       return null;
    }

    @Override
    public Integer update(Auto auto) {
        final String sql = "update AUTO set AUTO_BRAND = ?, AUTO_MODEL = ?, MANUFACT_YEAR = ?, COUNTRY = ?, PRICE = ? where ID = ?";
        return jdbcTemplate.update(sql, auto.getBrand(), auto.getModel(), auto.getManufactYear(), auto.getProducerCountry(),
                                        auto.getPrice(), auto.getId());
    }

    @Override
    public Integer delete(Long entityId) {
        return jdbcTemplate.update("delete from auto where id = ?", entityId);
    }

    @Override
    public Integer insert(Auto auto) {
        final String sql = "insert into AUTO(AUTO_BRAND, AUTO_MODEL, MANUFACT_YEAR, COUNTRY, PRICE)values(?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, auto.getBrand(), auto.getModel(), auto.getManufactYear(),
                auto.getProducerCountry(), auto.getPrice());
    }
}
