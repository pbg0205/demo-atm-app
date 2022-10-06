package com.cooper.demoatmapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DemoAtmAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoAtmAppApplication.class, args);
    }

}
