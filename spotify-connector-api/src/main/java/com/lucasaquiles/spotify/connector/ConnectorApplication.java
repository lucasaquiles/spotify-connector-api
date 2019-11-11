package com.lucasaquiles.spotify.connector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@EnableOAuth2Client
@EnableFeignClients
@SpringBootApplication
public class ConnectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConnectorApplication.class, args);
	}

}
