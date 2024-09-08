package com.uade.propertiesbackend.core.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RentalsDto {

  private List<RentDto> rentals;
  @JsonProperty("rent_processes")
  private List<RentDto> rentProcesses;
}
