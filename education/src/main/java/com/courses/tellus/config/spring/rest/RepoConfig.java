package com.courses.tellus.config.spring.rest;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("com.courses.tellus")
@PropertySource("classpath:properties/db.properties")
@EnableTransactionManagement
public class RepoConfig {

    @Autowired
    private transient Environment env;

    /**
     * Method for setup additional hibernate properties.
     *
     * @return additional properties for hibernate
     */
    @Bean
    public Properties advancedProp() {
        final Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.setProperty("hibernate.hbm2ddl.import_files", env.getProperty("hibernate.hbm2ddl.import_files"));
        properties.setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
        return properties;
    }

    /**
     * Create entity manager factory from data source.
     *
     * @param dataSource datasource config
     * @param advancedProp advanced hibernate properties
     * @return javax.persistence.EntityManagerFactory
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(final DataSource dataSource,
                                                                       final Properties advancedProp) {
        final LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        final JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.setDataSource(dataSource);
        factoryBean.setJpaProperties(advancedProp);
        factoryBean.setPackagesToScan("com.courses.tellus");
        return factoryBean;
    }

    /**
     * This transaction manager is appropriate for applications that use a single JPA EntityManagerFactory for
     * transactional data accesses.
     *
     * @param emf Entity manager
     * @return org.springframework.transaction.PlatformTransactionManager
     */
    @Bean
    public JpaTransactionManager transactionManager(final EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    /**
     * Translates native resource exceptions to Spring's DataAccessException hierarchy.
     *
     * @return HibernateExceptionTranslator
     */
    @Bean
    public HibernateExceptionTranslator exceptionTranslation() {
        return new HibernateExceptionTranslator();
    }

}
