package com.example.eurekaclientpizza.configuration;


import com.example.eurekaclientpizza.model.pizzaholder.PizzaHolder;
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
@EnableJpaRepositories(basePackages = "com.example.eurekaclientpizza.repository.pizzaholder",
        entityManagerFactoryRef = "pizzaHolderEntityManagerFactory",
        transactionManagerRef= "pizzaHolderTransactionManager")
public class PizzaHolderDSConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.holder")
    public DataSourceProperties pizzaHolderDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.holder.configuration")
    public DataSource pizzaHolderDataSource() {
        return pizzaHolderDataSourceProperties().initializeDataSourceBuilder()
                .type(BasicDataSource.class).build();
    }

    @Bean(name = "pizzaHolderEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean pizzaHolderEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        Map<String, String> map = new HashMap<>();
        map.put("hibernate.hbm2ddl.auto", "update");
        return builder
                .dataSource(pizzaHolderDataSource())
                .properties(map)
                .packages(PizzaHolder.class)
                .build();
    }

    @Bean
    public PlatformTransactionManager pizzaHolderTransactionManager(
            final @Qualifier("pizzaHolderEntityManagerFactory") LocalContainerEntityManagerFactoryBean pizzaHolderEntityManagerFactory) {
        return new JpaTransactionManager(pizzaHolderEntityManagerFactory.getObject());
    }
}
