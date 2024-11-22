package com.uade.propertiesbackend.core.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class PaymentEvent {

  @JsonProperty("rent_process_id")
  private String rentProcessId;

}
