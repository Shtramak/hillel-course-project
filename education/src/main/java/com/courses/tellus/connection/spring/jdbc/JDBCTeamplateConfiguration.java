package com.courses.tellus.connection.spring.jdbc;

import javax.sql.DataSource;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class JDBCTeamplateConfiguration {

    @Bean
    @Profile("test")
    public DataSource embeddedDataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("db_creation.sql")
                .build();
    }

    @Bean
    @Profile("prod")
    public DataSource pgDataSource() {
        PGSimpleDataSource pgds = new PGSimpleDataSource();
        pgds.setUrl("");
        pgds.setUser("");
        pgds.setPassword("");
        return pgds;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}