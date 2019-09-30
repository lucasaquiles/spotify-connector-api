package com.lucasaquiles.spotify.connector.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spotify", ignoreUnknownFields = true)
public class SpotifyConfig {

    private String secretKey;
    private String clientId;
    private String urlBase;
}
