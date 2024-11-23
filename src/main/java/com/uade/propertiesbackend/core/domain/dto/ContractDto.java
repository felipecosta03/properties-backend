package com.uade.propertiesbackend.core.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ContractDto {

  private String contractId;
  private Long rentId;

}
