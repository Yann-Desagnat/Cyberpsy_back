package com.cyberpsy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.cyberpsy")
//@EnableJpaRepositories(basePackages = "com.cyberpsy.interfaces")

public class CyberpsyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CyberpsyApiApplication.class, args);
	}
}
