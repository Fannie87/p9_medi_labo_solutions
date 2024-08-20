package com.openclassrooms.medi_labo.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableConfigurationProperties(UriConfiguration.class)
@RestController
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder, UriConfiguration uriConfiguration) {
		return builder.routes()
			.route(p -> p
					.path("/api-1-patient/**")
					.uri(uriConfiguration.getHttpApi1()))
			.route(p -> p
					.path("/api-2-note/**")
					.uri(uriConfiguration.getHttpApi2()))
			.build();
	}

	@RequestMapping("/fallback")
	public Mono<String> fallback() {
		return Mono.just("fallback");
	}
}

	@ConfigurationProperties
	class UriConfiguration {
		
		private String httpApi1 = "http://localhost:8081/api-1-patient";
		private String httpApi2 = "http://localhost:8082/api-2-note";


		public String getHttpApi1() {
			return httpApi1;
		}

		public void setHttpApi1(String httpApi1) {
			this.httpApi1 = httpApi1;
		}

		public String getHttpApi2() {
			return httpApi2;
		}

		public void setHttpApi2(String httpApi2) {
			this.httpApi2 = httpApi2;
		}
		
	}
