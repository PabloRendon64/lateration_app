package com.lateration_app.example.application.adapter.in;

import com.lateration_app.example.domain.dto.LevelTwoResponseDto;
import com.lateration_app.example.domain.dto.SatelliteDto;
import org.springframework.http.ResponseEntity;

public interface ILevelThreeApi {

    ResponseEntity<Void> saveInfo(String satelliteName, SatelliteDto request);

    ResponseEntity<LevelTwoResponseDto> getInfo();


}
