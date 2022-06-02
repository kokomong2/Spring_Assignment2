package com.sparta.assignment3_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Assignment3TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(Assignment3TestApplication.class, args);
    }

}
