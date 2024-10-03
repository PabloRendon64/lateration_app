package com.lateration_app.example.domain.service;

import com.lateration_app.example.domain.dto.LevelTwoRequestDto;
import com.lateration_app.example.domain.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LevelTwoValidationService {

    @Before("execution(* com.lateration_app.example.domain.service.LevelTwoService.solve(..)) && " +
            "args(request)")
    public void validateAtLeastThreeSatellites(LevelTwoRequestDto request) {
        if(request == null || request.getSatellites() == null || request.getSatellites().size() < 3) {
            log.info("validation not pass");
            throw new ValidationException();
        }
    }

}
