package com.lateration_app.example.domain.service;

import com.lateration_app.example.domain.dto.LevelTwoResponseDto;
import com.lateration_app.example.domain.dto.PositionDto;
import com.lateration_app.example.domain.dto.SatelliteDto;
import com.lateration_app.example.domain.exception.LevelThreeValidationException;
import com.lateration_app.example.domain.model.Satellite;
import com.lateration_app.example.domain.port.ILevelThreePort;
import com.lateration_app.example.domain.port.ISatelliteDBPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
public class LevelThreeServiceTest {

    private ILevelThreePort levelThreePort;

    @MockBean
    private ISatelliteDBPort satelliteDBPort;

    @BeforeEach
    public void setup() {
        levelThreePort = new LevelThreeService(satelliteDBPort);
    }


    @Test
    void given_name_and_satelliteRequest_when_saveInfo_then_return_info() {
        //given
        String satelliteName = "kenobi";
        SatelliteDto satelliteRequest = getSatelliteDto("kenobi");
        //when
        when(satelliteDBPort.findByName(anyString()))
                .thenReturn(getSatellite(satelliteName));
        when(satelliteDBPort.save(any(Satellite.class)))
                .thenReturn(getSatellite(satelliteName));
        //then
        levelThreePort.saveInfo(satelliteName, satelliteRequest);
    }

    @Test
    void when_call_getInfo_then_return_info() {
        //when
        List<Satellite> satellites = new ArrayList<>();
        satellites.add(getSatellite("kenobi"));
        satellites.add(getSatellite("skywalker"));
        satellites.add(getSatellite("sato"));
        satellites.add(getSatellite("dummy"));
        when(satelliteDBPort.findAll())
                .thenReturn(satellites);
        //then
        PositionDto position = new PositionDto(new double[]{ 53.028152754326975d, -33.099649475501515d });
        LevelTwoResponseDto responseDto = LevelTwoResponseDto.builder()
                .position(position)
                .message("este es un mensaje secreto")
                .build();
        assertThat(levelThreePort.getInfo()).usingRecursiveComparison().isEqualTo(responseDto);
    }

    @Test
    void when_info_is_incomplete_then_return_LevelThreeValidationException() {
        List<Satellite> satellites = new ArrayList<>();
        Satellite satellite1 = getSatellite("kenobi");
        satellite1.setDistance(null);
        satellite1.setMessage(null);
        satellites.add(satellite1);
        satellites.add(getSatellite("skywalker"));
        satellites.add(getSatellite("sato"));
        satellites.add(getSatellite("dummy"));
        when(satelliteDBPort.findAll())
                .thenReturn(satellites);
        Assertions.assertEquals("no hay suficiente información", assertThrows(LevelThreeValidationException.class,
                () -> levelThreePort.getInfo()).getDescription());
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
