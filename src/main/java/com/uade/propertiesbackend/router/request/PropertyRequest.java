package com.uade.propertiesbackend.router.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uade.propertiesbackend.core.domain.PropertyType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyRequest {

  @NotNull
  @Min(value = 1, message = "Beds must be greater than 0")
  private Integer beds;
  @NotNull
  @Min(value = 1, message = "Bathrooms must be greater than 0")
  private Integer bathrooms;
  @NotBlank
  private String country;
  @NotBlank
  private String city;
  @NotBlank
  private String state;
  @NotNull
  @Min(value = 1, message = "Rooms must be greater than 0")
  private Integer rooms;
  @NotNull
  @Positive
  @JsonProperty("surface_covered")
  private Double surfaceCovered;
  @NotNull
  @Positive
  @JsonProperty("surface_total")
  private Double surfaceTotal;
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
  @JsonProperty("user_id")
  @NotNull
  private Long userId;
  @NotBlank
  private String address;
  @Positive
  private Integer storeys;
  @Positive
  private Double price;
  @PositiveOrZero
  @NotNull
  private Integer garages;
  @NotNull
  private PropertyType type;
  private Boolean active = true;
}
