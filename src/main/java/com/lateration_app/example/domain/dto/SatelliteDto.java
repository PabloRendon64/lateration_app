package com.lateration_app.example.domain.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class SatelliteDto {

    @NonNull
    private String name;
    @NonNull
    private Double distance;
    @NonNull
    private String[] message;

}
