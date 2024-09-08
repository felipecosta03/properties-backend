package com.uade.propertiesbackend.core.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RentDto {

  private Long id;
  private PropertyDto property;
  private String status;
}
