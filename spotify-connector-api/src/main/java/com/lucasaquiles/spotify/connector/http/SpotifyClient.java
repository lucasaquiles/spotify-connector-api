package com.lucasaquiles.spotify.connector.http;


import com.lucasaquiles.spotify.connector.controller.response.PlayerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "SpotifyClient",
        url = "${spotify.api.url}",
        decode404 = true,
        configuration = Config.class
)
public interface SpotifyClient {

    @GetMapping("${spotify.api.player.current}")
    PlayerResponse getCurrent();
}
