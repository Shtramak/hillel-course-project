package com.courses.tellus.config.spring.jdbc;

import javax.sql.DataSource;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@PropertySource("classpath:properties/db.properties")
public class JDBCTemplateConfiguration {

    @Autowired
    private transient Environment env;

    /**
     * dataSource that create config to PostgreSQL Database.
     * @return data source for using
     */
    @Bean
    public DataSource pgDataSource() {
        final PGSimpleDataSource pgds = new PGSimpleDataSource();
        pgds.setUrl(env.getProperty("pg.url"));
        pgds.setUser(env.getProperty("pg.user"));
        pgds.setPassword(env.getProperty("pg.password"));
        return pgds;
    }

    /**
     * Provide config from selected dataSource.
     * @param dataSource profiled data source
     * @return JdbcTemplate for queries
     */
    @Bean
    public JdbcTemplate jdbcTemplate(final DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}