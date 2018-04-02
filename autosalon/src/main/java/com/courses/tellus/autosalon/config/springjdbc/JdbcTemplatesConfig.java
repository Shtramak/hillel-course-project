package com.courses.tellus.autosalon.config.springjdbc;

import java.sql.SQLException;
import javax.sql.DataSource;

import java.io.FileNotFoundException;

import com.courses.tellus.autosalon.dao.springjdbc.AutosalonDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@ComponentScan("com.courses.tellus.autosalon.dao.springjdbc")
public class JdbcTemplatesConfig {

    /**
     *
     * @return dataSource.
     * @throws SQLException if smth wrong
     */

    @Bean
    public DataSource dataSource()  {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("test.sql")
                .build();
    }

    /**
     *
     * @param dataSource is dataSource.
     * @return JdbcTemplate
     */

    @Bean
    public JdbcTemplate jdbcTemplate(final DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /**
     * Create AutosalonDaoEntyty.
     *
     *
     * @return new AutosalonDaoEntyty.
     */

    @Bean
    public AutosalonDao autosalonDao() throws SQLException, FileNotFoundException {
        return new AutosalonDao(jdbcTemplate(dataSource()));
    }
}
