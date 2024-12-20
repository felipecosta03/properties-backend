package com.uade.propertiesbackend.core.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uade.propertiesbackend.core.domain.PropertyType;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PropertyDetailsDTO {

  private Long id;
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
  @JsonProperty("owner_id")
  private Long userId;
  private String address;
  private String zipcode;
  private Double price;
  private PropertyType type;
  @JsonProperty("created_at")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createdAt;
  private boolean active;
  @Builder.Default
  private boolean favorite = false;
  private boolean disable;
}
