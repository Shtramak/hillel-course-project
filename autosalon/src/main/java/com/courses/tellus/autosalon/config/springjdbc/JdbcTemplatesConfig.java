package com.courses.tellus.autosalon.config.springjdbc;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.courses.tellus.autosalon.dao.AutosalonDaoInterface;
import com.courses.tellus.autosalon.dao.springjdbc.AutosalonDao;
import com.courses.tellus.autosalon.model.Autosalon;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@PropertySource("classpath:config.properties")
public class JdbcTemplatesConfig {

    @Value("${jdbc.url.mysql}")
    private String url;

    @Value("${jdbc.user.mysql}")
    private String user;

    @Value("${jdbc.pass.mysql}")
    private String password;
    /**
     * Create dataSource.
     *
     * @return dateSource.
     */
    @Bean
    public DataSource dataSource() {
        final MysqlDataSource mysql = new MysqlDataSource();
        mysql.setURL(url);
        mysql.setUser(user);
        mysql.setPassword(password);
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
     * Create AutosalonDaoEntyty.
     *
     *
     * @return new AutosalonDaoEntyty.
     */

    @Bean
    public AutosalonDaoInterface<Autosalon> autosalonDaoEntyty() throws SQLException, FileNotFoundException {
        return new AutosalonDao(jdbcTemplate(dataSource()));
    }
}
