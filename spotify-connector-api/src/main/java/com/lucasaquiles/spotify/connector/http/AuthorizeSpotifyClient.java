package com.lucasaquiles.spotify.connector.http;

import feign.Client;
import feign.Feign;
import feign.Logger;
import feign.Response;
import feign.slf4j.Slf4jLogger;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "authorize-spotify-client", url = "${spotify.api.authorize}")
public interface AuthorizeSpotifyClient {


    @GetMapping("/?client_id={clientId}&response_type={responseType}&redirect_uri={redirectURI}")
    Response authorize(@RequestParam("client_id") String clientId,
                       @RequestParam("response_type") String responseType,
                       @RequestParam("redirect_uri") String redirectURI);


    class ConfigAuth {

        @Bean
        Feign.Builder feignBuilder() {
            Client.Default defaultClient = new Client.Default(null, null);
            return Feign.builder()
                    .logger(new Slf4jLogger())
                    .logLevel(Logger.Level.FULL)
                    .client(defaultClient);
        }
    }
}
