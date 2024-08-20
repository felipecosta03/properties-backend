package com.uade.propertiesbackend.router;

import com.uade.propertiesbackend.core.exception.BadRequestException;
import com.uade.propertiesbackend.core.exception.NotFoundException;
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

    ApiError apiError = ApiError.builder().message(e.getMessage())
        .status(HttpStatus.BAD_REQUEST.value()).error(e.getClass().getSimpleName()).build();
    log.error(e.getMessage(), e.getCause());
    return ResponseEntity.status(apiError.getStatus()).body(apiError);
  }

  @ExceptionHandler(value = {NotFoundException.class})
  public ResponseEntity<ApiError> handleNotFoundException(Exception e) {
    ApiError apiError = ApiError.builder().message(e.getMessage())
        .status(HttpStatus.NOT_FOUND.value()).error(e.getClass().getSimpleName()).build();
    log.error(e.getMessage(), e.getCause());
    return ResponseEntity.status(apiError.getStatus()).body(apiError);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiError> handleUnknownException(Exception e) {

    ApiError apiError = ApiError.builder().message(e.getMessage())
        .status(HttpStatus.FAILED_DEPENDENCY.value()).error(e.getClass().getSimpleName()).build();
    log.error(e.getMessage(), e);
    return ResponseEntity.status(apiError.getStatus()).body(apiError);
  }


}
