package com.uade.propertiesbackend.core.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@Getter
public class ContractDto {

  private String contractId;
  private Long rentId;

  @JsonCreator
  public ContractDto(@JsonProperty("contractId") String contractId,@JsonProperty("rentId") Long rentId) {
    this.contractId = contractId;
    this.rentId = rentId;
  }
}
