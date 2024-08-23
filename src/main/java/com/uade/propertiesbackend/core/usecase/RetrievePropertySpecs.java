package com.uade.propertiesbackend.core.usecase;

import com.uade.propertiesbackend.core.domain.Property;
import java.util.Optional;
import java.util.function.Function;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

public interface RetrievePropertySpecs extends
    Function<RetrievePropertySpecs.Model, Specification<Property>> {

  @Getter
  @Builder
  class Model {
    private Optional<Double> minPrice;
    private Optional<Double> maxPrice;
  }
}
