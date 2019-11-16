package com.lucasaquiles.spotify.connector.controller;

import com.lucasaquiles.spotify.connector.http.AuthorizeSpotifyClient;
import com.lucasaquiles.spotify.connector.http.SpotifyClient;
import feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Controller("/")
public class IndexController {

    @Value("${spotify.api.clientId}")
    private String clientId;

    @Value("${spotify.api.responseType}")
    private String responseType;

    @Value("${spotify.api.redirectURI}")
    private String redirectURI;

    @GetMapping
    ResponseEntity authWithCode(@RequestParam String code) {

        return new ResponseEntity(code, HttpStatus.OK);
    }

}
