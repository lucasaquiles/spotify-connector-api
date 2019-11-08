package com.lucasaquiles.spotify.connector.http;


import com.lucasaquiles.spotify.connector.controller.response.PlayerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "spotify-client",
        url = "${spotify.api.url}"
)
public interface SpotifyClient {

    @GetMapping(value = "${spotify.api.player.current}")
    PlayerResponse getCurrent(@RequestHeader("Authorization") String token);
}
