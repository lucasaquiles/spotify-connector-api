package com.lucasaquiles.spotify.connector.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerResponse implements Serializable {

    @JsonProperty("progress_ms")
    private int progress;

    @JsonProperty("item")
    private ItemResponse response;

    @JsonProperty("artists")
    private ArtistResponse artist;
}
