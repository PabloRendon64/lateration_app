package com.lateration_app.example.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class LevelTwoRequestDto {

    private List<SatelliteDto> satellites;

}
