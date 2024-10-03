package com.lateration_app.example.domain.port;

import com.lateration_app.example.domain.dto.LevelTwoRequestDto;
import com.lateration_app.example.domain.dto.LevelTwoResponseDto;

public interface ILevelTwoPort {

    LevelTwoResponseDto solve(LevelTwoRequestDto request);

}
