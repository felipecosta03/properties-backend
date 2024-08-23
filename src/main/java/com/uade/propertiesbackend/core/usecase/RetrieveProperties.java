package com.uade.propertiesbackend.core.usecase;

import com.uade.propertiesbackend.core.domain.dto.PropertyDto;
import java.util.Optional;
import java.util.function.Function;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

public interface RetrieveProperties extends Function<RetrieveProperties.Model, Page<PropertyDto>> {

  @Builder
  @Getter
  class Model {
    private Optional<Double> minPrice;
    private Optional<Double> maxPrice;
    private Optional<Integer> page;
  }
}
