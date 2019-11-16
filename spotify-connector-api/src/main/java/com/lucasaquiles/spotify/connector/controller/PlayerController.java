package com.lucasaquiles.spotify.connector.controller;

import com.lucasaquiles.spotify.connector.controller.response.PlayerResponse;
import com.lucasaquiles.spotify.connector.http.SpotifyClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PlayerController {

    @Autowired
    private SpotifyClient client;


    @GetMapping("/player/current")
    public ResponseEntity<PlayerResponse> currentPlayer(@RequestParam String token){
        log.info("currentPlayer, request ... {}", token);

        PlayerResponse current = client.getCurrent("Bearer "+ token);
        return new ResponseEntity<>(current, HttpStatus.OK);
    }
}
