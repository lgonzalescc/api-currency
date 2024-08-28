package com.prueba.apicurrency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApiCurrencyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiCurrencyApplication.class, args);
	}

}
