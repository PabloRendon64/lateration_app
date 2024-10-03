package com.lateration_app.example.domain.service;

import com.lateration_app.example.domain.dto.LevelTwoRequestDto;
import com.lateration_app.example.domain.dto.LevelTwoResponseDto;
import com.lateration_app.example.domain.dto.PositionDto;
import com.lateration_app.example.domain.dto.SatelliteDto;
import com.lateration_app.example.domain.model.Satellite;
import com.lateration_app.example.domain.port.ILevelTwoPort;
import com.lateration_app.example.domain.port.ISatelliteDBPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LevelTwoService implements ILevelTwoPort {

    private final ISatelliteDBPort satelliteDBPort;

    @Override
    public LevelTwoResponseDto solve(LevelTwoRequestDto request) {
        List<Satellite> availableSatellites = satelliteDBPort.findAll();
        availableSatellites
                .forEach(satellite -> {
                    Optional<SatelliteDto> satelliteDtoOptional = request.getSatellites().stream()
                            .filter(satelliteDto -> satellite.getName().equals(satelliteDto.getName()))
                            .findFirst();
                    if(satelliteDtoOptional.isPresent()) {
                        SatelliteDto satelliteDtoAvailable = satelliteDtoOptional.get();
                        satellite.setDistance(satelliteDtoAvailable.getDistance());
                        satellite.setMessage(satelliteDtoAvailable.getMessage());
                    }
                });
        List<Satellite> usedSatellites = availableSatellites.stream()
                .filter(satellite -> satellite.getDistance() != null).toList();
        return LevelTwoResponseDto.builder()
                .position(new PositionDto(LevelOneService.getLocation(usedSatellites)))
                .message(LevelOneService.getMessage(usedSatellites))
                .build();
    }

}
