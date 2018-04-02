package com.courses.tellus.autosalon.config.springjdbc;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.RunScript;
import java.io.FileNotFoundException;

import com.courses.tellus.autosalon.dao.AutosalonDaoInterface;
import com.courses.tellus.autosalon.dao.springjdbc.AutosalonDao;
import com.courses.tellus.autosalon.model.Autosalon;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ComponentScan("com.courses.tellus.autosalon.dao.springjdbc")
public class JdbcTemplatesConfig {

    @Value("${jdbc.url.mysql}")
    private transient String url;

    @Value("${jdbc.user.mysql}")
    private transient String user;

    @Value("${jdbc.pass.mysql}")
    private transient String password;

    /**
     *
     * @return dataSource.
     * @throws SQLException if smth wrong
     */
    @Bean
    public DataSource embeddedDataSource() throws SQLException {
        final JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setPassword("sa");
        dataSource.setUser("sa");
        dataSource.setURL("jdbc:h2:file:/home/andrii/Desktop/Project/tellus/autosalon/out/db;AUTO_SERVER=TRUE");
        final Connection connection = dataSource.getConnection();
        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        final InputStream resourceAsStream = classLoader.getResourceAsStream("test.sql");
        RunScript.execute(connection, new InputStreamReader(resourceAsStream));
        return dataSource;
    }

    public DataSource dataSource() {
        final MysqlDataSource mysql = new MysqlDataSource();
        mysql.setURL(url);
        mysql.setUser(user);
        mysql.setPassword(password);
        return mysql;
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
    public AutosalonDaoInterface<Autosalon> autosalonDaoEntyty() throws SQLException, FileNotFoundException {
        return new AutosalonDao(jdbcTemplate(dataSource()));
    }
}
