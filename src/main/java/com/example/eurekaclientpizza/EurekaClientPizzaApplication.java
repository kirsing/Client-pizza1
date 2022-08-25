package com.example.eurekaclientpizza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EurekaClientPizzaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientPizzaApplication.class, args);
    }

}
