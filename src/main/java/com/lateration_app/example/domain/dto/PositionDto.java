package com.lateration_app.example.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PositionDto {

    private double x;
    private double y;

    public PositionDto(double[] location) {
        this.x = location[0];
        this.y = location[1];
    }

}
