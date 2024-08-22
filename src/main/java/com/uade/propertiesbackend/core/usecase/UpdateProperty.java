package com.uade.propertiesbackend.core.usecase;

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
    private String country;
    private String city;
    private String state;
    private Integer rooms;
    private Double surface;
    private String title;
    private String description;
    private Double latitude;
    private Double longitude;
    private List<String> images;
    private Long userId;
    private String street;
    private Integer streetNumber;
    private Integer storeys;
    private Double price;
  }
}
