package com.rentalHive.rentalHive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
@EntityScan(basePackages = "com.rentalHive.rentalHive.model.entities")
public class RentalHiveApplication {
	public static void main(String[] args) {
		SpringApplication.run(RentalHiveApplication.class, args);
	}
}
