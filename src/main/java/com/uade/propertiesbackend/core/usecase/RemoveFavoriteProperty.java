package com.uade.propertiesbackend.core.usecase;

import java.util.function.Consumer;
import lombok.Builder;
import lombok.Getter;

@FunctionalInterface
public interface RemoveFavoriteProperty extends Consumer<RemoveFavoriteProperty.Model> {

  @Getter
  @Builder
  class Model {
    private Long userId;
    private Long propertyId;
  }
}
