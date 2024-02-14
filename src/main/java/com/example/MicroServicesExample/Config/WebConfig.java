package com.example.MicroServicesExample.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebConfig {

	@Bean
	public WebClient webClient() {
		WebClient webClient = WebClient.builder().baseUrl("http://localhost:8081/employees")
				.defaultCookie("cookie-name", "cookie-value")
				.defaultHeader(org.springframework.http.HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();

		return webClient;
	}

}
