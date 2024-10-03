package com.lateration_app.example.application.adapter.in;

import com.lateration_app.example.domain.dto.LevelTwoResponseDto;
import com.lateration_app.example.domain.dto.SatelliteDto;
import com.lateration_app.example.domain.port.ILevelThreePort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topsecret_split/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LevelThreeController implements ILevelThreeApi {

    private final ILevelThreePort levelThreePort;

    @Override
    @PostMapping("{satelliteName}")
    public ResponseEntity<Void> saveInfo(@PathVariable("satelliteName") String satelliteName,
                                         @RequestBody SatelliteDto request) {
        levelThreePort.saveInfo(satelliteName, request);
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping
    public ResponseEntity<LevelTwoResponseDto> getInfo() {
        return ResponseEntity.ok(levelThreePort.getInfo());
    }

}
