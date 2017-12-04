/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samirhasanov.spring.data.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Hasanov (Asus)
 */
@Configuration
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class DbConfig {
    
    @Autowired
    private Environment environment;
    
    @Bean // get properties from file
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
    @Bean // translate JPA exceptions to Spring Data exceptions (DataAccessException)
    public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
    
    @Bean // transaction manager for JPA
    public PlatformTransactionManager platformTransactionManager() {
        return new JpaTransactionManager(entityManagerFactory());
    }
    
    @Bean // hibernate properties. hibernate will be use as backend for JPA
    public Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.format_sql", true);
        properties.put("hibernate.use_sql_comments", true);
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        
        return properties;
    }
    
    @Bean // JPA entity manager will be used instead of Hibernate SessionFactory
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setPackagesToScan("com.samirhasanov.spring.data.domain");
        bean.setDataSource(dataSource());
        bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        bean.setJpaProperties(hibernateProperties());
        bean.afterPropertiesSet();
        
        return bean.getNativeEntityManagerFactory();
    }
    
    @Bean(destroyMethod = "close") // datasource config. We are using HikariCP connection pool
    public DataSource dataSource() {
        try {
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setDriverClassName(environment.getProperty("database.postgresql.driver"));
            hikariConfig.setJdbcUrl(environment.getProperty("database.postgresql.url"));
            hikariConfig.setUsername(environment.getProperty("database.postgresql.username"));
            hikariConfig.setPassword(environment.getProperty("database.postgresql.password"));
            
            // Connection pool specific configuration
            hikariConfig.setMaximumPoolSize(10);
            hikariConfig.setConnectionTestQuery("select 1");
            hikariConfig.setPoolName("springHikariCP");
            hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
            hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
            hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "2048");
            hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", "true");
            
            HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
            
            return hikariDataSource;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
