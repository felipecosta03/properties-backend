package com.uade.propertiesbackend.util;

public class EnumUtils {

  public static <T extends Enum<T>> T getEnum(Class<T> enumClass, String value) {
    if (enumClass == null) {
      throw new IllegalArgumentException("Enum class can't be null.");
    }
    return Enum.valueOf(enumClass, value.toUpperCase());
  }
}
