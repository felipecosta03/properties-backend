package com.uade.propertiesbackend.router.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uade.propertiesbackend.core.domain.Property;
import com.uade.propertiesbackend.core.domain.RentProcessStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RentProcessNews {

  @JsonProperty("rent_process_id")
  private Long rentProcessId;
  private RentProcessStatus status;
  private Long tenantId;
  private Long landLordId;
  private Long propertyId;
  private Property property;

  private LocalDateTime dateCreated;
}
