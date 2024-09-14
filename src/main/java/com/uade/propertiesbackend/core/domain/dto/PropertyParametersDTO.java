package com.uade.propertiesbackend.core.domain.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PropertyParametersDTO {
    Long propertyId;
    Long userId;
}
