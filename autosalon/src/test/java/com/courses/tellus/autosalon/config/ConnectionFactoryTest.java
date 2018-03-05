package com.courses.tellus.autosalon.config;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;

import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;

public class ConnectionFactoryTest {

    @Test
    public void openConnBeforerStmtTestASE() throws SQLException, IOException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        assertTrue(connection != null);
    }

    @Test
    public void getConnectionWithReflectedDataSourceField() throws IOException {
        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        try {
            Field reflDataSource = connectionFactory.getClass().getDeclaredField("dataSource");
            reflDataSource.setAccessible(true);
            JdbcDataSource dataSource = (JdbcDataSource) reflDataSource.get(connectionFactory);
            when(dataSource.getConnection()).thenThrow(SQLException.class);
            assertThrows(SQLException.class, () -> dataSource.getConnection());
            assertTrue(connectionFactory.getConnection()==null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
