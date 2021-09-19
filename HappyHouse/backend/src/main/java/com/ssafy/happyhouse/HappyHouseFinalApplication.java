package com.ssafy.happyhouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class HappyHouseFinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(HappyHouseFinalApplication.class, args);
	}

}
