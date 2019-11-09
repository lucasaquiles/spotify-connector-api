package com.lucasaquiles.spotify.connector.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtistResponse implements Serializable {

    private String name;
    private String type;
    private String hrfe;
}
