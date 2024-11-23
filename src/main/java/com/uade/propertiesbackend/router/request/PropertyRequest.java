package com.uade.propertiesbackend.router.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uade.propertiesbackend.core.domain.PropertyType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyRequest {

  @NotNull
  @Positive
  @JsonProperty("surface_total")
  private Double surfaceTotal;
  @NotNull
  @Min(value = 1, message = "Bathrooms must be greater than 0")
  private Integer bathrooms;
  @NotNull
  @Min(value = 1, message = "Beds must be greater than 0")
  private Integer beds;
  @NotBlank
  private String district;
  @NotNull
  @Min(value = 1, message = "Rooms must be greater than 0")
  private Integer rooms;
  @NotNull
  @Positive
  @JsonProperty("surface_covered")
  private Double surfaceCovered;

  @NotBlank
  private String title;
  @NotBlank
  private String description;
  @NotNull
  private Double latitude;
  @NotNull
  private Double longitude;
  @NotNull
  private List<String> images;
  @NotBlank
  private String address;
  @NotBlank
  private String zipcode;
  @Positive
  private Double price;
  @NotNull
  private PropertyType type;
  private Boolean active = true;
}
