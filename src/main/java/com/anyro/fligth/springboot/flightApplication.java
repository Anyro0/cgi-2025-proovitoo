package com.anyro.fligth.springboot;
//package com.rimmelasghar.boilerplate.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableAspectJAutoProxy
public class flightApplication {
    public static void main(String[] args) {
        SpringApplication.run(flightApplication.class, args);
    }
}
