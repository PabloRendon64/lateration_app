package com.lateration_app.example.domain.service;

import com.lateration_app.example.domain.dto.LevelTwoResponseDto;
import com.lateration_app.example.domain.dto.PositionDto;
import com.lateration_app.example.domain.dto.SatelliteDto;
import com.lateration_app.example.domain.model.Satellite;
import com.lateration_app.example.domain.port.ILevelThreePort;
import com.lateration_app.example.domain.port.ISatelliteDBPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LevelThreeService implements ILevelThreePort {

    private final ISatelliteDBPort satelliteDBPort;

    @Override
    public void saveInfo(String satelliteName, SatelliteDto satelliteRequest) {
        Satellite satellite = satelliteDBPort.findByName(satelliteName);
        satellite.setMessage(satelliteRequest.getMessage());
        satellite.setDistance(satelliteRequest.getDistance());
        satelliteDBPort.save(satellite);
    }

    @Override
    public LevelTwoResponseDto getInfo() {
        List<Satellite> satellites = satelliteDBPort.findAll().stream()
                .filter(satellite -> satellite.getDistance() != null)
                .toList();
        return LevelTwoResponseDto.builder()
                .position(new PositionDto(LevelOneService.getLocation(satellites)))
                .message(LevelOneService.getMessage(satellites))
                .build();
    }

}
