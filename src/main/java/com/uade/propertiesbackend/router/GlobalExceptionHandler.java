package com.uade.propertiesbackend.router;

import com.uade.propertiesbackend.core.exception.BadRequestException;
import com.uade.propertiesbackend.router.exception.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(value = {BadRequestException.class})
  public ResponseEntity<ApiError> handleBadRequestException(Exception e) {

    ApiError apiError =
        ApiError.builder().message(e.getMessage()).status(HttpStatus.BAD_REQUEST.value()).error(e.getClass().getSimpleName()).build();
    return ResponseEntity.status(apiError.getStatus()).body(apiError);
  }
}
