package com.courses.tellus.autosalon.dao.springjdbc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.autosalon.dao.AutosalonDaoInterface;
import com.courses.tellus.autosalon.model.Auto;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AutoDao implements AutosalonDaoInterface<Auto> {

    private static final Logger LOGGER = Logger.getLogger(AutoDao.class);
    private final transient JdbcTemplate jdbcTemplate;

    public AutoDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Return list of auto from DataBase.
     *
     * @return list of auto.
     */
    @Override
    public List<Auto> getAll() {
        try {
            return jdbcTemplate.query("SELECT * FROM AUTO", new AutoMapper());
        } catch (DataAccessException e) {
            LOGGER.debug(e.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * Return auto from DataBase by id.
     *
     * @param autoId of the object to be selected from database.
     * @return auto.
     */
    @Override
    public Optional<Auto> getById(final Long autoId) {
        try {
            final Auto auto = jdbcTemplate.queryForObject(
                    "SELECT * FROM AUTO WHERE ID= ?", new Object[]{autoId}, new AutoMapper());
            return Optional.of(auto);
        } catch (DataAccessException e) {
            LOGGER.debug(e.getMessage());
            return Optional.empty();
        }
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
        try {
            return jdbcTemplate.update(sql, auto.getBrand(), auto.getModel(), auto.getManufactYear(), auto.getProducerCountry(),
                                            auto.getPrice(), auto.getId());
        } catch (DataAccessException e) {
            LOGGER.debug(e.getMessage());
            return -1;
        }
    }

    /**
     * Remove auto from DataBase by id.
     *
     * @param autoId of the object to be removed from database.
     * @return count of removed rows.
     */
    @Override
    public Integer delete(final Long autoId) {
        try {
            return jdbcTemplate.update("DELETE FROM AUTO WHERE ID = ?", autoId);
        } catch (DataAccessException e) {
            LOGGER.debug(e.getMessage());
            return -1;
        }
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
        try {
            return jdbcTemplate.update(sql, auto.getBrand(), auto.getModel(), auto.getManufactYear(),
                                            auto.getProducerCountry(), auto.getPrice());
        } catch (DataAccessException e) {
            LOGGER.debug(e.getMessage());
            return -1;
        }
    }
}
