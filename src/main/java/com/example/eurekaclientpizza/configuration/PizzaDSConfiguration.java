package com.example.eurekaclientpizza.configuration;


import com.example.eurekaclientpizza.model.pizza.Pizza;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.eurekaclientpizza.repository.pizza",
        entityManagerFactoryRef = "pizzaEntityManagerFactory",
        transactionManagerRef= "pizzaTransactionManager")
public class PizzaDSConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.pizza")
    public DataSourceProperties pizzaDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.pizza.configuration")
    public DataSource pizzaDataSource() {
        return pizzaDataSourceProperties().initializeDataSourceBuilder()
                .type(BasicDataSource.class).build();
    }

    @Primary
    @Bean(name = "pizzaEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean pizzaEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        Map<String, String> map = new HashMap<>();
        map.put("hibernate.hbm2ddl.auto", "update");
        return builder
                .dataSource(pizzaDataSource())
                .properties(map)
                .packages(Pizza.class)
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager pizzaTransactionManager(
            final @Qualifier("pizzaEntityManagerFactory") LocalContainerEntityManagerFactoryBean pizzaEntityManagerFactory) {
        return new JpaTransactionManager(pizzaEntityManagerFactory.getObject());
    }

}
