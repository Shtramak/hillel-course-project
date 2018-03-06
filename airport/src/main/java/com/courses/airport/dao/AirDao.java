package com.courses.airport.dao;


import com.courses.airport.essences.Airport;

import java.sql.SQLException;
import java.util.List;

public interface AirDao {
    Airport create();

    Airport read(int key) throws SQLException;

    void update(Airport airport);

    void delete(Airport airport);

    List<Airport> getAll() throws SQLException;
}
