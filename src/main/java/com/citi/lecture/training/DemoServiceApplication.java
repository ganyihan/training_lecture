package com.citi.lecture.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import sun.security.krb5.internal.PAForUserEnc;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@RestController
public class DemoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoServiceApplication.class, args);
	}

	@GetMapping("/hello")
	public String helloDemo(){
		return "hello my demo project";
	}

}
