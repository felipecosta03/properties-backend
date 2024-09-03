package com.uade.propertiesbackend.core.usecase;

import com.uade.propertiesbackend.core.domain.PropertySortBy;
import com.uade.propertiesbackend.core.domain.PropertyType;
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
    private Optional<Integer> minRooms;
    private Optional<Integer> maxRooms;
    private Optional<Integer> rooms;
    private Optional<Integer> minBeds;
    private Optional<Integer> maxBeds;
    private Optional<Integer> beds;
    private Optional<Integer> minBathrooms;
    private Optional<Integer> maxBathrooms;
    private Optional<Integer> bathrooms;
    private Optional<Double> minSurfaceCovered;
    private Optional<Double> maxSurfaceCovered;
    private Optional<Double> minSurfaceTotal;
    private Optional<Double> maxSurfaceTotal;
    private Optional<Double> minLat;
    private Optional<Double> minLon;
    private Optional<Double> maxLat;
    private Optional<Double> maxLon;
    private Optional<PropertyType> propertyType;
    private Optional<Integer> page;
    private Optional<PropertySortBy> sortBy;
    private Optional<Long> userId;
  }
}
