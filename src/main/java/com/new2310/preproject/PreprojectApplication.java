package com.new2310.preproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PreprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(PreprojectApplication.class, args);
    }

}
