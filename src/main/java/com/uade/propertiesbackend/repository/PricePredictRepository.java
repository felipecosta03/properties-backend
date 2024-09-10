package com.uade.propertiesbackend.repository;

import com.uade.propertiesbackend.core.domain.dto.PricePredictDto;
import com.uade.propertiesbackend.core.domain.dto.PropertyDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

public interface PricePredictRepository {

  @PostExchange("/predict")
  PricePredictDto predictPrice(@RequestBody PropertyDto propertyDto);
}
