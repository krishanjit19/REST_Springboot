package com.example.q3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(
		basePackages = {"com.example.q3"}
)
@EnableJpaRepositories("com.example.q3.repository")
public class Q3Application {

	public static void main(String[] args) {
		SpringApplication.run(Q3Application.class, args);
	}

}
