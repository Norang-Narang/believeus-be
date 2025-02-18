package com.example.believeus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BelieveusApplication {

    public static void main(String[] args) {
        SpringApplication.run(BelieveusApplication.class, args);
    }

}
