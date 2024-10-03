package com.lateration_app.example.domain.service;

import com.lateration_app.example.domain.exception.LevelThreeValidationException;
import com.lateration_app.example.domain.model.Satellite;
import com.lateration_app.example.domain.port.ISatelliteDBPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LevelThreeValidationService {

    private final ISatelliteDBPort satelliteDBPort;

    @Before("execution(* com.lateration_app.example.domain.service.LevelThreeService.getInfo())")
    public void validateAtLeastThreeStoredMessages() {
        List<Satellite> satellites = satelliteDBPort.findAll().stream()
                .filter(satellite -> satellite.getMessage() != null &&
                        !Arrays.stream(satellite.getMessage()).toList().isEmpty()).toList();
        if(satellites.size() < 3) {
            log.info("there is not enough information");
            throw new LevelThreeValidationException("there is not enough information");
        }
    }

}
