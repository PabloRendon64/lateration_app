package com.lateration_app.example.domain.service;

import com.lateration_app.example.domain.dto.LevelTwoRequestDto;
import com.lateration_app.example.domain.dto.LevelTwoResponseDto;
import com.lateration_app.example.domain.dto.PositionDto;
import com.lateration_app.example.domain.dto.SatelliteDto;
import com.lateration_app.example.domain.model.Satellite;
import com.lateration_app.example.domain.port.ILevelTwoPort;
import com.lateration_app.example.domain.port.ISatelliteDBPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
public class LevelTwoServiceTest {

    private ILevelTwoPort levelTwoPort;

    @MockBean
    private ISatelliteDBPort satelliteDBPort;

    @BeforeEach
    public void setup() {
        levelTwoPort = new LevelTwoService(satelliteDBPort);
    }


    @Test
    void given_request_when_solve_then_return_info() {
        //given
        LevelTwoRequestDto request = new LevelTwoRequestDto();
        List<SatelliteDto> satellitesRequest = new ArrayList<>();
        satellitesRequest.add(getSatelliteDto("kenobi"));
        satellitesRequest.add(getSatelliteDto("skywalker"));
        satellitesRequest.add(getSatelliteDto("sato"));
        request.setSatellites(satellitesRequest);
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
        Assertions.assertThat(levelTwoPort.solve(request)).usingRecursiveComparison().isEqualTo(responseDto);
    }

    private Satellite getSatellite(String name) {
        Satellite satellite = new Satellite();
        satellite.setName(name);
        if("kenobi".equals(name)) {
            satellite.setPositionX(-500d);
            satellite.setPositionY(-200d);
        } else if("skywalker".equals(name)) {
            satellite.setPositionX(100d);
            satellite.setPositionY(-100d);
        } else if("sato".equals(name)) {
            satellite.setPositionX(500d);
            satellite.setPositionY(100d);
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
