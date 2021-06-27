package com.jeferson.scheduler.adapter.api.handler;

import com.jeferson.scheduler.core.domain.exception.NotificationNotFoundException;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NotificationExceptionHandler {

  @ExceptionHandler(NotificationNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleMessageNotFound(
      NotificationNotFoundException exception) {
    return ResponseEntity.status(404)
        .body(ErrorResponse.builder().description(exception.getLocalizedMessage()).build());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<ErrorResponse>> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    List<ErrorResponse> apiErrorList = new ArrayList<>();
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
