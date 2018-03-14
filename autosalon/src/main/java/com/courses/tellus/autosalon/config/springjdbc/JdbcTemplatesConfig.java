package com.courses.tellus.autosalon.config.springjdbc;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class JdbcTemplatesConfig {

    /**
     * @return Embedded database as a implementation of DataSource interface.
     */
    @Bean
    public DataSource dataSource() {
        final EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
        return dbBuilder
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }

    /**
     * @param dataSource - a factory for connections to the data source
     * @return JdbcTemplate class from Spring JDBC framework that executes SQL queries or updates.
     */
    @Bean
    public JdbcTemplate jdbcTemplate(final DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
