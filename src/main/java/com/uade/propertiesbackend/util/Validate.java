package com.uade.propertiesbackend.util;

import static java.util.Objects.isNull;

import com.uade.propertiesbackend.core.exception.BadRequestException;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Validate {

  public static void validateNotNullAndPositive(Number value, String fieldName) {
    validateNotNull(value, fieldName);
    if (value.doubleValue() <= 0) {
      throw new BadRequestException(fieldName + " must be positive");
    }
  }

  public static void validateNotBlank(String value, String fieldName) {
    validateNotNull(value, fieldName);
    if (value.isBlank()) {
      throw new BadRequestException(fieldName + " must not be blank");
    }
  }

  public static void validateNotNull(Object value, String fieldName) {
    if (isNull(value)) {
      throw new BadRequestException(fieldName + " must not be null");
    }
  }

  public static void validateNotEmpty(List<?> value, String fieldName) {
    validateNotNull(value, fieldName);
    if (value.isEmpty()) {
      throw new BadRequestException(fieldName + " must not be empty");
    }
  }
}
