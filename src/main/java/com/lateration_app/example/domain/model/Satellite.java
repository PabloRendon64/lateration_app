package com.lateration_app.example.domain.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Satellite {

    private String name;
    private Double positionX;
    private Double positionY;
    private Double distance;
    private String[] message;

}
