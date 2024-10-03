package com.lateration_app.example.domain.service;

import com.lateration_app.example.domain.dto.LevelTwoRequestDto;
import com.lateration_app.example.domain.dto.SatelliteDto;
import com.lateration_app.example.domain.exception.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(SpringExtension.class)
public class LevelTwoValidationServiceTest {

    private LevelTwoValidationService levelTwoValidationService;


    @BeforeEach
    public void setup() {
        levelTwoValidationService = new LevelTwoValidationService();
    }

    @Test
    void given_three_satellites_full_data_in_request_when_validateAtLeastThreeSatellites_then_success_validation() {
        //when
        LevelTwoRequestDto request = new LevelTwoRequestDto();
        List<SatelliteDto> satellitesRequest = new ArrayList<>();
        satellitesRequest.add(getSatelliteDto("kenobi"));
        satellitesRequest.add(getSatelliteDto("skywalker"));
        satellitesRequest.add(getSatelliteDto("sato"));
        request.setSatellites(satellitesRequest);
        //then
        levelTwoValidationService.validateAtLeastThreeSatellites(request);
    }

    @Test
    void given_two_satellites_full_data_in_request_when_validateAtLeastThreeSatellites_then_fail_validation() {
        LevelTwoRequestDto request = new LevelTwoRequestDto();
        List<SatelliteDto> satellitesRequest = new ArrayList<>();
        satellitesRequest.add(getSatelliteDto("kenobi"));
        satellitesRequest.add(getSatelliteDto("skywalker"));
        request.setSatellites(satellitesRequest);
        assertThrows(ValidationException.class,
                () -> levelTwoValidationService.validateAtLeastThreeSatellites(request));
    }

    @Test
    void given_satellites_null_in_request_when_validateAtLeastThreeSatellites_then_fail_validation() {
        LevelTwoRequestDto request = new LevelTwoRequestDto();
        assertThrows(ValidationException.class,
                () -> levelTwoValidationService.validateAtLeastThreeSatellites(request));
    }

    @Test
    void given_request_null_when_validateAtLeastThreeSatellites_then_fail_validation() {
        assertThrows(ValidationException.class,
                () -> levelTwoValidationService.validateAtLeastThreeSatellites(null));
    }

    private SatelliteDto getSatelliteDto(String name) {
        SatelliteDto satellite = new SatelliteDto();
        satellite.setName(name);
        if("kenobi".equals(name)) {
            satellite.setDistance(100d);
            satellite.setMessage(new String[]{ "este", "", "", "mensaje", "" });
        } else if("skywalker".equals(name)) {
            satellite.setDistance(115.5d);
            satellite.setMessage(new String[]{ "", "es", "", "", "secreto" });
        } else {
            satellite.setDistance(142.7);
            satellite.setMessage(new String[]{ "este", "", "un", "", "" });
        }
        return satellite;
    }

}
