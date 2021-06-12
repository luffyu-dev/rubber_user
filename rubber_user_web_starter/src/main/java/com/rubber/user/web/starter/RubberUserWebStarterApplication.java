package com.rubber.user.web.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.rubber.*")
public class RubberUserWebStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(RubberUserWebStarterApplication.class, args);
	}

}
