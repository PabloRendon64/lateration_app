package com.lateration_app.example.application.adapter.in;

import com.lateration_app.example.domain.dto.LevelTwoRequestDto;
import com.lateration_app.example.domain.dto.LevelTwoResponseDto;
import org.springframework.http.ResponseEntity;

public interface ILevelTwoApi {

    ResponseEntity<LevelTwoResponseDto> solve(LevelTwoRequestDto request);

}
