package com.lucasaquiles.spotify.connector.http;

import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;

@Configuration
public class Config {


    @Bean
    RequestInterceptor oauth2FeignRequestInterceptor(OAuth2ClientContext oAuth2ClientContext) {
        return new SpotifyConfiguration(oAuth2ClientContext);
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;
    }

}
