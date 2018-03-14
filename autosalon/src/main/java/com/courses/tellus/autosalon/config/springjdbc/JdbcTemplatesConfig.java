package com.courses.tellus.autosalon.config.springjdbc;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@PropertySource(value = {"config.properties"})
public class JdbcTemplatesConfig {

    @Bean
    public DataSource dataSource(){
        MysqlDataSource mysql = new MysqlDataSource();
        mysql.setURL("jdbc.url.mysql");
        mysql.setUser("jdbc.user.mysql");
        mysql.setPassword("jdbc.pass.mysql");
        return mysql;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
       return new JdbcTemplate(dataSource);
    }
}
