package com.courses.tellus.autosalon.dao.springjdbc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.autosalon.dao.AutosalonDaoInterface;
import com.courses.tellus.autosalon.model.Autosalon;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AutosalonDao implements AutosalonDaoInterface<Autosalon> {

    private static final Logger LOGGER = Logger.getLogger(AutosalonDao.class);
    private final transient JdbcTemplate jdbcTemplate;

    public AutosalonDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     *Return list of auto from DataBase.
     *
     * @return list of autosalon.
     */

    @Override
    public List<Autosalon> getAll() {
        try {
            return jdbcTemplate.query("SELECT * FROM infoSalon", new AutosalonMapper());
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * Return autosalon from DataBase by id.
     *
     * @param entityId  of the object to be selected from database.
     * @return autosalon.
     */

    @Override
    public Optional<Autosalon> getById(final Long entityId) {
        try {
            final Autosalon optionalAutosalon = jdbcTemplate.queryForObject("SELECT * FROM infoSalon WHERE ID =?",
                    new Object[]{entityId}, new AutosalonMapper());
            return Optional.of(optionalAutosalon);
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage());
            return Optional.empty();
        }
    }

    /**
     * Update autosalon to DataBase.
     *
     * @param autosalon of the object to be updated to database.
     * @return count of updated rows.
     */

    @Override
    public Integer update(final Autosalon autosalon) {
        try {
            return jdbcTemplate.update("UPDATE infoSalon SET name = ?, address = ?, telephone = ? WHERE id = ?",
                    autosalon.getName(), autosalon.getAddress(), autosalon.getTelephone(), autosalon.getId());
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage());
            return -1;
        }
    }

    /**
     *  Remove autosalon from DataBase by id.
     *
     * @param entityId of the object to be removed from database.
     * @return count of removed rows.
     */

    @Override
    public Integer delete(final Long entityId) {
        try {
            return jdbcTemplate.update("DELETE FROM infoSalon WHERE id = ?", entityId);
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage());
            return -1;
        }
    }

    /**
     * Add new autosalon to DataBase.
     *
     * @param autosalon of the object to be inserted to database.
     * @return count of added rows.
     */

    @Override
    public Integer insert(final Autosalon autosalon) {
        try {
            return jdbcTemplate.update("INSERT INTO infoSalon (name,address,telephone) VALUES(?,?,?)",
                    autosalon.getName(), autosalon.getAddress(), autosalon.getTelephone());
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage());
            return -1;
        }
    }
}
