package com.uade.propertiesbackend.core.usecase;

import com.uade.propertiesbackend.core.usecase.RetrieveConsumerStrategy.Model;
import java.util.function.Consumer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public interface RetrieveConsumerStrategy extends Consumer<Model> {

  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  class Model {

    private String detail;
    private String detailEvent;
  }
}
