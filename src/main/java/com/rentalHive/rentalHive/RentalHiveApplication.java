package com.rentalHive.rentalHive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class RentalHiveApplication {
	public static void main(String[] args) {
		SpringApplication.run(RentalHiveApplication.class, args);
	}
}
