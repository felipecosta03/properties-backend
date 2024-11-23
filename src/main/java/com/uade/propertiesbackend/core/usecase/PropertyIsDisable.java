package com.uade.propertiesbackend.core.usecase;

import java.util.function.Predicate;
import lombok.Builder;
import lombok.Value;

@FunctionalInterface
public interface PropertyIsDisable extends Predicate<PropertyIsDisable.Model> {

  @Value
  @Builder
  class Model {

    Long propertyId;
    Long propertyUserId;
    Long userId;
  }
}
