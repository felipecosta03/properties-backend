package com.uade.propertiesbackend.router.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uade.propertiesbackend.core.domain.PropertyType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartialPropertyRequest {
  private Integer beds;
  private Integer bathrooms;
  private String district;
  private Integer rooms;
  @JsonProperty("surface_covered")
  private Double surfaceCovered;
  @JsonProperty("surface_total")
  private Double surfaceTotal;
  private String title;
  private String description;
  private Double latitude;
  private Double longitude;
  private List<String> images;
  private String address;
  private String zipcode;
  private Double price;
  private PropertyType type;
  private Boolean active;

}
