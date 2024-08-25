package com.openclassrooms.medi_labo.front.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.auth.BasicAuthRequestInterceptor;
import okhttp3.OkHttpClient;

@Configuration
public class FeignConfig {

	@Value("${security.user}")
	private String user;

	@Value("${security.password}")
	private String password;
	
//	Permet de demander un user et password à chaque requête sans le réécrire à chaque fois
	@Bean
	public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
		return new BasicAuthRequestInterceptor(user, password);
	}

	@Bean
	public OkHttpClient client() {
		return new OkHttpClient();
	}
}
