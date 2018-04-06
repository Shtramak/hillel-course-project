package com.courses.tellus.airport.dao.spring.jdbc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.airport.dao.AirportDaoInterface;
import com.courses.tellus.airport.model.Airport;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AirportDao implements AirportDaoInterface<Airport> {
    private static final Logger LOGGER = Logger.getLogger(AirportDao.class);
    private final transient JdbcTemplate jdbcTemplate;

    @Autowired
    public AirportDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Airport> getAll() {
        try {
            return jdbcTemplate.query("SELECT * FROM airport_airports", new AirportMapper());
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<Airport> getById(final Long airportId) {
        try {
            final Airport airport = jdbcTemplate.queryForObject("SELECT * FROM airport_airports "
                    + "WHERE id=" + airportId, new AirportMapper());
            return Optional.of(airport);
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Integer update(final Airport airport) {
        final String sql = "UPDATE airport_airports SET name = ?, date_of_birth = ?, terminal = ?, phone_number = ? "
                + "WHERE id = ?";
        try {
            return jdbcTemplate.update(sql, airport.getNameAirport(), airport.getDateOfBirth(), airport.getNumberTerminal(),
                    airport.getTelephone(), airport.getAirportId());
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage());
            return -1;
        }
    }

    @Override
    public Integer delete(final Long airportId) {
        try {
            return jdbcTemplate.update("DELETE FROM airport_airports WHERE id = " + airportId);
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage());
            return -1;
        }
    }

    @Override
    public Integer insert(final Airport airport) {
        final String sql = "INSERT INTO airport_airports (name, date_of_birth, terminal, phone_number) VALUES (?, ?, ?, ?)";
        try {
            return jdbcTemplate.update(sql, airport.getNameAirport(), airport.getDateOfBirth(),
                    airport.getNumberTerminal(), airport.getTelephone());
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage());
            return -1;
        }
    }
}