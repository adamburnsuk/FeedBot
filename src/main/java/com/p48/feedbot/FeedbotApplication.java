package com.p48.feedbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FeedbotApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeedbotApplication.class, args);
	}
}
