package com.courses.tellus.autosalon.config.springjdbc;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.RunScript;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@PropertySource("classpath:config.properties")
@ComponentScan("com.courses.tellus.autosalon.dao.springjdbc")
public class JdbcTemplatesConfig {

    /**
     * Create dataSource.
     *
     * @return dateSource.
     */
/*
    @Bean
    public DataSource mySqlDataSource() {
        final MysqlDataSource mysql = new MysqlDataSource();
        mysql.setURL("jdbc.url.mysql");
        mysql.setUser("jdbc.user.mysql");
        mysql.setPassword("jdbc.pass.mysql");
        return mysql;
    }

*/

/*
    @Bean
    public DataSource embeddedDataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("test.sql")
                .build();
    }
*/
    @Bean
    public DataSource embeddedDataSource() throws SQLException, FileNotFoundException {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setPassword("sa");
        dataSource.setUser("sa");
        dataSource.setURL("jdbc:h2:file:/home/andrii/Desktop/Project/tellus/autosalon/out/db;AUTO_SERVER=TRUE");
        Connection connection = dataSource.getConnection();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream("test.sql");
        RunScript.execute(connection, new InputStreamReader(resourceAsStream));
        return dataSource;
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
