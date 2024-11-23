package com.uade.propertiesbackend.core.usecase;

import com.uade.propertiesbackend.core.domain.RentStatus;
import java.util.function.Consumer;
import lombok.Builder;
import lombok.Getter;

@FunctionalInterface
public interface HandleRentNews extends Consumer<HandleRentNews.Model> {

  @Builder
  @Getter
  class Model {

    private Long rentProcessId;
    private Long rentId;
    private RentStatus status;
  }
}
