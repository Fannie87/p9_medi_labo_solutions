package com.openclassrooms.medi_labo.api_3_risque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Api3RisqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(Api3RisqueApplication.class, args);
	}

}
