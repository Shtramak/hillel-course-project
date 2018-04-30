package com.courses.tellus.config.spring.mvc;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(final ServletContext servletContext) throws ServletException {
        final AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(WebAppConfig.class, JdbcTemplateConfig.class);
        ctx.setServletContext(servletContext);

        final ServletRegistration.Dynamic servlet = servletContext.addServlet("springDispatcherServlet",
                new DispatcherServlet(ctx));

        servlet.setLoadOnStartup(1);
        servlet.addMapping("/spring/*");
    }
}
