package com.lateration_app.example.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LevelThreeValidationException extends ValidationException {

    private final String description;

}
