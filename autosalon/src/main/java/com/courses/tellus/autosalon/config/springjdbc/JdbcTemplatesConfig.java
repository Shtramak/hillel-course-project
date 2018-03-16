package com.courses.tellus.autosalon.config.springjdbc;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@PropertySource("config.properties")
public class JdbcTemplatesConfig {

    /**
     * Create dataSource.
     *
     * @return dateSource.
     */
    @Bean
    public DataSource dataSource() {
        final MysqlDataSource mysql = new MysqlDataSource();
        mysql.setURL("jdbc.url.mysql");
        mysql.setUser("jdbc.user.mysql");
        mysql.setPassword("jdbc.pass.mysql");
        return mysql;
    }

    /**
     * Create JdbcTemplate.
     *
     * @param dataSource to save.
     * @return jdbctemplate.
     */
    @Bean
    public JdbcTemplate jdbcTemplate(final DataSource dataSource) {
       return new JdbcTemplate(dataSource);
    }
}
