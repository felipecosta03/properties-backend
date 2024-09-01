package com.uade.propertiesbackend.core.usecase;

import com.uade.propertiesbackend.core.domain.RentProcess;
import java.util.function.Consumer;
import lombok.Builder;
import lombok.Getter;

@FunctionalInterface
public interface CreateRent extends Consumer<CreateRent.Model> {

  @Getter
  @Builder
  class Model {

    private RentProcess rentProcess;
  }
}
