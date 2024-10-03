package com.lateration_app.example.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LevelTwoResponseDto {

    private final PositionDto position;
    private final String message;

}
