package com.epam.task.spring_two;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
public class SpringTwoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTwoApplication.class, args);
	}

}
