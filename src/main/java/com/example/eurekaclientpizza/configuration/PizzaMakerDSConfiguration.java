package com.example.eurekaclientpizza.configuration;

import com.example.eurekaclientpizza.model.pizzamaker.PizzaMaker;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.eurekaclientpizza.repository.pizzamaker",
        entityManagerFactoryRef = "pizzaMakerEntityManagerFactory",
        transactionManagerRef= "pizzaMakerTransactionManager")
public class PizzaMakerDSConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.maker")
    public DataSourceProperties pizzaMakerDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.maker.configuration")
    public DataSource pizzaMakerDataSource() {
        return pizzaMakerDataSourceProperties().initializeDataSourceBuilder()
                .type(BasicDataSource.class).build();
    }

    @Bean(name = "pizzaMakerEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean pizzaMakerEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        Map<String, String> map = new HashMap<>();
        map.put("hibernate.hbm2ddl.auto", "update");
        return builder
                .dataSource(pizzaMakerDataSource())
                .properties(map)
                .packages(PizzaMaker.class)
                .build();
    }

    @Bean
    public PlatformTransactionManager pizzaMakerTransactionManager(
            final @Qualifier("pizzaMakerEntityManagerFactory") LocalContainerEntityManagerFactoryBean pizzaMakerEntityManagerFactory) {
        return new JpaTransactionManager(pizzaMakerEntityManagerFactory.getObject());
    }
}
