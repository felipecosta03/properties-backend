package com.uade.propertiesbackend.core.usecase;

import java.util.function.Consumer;
import lombok.Builder;
import lombok.Getter;

@FunctionalInterface
public interface AddFavoriteProperty extends Consumer<AddFavoriteProperty.Model> {

  @Getter
  @Builder
  class Model {

    private Long userId;
    private Long propertyId;
  }
}
