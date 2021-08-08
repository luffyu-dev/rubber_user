package com.rubber.user.web.starter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.rubber.*")
@MapperScan("com.rubber.user.dao.mapper")
public class RubberUserWebStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(RubberUserWebStarterApplication.class, args);
	}

}
