package com.uade.propertiesbackend.core.usecase;

import com.uade.propertiesbackend.core.domain.dto.PropertyDto;
import java.util.function.Function;
import lombok.Builder;
import lombok.Getter;

@FunctionalInterface
public interface CreateProperty extends Function<CreateProperty.Model, PropertyDto> {

  @Getter
  @Builder
  class Model{

  }
}
