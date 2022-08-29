package com.example.eurekaclientpizza;



import brave.sampler.Sampler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class EurekaClientPizzaApplication {

    @Bean
    public Sampler alwaysSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }
    public static void main(String[] args) {
        SpringApplication.run(EurekaClientPizzaApplication.class, args);
    }

}
