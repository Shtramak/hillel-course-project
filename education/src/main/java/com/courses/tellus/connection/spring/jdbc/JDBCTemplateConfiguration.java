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
     * dataSource that create test connection to H2 inmemory Database.
     * @return data source for code tests
     */
    @Bean
    @Profile("test")
    public DataSource embeddedDataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("database/h2/db_creation.sql")
                .build();
    }

    /**
     * dataSource that create connection to PostgreSQL Database.
     * @return data source for using
     */
    @Bean
    @Profile("prod")
    public DataSource pgDataSource() {
        final PGSimpleDataSource pgds = new PGSimpleDataSource();
        pgds.setUrl(env.getProperty("pg.url"));
        pgds.setUser(env.getProperty("pg.user"));
        pgds.setPassword(env.getProperty("pg.password"));
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