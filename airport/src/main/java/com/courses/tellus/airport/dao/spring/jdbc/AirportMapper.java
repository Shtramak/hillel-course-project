package com.courses.tellus.airport.dao.spring.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.courses.tellus.airport.model.Airport;
import org.springframework.jdbc.core.RowMapper;

public class AirportMapper implements RowMapper<Airport> {
    @Override
    public Airport mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
        final Airport airport = new Airport();
        airport.setAirportId(resultSet.getLong("id"));
        airport.setNameAirport(resultSet.getString("name"));
        airport.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
        airport.setNumberTerminal(resultSet.getString("terminal"));
        airport.setTelephone(resultSet.getString("phone_number"));
        return airport;
    }
}
