package com.courses.tellus.autosalon.dao.springjdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AutoMapperTest {

    private AutoMapper autoMapper;

    @Mock
    ResultSet resultSet;

    @BeforeEach
    public void setUpBeforeTest() {
        MockitoAnnotations.initMocks(this);
        autoMapper = new AutoMapper();
    }

    @Test
    public void testMapRow() throws SQLException {
        autoMapper.mapRow(resultSet, 1);
    }
}
