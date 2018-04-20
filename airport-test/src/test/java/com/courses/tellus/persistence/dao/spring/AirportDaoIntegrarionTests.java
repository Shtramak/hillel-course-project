package com.courses.tellus.persistence.dao.spring;

import com.courses.tellus.airport.dao.spring.jdbc.AirportDao;
import com.courses.tellus.airport.model.Airport;
import com.courses.tellus.persistence.dao.datasource.TestDatasource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestDatasource.class, AirportDao.class})
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                scripts = "classpath:spring/airport-table-create.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                scripts = "classpath:spring/airport-table-delete.sql")
})

public class AirportDaoIntegrarionTests {

    @Autowired
    private AirportDao airportDao;
    private Airport airport = new Airport(1L, "Borysbil", LocalDate.of(1990, 1, 1), "D", "04466666666");

    @Test
    void getAllWhenReturnsAirportsList() { assertEquals(1, airportDao.getAll().size()); }

    @Test
    void getAllWhenReturnsZero() {
        airportDao.delete(1L);
        assertEquals(0, airportDao.getAll().size());
    }

    @Test
    void testGetByIdWhenReturnsAirport() {
        assertEquals(airport, airportDao.getById(1L).get());
    }

    @Sql("classpath:spring/airport-table-delete.sql")
    @Test
    void getByIdWhenTableNotExists() {
        assertEquals(Optional.empty(), airportDao.getById(10L));
    }

    @Test
    void testInsertWhenUpdatedSuccessfully() {
        assertEquals(Integer.valueOf(1), airportDao.insert(airport));
    }

    @Test
    void testUpdateWhenUpdatedSuccessfully() {
        airport.setNameAirport("Zhuliani");
        assertEquals(Integer.valueOf(1), airportDao.update(airport));
    }

    @Test
    void testDeleteWhenUpdatedSuccessfully() {
        assertEquals(Integer.valueOf(1), airportDao.delete(1L));
    }
}