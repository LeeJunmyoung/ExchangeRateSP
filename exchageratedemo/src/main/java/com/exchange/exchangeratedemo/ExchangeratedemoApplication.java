package com.exchange.exchangeratedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ExchangeratedemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExchangeratedemoApplication.class, args);
	}

}

