package com.uade.propertiesbackend.core.usecase;

import com.uade.propertiesbackend.core.domain.dto.PropertyDto;
import com.uade.propertiesbackend.core.domain.dto.PropertyParametersDTO;

import java.util.function.Function;

public interface RetrieveProperty extends Function<PropertyParametersDTO, PropertyDto> {

    PropertyDto apply(Long propertyId);
}
