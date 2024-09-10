package com.uade.propertiesbackend.core.usecase.impl;

import com.uade.propertiesbackend.core.domain.dto.PricePredictDto;
import com.uade.propertiesbackend.core.domain.dto.PropertyDto;
import com.uade.propertiesbackend.core.usecase.PredictPropertyPrice;
import com.uade.propertiesbackend.core.usecase.RetrieveProperty;
import com.uade.propertiesbackend.repository.PricePredictRepository;
import org.springframework.stereotype.Component;

@Component
public class DefaultPredictPropertyPrice implements PredictPropertyPrice {

  private final RetrieveProperty retrieveProperty;
  private final PricePredictRepository pricePredictRepository;

  public DefaultPredictPropertyPrice(RetrieveProperty retrieveProperty,
      PricePredictRepository pricePredictRepository) {
    this.retrieveProperty = retrieveProperty;
    this.pricePredictRepository = pricePredictRepository;
  }

  @Override
  public PricePredictDto apply(Long propertyId) {

    PropertyDto propertyDto = retrieveProperty.apply(propertyId);

    return pricePredictRepository.predictPrice(propertyDto);
  }
}
