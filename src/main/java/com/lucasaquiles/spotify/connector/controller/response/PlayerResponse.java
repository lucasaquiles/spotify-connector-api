package com.lucasaquiles.spotify.connector.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerResponse implements Serializable {

    private ItemResponse response;
    private ArtistResponse artist;
}
