package com.uade.propertiesbackend.core.usecase;

import com.uade.propertiesbackend.core.domain.dto.PropertyDto;
import java.util.function.Function;

public interface RetrieveProperty extends Function<Long, PropertyDto> {

}
