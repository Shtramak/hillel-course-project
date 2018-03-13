package com.courses.tellus.connection.spring.jdbc;

import javax.sql.DataSource;

import org.postgresql.ds.PGSimpleDataSource;
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
                .addScript(" ")
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