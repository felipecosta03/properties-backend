package com.uade.propertiesbackend.core.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PricePredictResponseDto {

  @JsonProperty("estimated_price")
  private Double estimatedPrice;
}
