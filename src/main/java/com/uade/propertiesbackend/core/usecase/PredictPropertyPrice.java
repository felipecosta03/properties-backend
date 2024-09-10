package com.uade.propertiesbackend.core.usecase;

import com.uade.propertiesbackend.core.domain.dto.PricePredictDto;
import java.util.function.Function;

public interface PredictPropertyPrice extends Function<Long, PricePredictDto> {

}
