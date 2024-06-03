package com.solji.star;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.solji.star.mapper")
public class AskTheStarsBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AskTheStarsBeApplication.class, args);
	}

}
