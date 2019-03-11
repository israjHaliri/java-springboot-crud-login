package com.haliri.israj.appservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.haliri.israj" })
public class AppService {

	public static void main(String[] args) {
		SpringApplication.run(AppService.class, args);
	}
}
