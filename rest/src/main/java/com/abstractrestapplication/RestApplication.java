package com.abstractrestapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
// TODO: 25-3-2019 link to every specific directory
@ComponentScan(basePackages = {"com.hateoas", "com.abstractrestapplication"})
@EntityScan(basePackages="com.abstractrestapplication.domain")
public class RestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

}
