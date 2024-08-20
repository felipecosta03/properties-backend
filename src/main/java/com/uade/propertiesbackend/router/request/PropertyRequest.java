package com.uade.propertiesbackend.router.request;

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
  private Double surface;
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
  @NotNull
  private Long userId;
  @NotBlank
  private String street;
  @Positive
  private Integer streetNumber;
  @Positive
  private Integer storeys;
  @Positive
  private Double price;
}
