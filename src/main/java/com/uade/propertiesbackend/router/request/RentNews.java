package com.uade.propertiesbackend.router.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uade.propertiesbackend.core.domain.RentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentNews {

  @JsonProperty("rent_id")
  private Long rentId;
  private RentStatus status;
}
