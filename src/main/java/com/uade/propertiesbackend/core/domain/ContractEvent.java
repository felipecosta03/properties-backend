package com.uade.propertiesbackend.core.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter

public class ContractEvent {

  @JsonProperty("contractId")
  private String contractId;

  @JsonCreator
  public ContractEvent(@JsonProperty("contractId") String contractId) {
    this.contractId = contractId;
  }
}
