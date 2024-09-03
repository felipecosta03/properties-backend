package com.uade.propertiesbackend.core.usecase;

import com.uade.propertiesbackend.core.domain.PropertyType;
import com.uade.propertiesbackend.core.domain.dto.PropertyDto;
import java.util.List;
import java.util.function.Function;
import lombok.Builder;
import lombok.Data;

@FunctionalInterface
public interface UpdateProperty extends Function<UpdateProperty.Model, PropertyDto> {

  @Data
  @Builder
  class Model {
    private Long id;
    private Integer beds;
    private Integer bathrooms;
    private String city;
    private String district;
    private Integer rooms;
    private Double surfaceCovered;
    private Double surfaceTotal;
    private String title;
    private String description;
    private Double latitude;
    private Double longitude;
    private List<String> images;
    private Long userId;
    private String address;
    private Double price;
    private PropertyType type;
    private Boolean active;
  }
}
