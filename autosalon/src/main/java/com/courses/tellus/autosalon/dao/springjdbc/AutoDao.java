package com.courses.tellus.autosalon.dao.springjdbc;

import java.util.List;
import java.util.Optional;

import com.courses.tellus.autosalon.dao.AutosalonDaoInterface;
import com.courses.tellus.autosalon.model.Auto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AutoDao implements AutosalonDaoInterface<Auto> {

    private final transient JdbcTemplate jdbcTemplate;

    public AutoDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     *Return list of auto from DataBase.
     *
     * @return list of auto.
     */
    @Override
    public List<Auto> getAll() {
        return jdbcTemplate.query("select * from AUTO", new AutoMapper());
    }

    /**
     * Return auto from DataBase by id.
     *
     * @param autoId  of the object to be selected from database.
     * @return auto.
     */
    @Override
    public Optional<Auto> getById(final Long autoId) {
        final Auto auto = jdbcTemplate.queryForObject("SELECT * FROM AUTO WHERE ID= ?", new Object[]{autoId}, new AutoMapper());
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
        return jdbcTemplate.update("DELETE FROM AUTO WHERE ID = ?", autoId);
    }

    /**
     * Add new auto to DataBase.
     *
     * @param auto of the object to be inserted to database.
     * @return count of added rows.
     */
    @Override
    public Integer insert(final Auto auto) {
        final String sql =
                "INSERT INTO AUTO(AUTO_BRAND, AUTO_MODEL, MANUFACT_YEAR, COUNTRY, PRICE) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, auto.getBrand(), auto.getModel(), auto.getManufactYear(),
                                        auto.getProducerCountry(), auto.getPrice());
    }
}
