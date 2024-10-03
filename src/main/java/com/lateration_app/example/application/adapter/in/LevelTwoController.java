package com.lateration_app.example.application.adapter.in;

import com.lateration_app.example.domain.dto.LevelTwoRequestDto;
import com.lateration_app.example.domain.dto.LevelTwoResponseDto;
import com.lateration_app.example.domain.port.ILevelTwoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topsecret/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LevelTwoController implements ILevelTwoApi {

    private final ILevelTwoPort levelTwoPort;

    @Override
    @PostMapping
    public ResponseEntity<LevelTwoResponseDto> solve(@RequestBody LevelTwoRequestDto request) {
        return ResponseEntity.ok(levelTwoPort.solve(request));
    }

}
