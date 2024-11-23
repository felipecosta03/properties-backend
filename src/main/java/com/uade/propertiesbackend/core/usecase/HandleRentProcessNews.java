package com.uade.propertiesbackend.core.usecase;

import com.uade.propertiesbackend.core.domain.RentProcessStatus;
import java.util.function.Consumer;
import lombok.Builder;
import lombok.Getter;

@FunctionalInterface
public interface HandleRentProcessNews extends Consumer<HandleRentProcessNews.Model> {

  @Builder
  @Getter
  class Model {

    private Long rentProcessId;
    private RentProcessStatus status;
  }
}
