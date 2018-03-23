package com.courses.tellus.autosalon.config.springjdbc;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.courses.tellus.autosalon.dao.AutosalonDaoEntyty;
import com.courses.tellus.autosalon.dao.springjdbc.AutosalonDao;
import com.courses.tellus.autosalon.service.AutosalonService;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@PropertySource("classpath:config.properties")
public class JdbcTemplatesConfig {

    @Autowired
    private transient Environment environment;

    /**
     * Create dataSource.
     *
     * @return dateSource.
     */
    @Bean
    public DataSource dataSource() {
        final MysqlDataSource mysql = new MysqlDataSource();
        mysql.setURL(environment.getProperty("jdbc.url.mysql"));
        mysql.setUser(environment.getProperty("jdbc.user.mysql"));
        mysql.setPassword(environment.getProperty("jdbc.pass.mysql"));
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

    /**
     * Create AutosalonService.
     *
     *
     * @return new AutosalonService.
     */

    @Bean
    public AutosalonService autosalonService() {
        return new AutosalonService();
    }

    /**
     * Create AutosalonDaoEntyty.
     *
     *
     * @return new AutosalonDaoEntyty.
     */

    @Bean
    public AutosalonDaoEntyty autosalonDaoEntyty() throws SQLException, FileNotFoundException {
        return new AutosalonDao(jdbcTemplate(dataSource()));
    }
}
