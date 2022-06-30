package com.example.training_lecture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootApplication(exclude = DataSourceAutoConfiguration)
public class TrainingLectureApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrainingLectureApplication.class, args);
    }

}
