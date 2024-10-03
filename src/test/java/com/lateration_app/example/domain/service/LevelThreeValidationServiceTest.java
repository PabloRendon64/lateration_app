package com.lateration_app.example.domain.service;

import com.lateration_app.example.domain.exception.LevelThreeValidationException;
import com.lateration_app.example.domain.model.Satellite;
import com.lateration_app.example.domain.port.ISatelliteDBPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
public class LevelThreeValidationServiceTest {

    private LevelThreeValidationService levelThreeValidationService;

    @MockBean
    private ISatelliteDBPort satelliteDBPort;

    @BeforeEach
    public void setup() {
        levelThreeValidationService = new LevelThreeValidationService(satelliteDBPort);
    }

    @Test
    void given_three_satellites_full_data_in_database_when_validateAtLeastThreeStoredMessages_then_success_validation() {
        //when
        List<Satellite> satellites = new ArrayList<>();
        satellites.add(getSatellite("kenobi"));
        satellites.add(getSatellite("skywalker"));
        satellites.add(getSatellite("sato"));
        satellites.add(getSatellite("dummy"));
        when(satelliteDBPort.findAll())
                .thenReturn(satellites);
        //then
        levelThreeValidationService.validateAtLeastThreeStoredMessages();
    }

    @Test
    void given_two_satellites_full_data_in_database_when_validateAtLeastThreeStoredMessages_then_fail_validation() {
        List<Satellite> satellites = new ArrayList<>();
        Satellite satellite1 = getSatellite("kenobi");
        satellite1.setDistance(null);
        satellite1.setMessage(new String[]{});
        satellites.add(satellite1);
        satellites.add(getSatellite("skywalker"));
        satellites.add(getSatellite("sato"));
        satellites.add(getSatellite("dummy"));
        when(satelliteDBPort.findAll())
                .thenReturn(satellites);
        Assertions.assertEquals("no hay suficiente información", assertThrows(LevelThreeValidationException.class,
                () -> levelThreeValidationService.validateAtLeastThreeStoredMessages()).getDescription());
    }

    private Satellite getSatellite(String name) {
        Satellite satellite = new Satellite();
        satellite.setName(name);
        if("kenobi".equals(name)) {
            satellite.setPositionX(-500d);
            satellite.setPositionY(-200d);
            satellite.setDistance(100d);
            satellite.setMessage(new String[]{ "este", "", "", "mensaje", "" });
        } else if("skywalker".equals(name)) {
            satellite.setPositionX(100d);
            satellite.setPositionY(-100d);
            satellite.setDistance(115.5d);
            satellite.setMessage(new String[]{ "", "es", "", "", "secreto" });
        } else if("sato".equals(name)) {
            satellite.setPositionX(500d);
            satellite.setPositionY(100d);
            satellite.setDistance(142.7);
            satellite.setMessage(new String[]{ "este", "", "un", "", "" });
        } else {
            satellite.setPositionX(1000d);
            satellite.setPositionY(1000d);
        }
        return satellite;
    }

}
