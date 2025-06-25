package com.example.pharmacy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point of the Pharmacy Spring Boot application.
 * This class bootstraps the application using Spring Boot's auto-configuration.
 */
@SpringBootApplication
public class PharmacyApplication {

    /**
     * Main method that launches the Spring Boot application.
     *
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(PharmacyApplication.class, args);
    }

}
