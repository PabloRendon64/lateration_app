package com.lateration_app.example.infrastructure.handler;

import com.lateration_app.example.domain.exception.LevelThreeValidationException;
import com.lateration_app.example.domain.exception.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(value = {ValidationException.class})
    public final ResponseEntity<Void> handleException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = {LevelThreeValidationException.class})
    public final ResponseEntity<String> handleException(LevelThreeValidationException ex) {
        return ResponseEntity.ok(ex.getDescription());
    }

}
