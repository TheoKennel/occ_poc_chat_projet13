package com.backend.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.backend.domain", "com.backend.data"})
@ComponentScan(basePackages = "com.backend")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}