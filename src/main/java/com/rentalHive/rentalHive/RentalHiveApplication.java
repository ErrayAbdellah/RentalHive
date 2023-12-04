package com.rentalHive.rentalHive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"com.rentalHive.rentalHive.service.implementations", "com.rentalHive.rentalHive.service", "com.rentalHive.rentalHive.controller"})
public class RentalHiveApplication {
	public static void main(String[] args) {
		SpringApplication.run(RentalHiveApplication.class, args);
	}
}
