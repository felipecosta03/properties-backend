package com.uade.propertiesbackend.core.usecase;

import java.util.function.Predicate;
import lombok.Builder;
import lombok.Getter;

@FunctionalInterface
public interface PropertyIsFavorite extends Predicate<PropertyIsFavorite.Model> {

  @Getter
  @Builder
  class Model {

    Long propertyId;
    Long userId;
  }
}
