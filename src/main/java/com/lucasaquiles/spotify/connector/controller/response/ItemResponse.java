package com.lucasaquiles.spotify.connector.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse implements Serializable {

    private String name;
    private String href;
}
