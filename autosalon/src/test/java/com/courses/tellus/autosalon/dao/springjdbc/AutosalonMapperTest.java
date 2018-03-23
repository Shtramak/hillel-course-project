package com.courses.tellus.autosalon.dao.springjdbc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AutosalonMapperTest {

    private AutosalonMapper autosalonMapper;

    @Mock
    ResultSet resultSet;

    @BeforeEach
    public void setUpBeforeTest() {
        MockitoAnnotations.initMocks(this);
        autosalonMapper = new AutosalonMapper();
    }

    @Test
    public void testMapRow() throws SQLException {
        autosalonMapper.mapRow(resultSet, 1);
    }
}
