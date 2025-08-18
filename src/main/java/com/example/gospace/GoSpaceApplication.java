package com.example.gospace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class GoSpaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoSpaceApplication.class, args);
    }

}
