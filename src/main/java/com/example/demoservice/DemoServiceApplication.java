package com.example.demoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@RestController
public class DemoServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoServiceApplication.class, args);
  }

  @RequestMapping(value = "/hello", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String hello(String name) {
    return "你好" + name;
  }

}
