package com.uade.propertiesbackend.core.usecase;

import com.uade.propertiesbackend.core.domain.Property;
import com.uade.propertiesbackend.core.domain.dto.PropertyDto;
import java.util.function.Function;

@FunctionalInterface
public interface BuildPropertyDto extends Function<Property, PropertyDto> {

}
