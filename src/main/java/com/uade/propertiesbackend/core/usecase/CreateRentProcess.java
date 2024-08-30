package com.uade.propertiesbackend.core.usecase;

import java.util.function.Consumer;
import lombok.Builder;
import lombok.Getter;

public interface CreateRentProcess extends Consumer<CreateRentProcess.Model> {

  @Builder
  @Getter
  class Model {

    private Long userId;
    private Long propertyId;
  }
}
