package com.lucasaquiles.spotify.connector.http;


import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

import java.util.Arrays;

public class SpotifyConfiguration {

    @Value("${spotify.api.secret}")
    private String secret;

    @Value("${spotify.api.clientId}")
    private String clientId;

    @Value("${spotify.api.authorize}")
    private String authUrl;

    @Value("${spotify.api.scope}")
    private String scope;

    @Bean
    RequestInterceptor requestInterceptor() {

        return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), resource());
    }

    OAuth2ProtectedResourceDetails resource() {
        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();

        details.setClientSecret(secret);
        details.setClientId(clientId);
        details.setAccessTokenUri(authUrl);
        details.setScope(Arrays.asList(scope));
        details.setGrantType("client_credentials");

        return details;
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
