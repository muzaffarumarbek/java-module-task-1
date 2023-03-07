package com.epam.javamoduletask1.config;

import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ConditionalOnClass(DataSource.class)
public class H2AutoConfiguration {

    private final Environment environment;

    public H2AutoConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    @ConditionalOnProperty(name = "useh2", havingValue = "local")
    @ConditionalOnMissingBean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:test");
        dataSource.setUsername("h2user");
        dataSource.setPassword("h2pass");

        return dataSource;
    }

    @Bean
    @ConditionalOnBean(name = "dataSource")
    @ConditionalOnMissingBean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan("com.epam.javamoduletask1.config");
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        additionalProperties();
        factoryBean.setJpaProperties(additionalProperties());
        return factoryBean;
    }
//TODO check getProperties file
    @ConditionalOnResource(resources = "classpath:h2.properties")
    @Conditional(HibernateConditionConfig.class)
    final Properties additionalProperties() {
        final Properties hibernateProperties = new Properties();

        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("mysql-hibernate.hbm2ddl.auto"));
        hibernateProperties.setProperty("hibernate.dialect", environment.getProperty("mysql-hibernate.dialect"));
        hibernateProperties.setProperty("hibernate.show_sql", environment.getProperty("mysql-hibernate.show_sql") != null ? environment.getProperty("mysql-hibernate.show_sql") : "false");

        return hibernateProperties;
    }

//    @Bean
//    @ConditionalOnMissingBean(type = "JpaTransactionManager")
//    JpaTransactionManager transactionManager(final EntityManagerFactory entityManagerFactory) {
//        final JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory);
//        return transactionManager;
//    }

}

