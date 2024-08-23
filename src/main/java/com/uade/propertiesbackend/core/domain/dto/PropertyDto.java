package com.uade.propertiesbackend.core.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uade.propertiesbackend.core.domain.PropertyType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PropertyDto {

  private Long id;
  private Integer beds;
  private Integer bathrooms;
  private String country;
  private String city;
  private String state;
  private Integer rooms;
  private Double surfaceCovered;
  private Double surfaceTotal;
  private String title;
  private String description;
  private Double latitude;
  private Double longitude;
  private List<String> images;
  @JsonProperty("user_id")
  private Long userId;
  private String street;
  @JsonProperty("street_number")
  private Integer streetNumber;
  private Integer storeys;
  private Double price;
  private Integer garages;
  private PropertyType type;
}
