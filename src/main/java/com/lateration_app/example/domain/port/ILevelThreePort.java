package com.lateration_app.example.domain.port;

import com.lateration_app.example.domain.dto.LevelTwoResponseDto;
import com.lateration_app.example.domain.dto.SatelliteDto;

public interface ILevelThreePort {

    void saveInfo(String satelliteName, SatelliteDto satelliteRequest);

    LevelTwoResponseDto getInfo();

}
