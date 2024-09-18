package com.uade.propertiesbackend.core.usecase;

import com.uade.propertiesbackend.core.domain.PropertyType;
import com.uade.propertiesbackend.core.domain.dto.PropertyDto;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import lombok.Builder;
import lombok.Data;

@FunctionalInterface
public interface PartialUpdateProperty extends Function<PartialUpdateProperty.Model, PropertyDto> {
  @Data
  @Builder
  class Model {
    private Long id;
    private Optional<Integer> beds;
    private Optional<Integer> bathrooms;
    private Optional<String> district;
    private Optional<Integer> rooms;
    private Optional<Double> surfaceCovered;
    private Optional<Double> surfaceTotal;
    private Optional<String> title;
    private Optional<String> description;
    private Optional<Double> latitude;
    private Optional<Double> longitude;
    private Optional<List<String>> images;
    private Long userId;
    private Optional<String> address;
    private Optional<String> zipcode;
    private Optional<Double> price;
    private Optional<PropertyType> type;
    private Boolean active;
  }
}
