package com.jeferson.scheduler.adapter.api.handler;

import com.jeferson.scheduler.core.domain.exception.NotificationNotFoundException;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;

@ControllerAdvice
public class NotificationExceptionHandler {

    @ExceptionHandler(NotificationNotFoundException.class)
    public ResponseEntity handleMessageNotFound(NotificationNotFoundException exception) {
        return ResponseEntity.status(404).body(exception);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationExceptions(MethodArgumentNotValidException ex) {
        var apiErrorList = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            apiErrorList.add(ErrorResponse.builder()
                    .field(fieldName)
                    .description(errorMessage).build());
        });
        return ResponseEntity.badRequest().body(apiErrorList);
    }
}

@Getter
@Builder
class ErrorResponse {
    private String field;
    private String description;
}
