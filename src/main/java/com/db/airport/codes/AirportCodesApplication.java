package com.db.airport.codes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class AirportCodesApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirportCodesApplication.class, args);
	}

}
