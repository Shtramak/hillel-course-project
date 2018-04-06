package com.courses.tellus.airport.dao.spring.jdbc;

import com.courses.tellus.airport.dao.spring.jdbc.AirportMapper;
import com.courses.tellus.airport.model.Airport;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AirportMapperTest {
    private static final Airport TEST_AIRORT = new Airport(1L, "Borysbil", LocalDate.of(1990, 1, 1), "D", "04466666666");

    @Test
    void ticketMapperMapRowReturnsTicket() throws SQLException {
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.getLong("id")).thenReturn(TEST_AIRORT.getAirportId());
        when(resultSetMock.getString("name")).thenReturn(TEST_AIRORT.getNameAirport());
        when(resultSetMock.getDate("date_of_birth")).thenReturn(Date.valueOf(TEST_AIRORT.getDateOfBirth()));
        when(resultSetMock.getString("terminal")).thenReturn(TEST_AIRORT.getNumberTerminal());
        when(resultSetMock.getString("phone_number")).thenReturn(TEST_AIRORT.getTelephone());
        AirportMapper airportMapper = new AirportMapper();
        assertEquals(TEST_AIRORT, airportMapper.mapRow(resultSetMock, 1));
    }
}
