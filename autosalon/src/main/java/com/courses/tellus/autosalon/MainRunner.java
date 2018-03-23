package com.courses.tellus.autosalon;

import com.courses.tellus.autosalon.config.springjdbc.JdbcTemplatesConfig;
import com.courses.tellus.autosalon.dao.springjdbc.AutosalonDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainRunner {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(JdbcTemplatesConfig.class);
        System.out.println(context.getBean(AutosalonDao.class).getAll());



    }
}
