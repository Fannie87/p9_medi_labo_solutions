package com.openclassrooms.medi_labo.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
	
	@Value("${url.httpApi1}")
	private String httpApi1;
	
	@Value("${url.httpApi2}")
	private String httpApi2;
	
	@Value("${url.httpApi3}")
	private String httpApi3;


	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
			.route(p -> p
					.path("/api-1-patient/**")
					.uri(httpApi1))
			.route(p -> p
					.path("/api-2-note/**")
					.uri(httpApi2))
			.route(p -> p
					.path("/api-3-risque/**")
					.uri(httpApi3))
			.build();
	}

	@RequestMapping("/fallback")
	public Mono<String> fallback() {
		return Mono.just("fallback");
	}
}

