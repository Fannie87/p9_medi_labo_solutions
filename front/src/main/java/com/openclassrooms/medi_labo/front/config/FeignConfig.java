package com.openclassrooms.medi_labo.front.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.auth.BasicAuthRequestInterceptor;
import okhttp3.OkHttpClient;

@Configuration
public class FeignConfig {

  @Bean
  public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
    return new BasicAuthRequestInterceptor("user", "password");
  }

  @Bean
  public OkHttpClient client() {
    return new OkHttpClient();
  }
}
