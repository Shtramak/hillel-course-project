package com.courses.tellus.connection.spring.jdbc;

import javax.sql.DataSource;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@PropertySource("classpath:properties/db.properties")
public class JDBCTemplateConfiguration {

    @Autowired private transient Environment env;

    /**
     * dataSource that create connection to PostgreSQL Database.
     * @return data source for using
     */
    @Bean
    public DataSource pgDataSource() {
        final PGSimpleDataSource pgds = new PGSimpleDataSource();
        pgds.setUrl("");
        pgds.setUser("");
        pgds.setPassword("");
        return pgds;
    }

    /**
     * Provide connection from selected dataSource.
     * @param dataSource profiled data source
     * @return JdbcTemplate for queries
     */
    @Bean
    public JdbcTemplate jdbcTemplate(final DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}