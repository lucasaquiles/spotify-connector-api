package com.lucasaquiles.spotify.connector.http;


import com.lucasaquiles.spotify.connector.controller.response.PlayerResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "SpotifyClient",
        url = "${spotify.api.url}",
        decode404 = true
)
public interface SpotifyClient {

    @GetMapping("${spotify.api.player.current}")
    PlayerResponse getCurrent(@RequestHeader("Authorization") String bearerToken);
}
