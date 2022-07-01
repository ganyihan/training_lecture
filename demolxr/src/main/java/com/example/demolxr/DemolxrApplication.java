package com.example.demolxr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude= DataSourceAutoConfiguration.class)
public class DemolxrApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemolxrApplication.class, args);
    }

}
