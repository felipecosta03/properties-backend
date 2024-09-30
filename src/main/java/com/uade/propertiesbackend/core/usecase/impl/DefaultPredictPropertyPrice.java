package com.uade.propertiesbackend.core.usecase.impl;

import com.uade.propertiesbackend.core.domain.ClassificationPrice;
import com.uade.propertiesbackend.core.domain.dto.PricePredictDto;
import com.uade.propertiesbackend.core.domain.dto.PricePredictResponseDto;
import com.uade.propertiesbackend.core.domain.dto.PropertyDto;
import com.uade.propertiesbackend.core.usecase.PredictPropertyPrice;
import com.uade.propertiesbackend.core.usecase.RetrieveProperty;
import com.uade.propertiesbackend.repository.PricePredictRepository;
import org.springframework.stereotype.Component;

@Component
public class DefaultPredictPropertyPrice implements PredictPropertyPrice {

  private final Double DOLAR_RATE = 1100D;
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
    propertyDto.setPrice(propertyDto.getPrice()/DOLAR_RATE);
    PricePredictResponseDto responseDto = pricePredictRepository.predictPrice(propertyDto);
    String classification = classificateRealPrice(propertyDto.getPrice(),
        responseDto.getEstimatedPrice());

    return PricePredictDto.builder().estimatedPrice(responseDto.getEstimatedPrice())
        .classification(classification).build();
  }

  private String classificateRealPrice(Double realPrice, Double estimatedPrice) {
    Double rate = realPrice / estimatedPrice;
    if (rate <= ClassificationPrice.ECONOMICAL.getThreshold()) {
      return ClassificationPrice.ECONOMICAL.toString();
    } else if (rate <= ClassificationPrice.AFFORDABLE.getThreshold()) {
      return ClassificationPrice.AFFORDABLE.toString();
    } else if (rate <= ClassificationPrice.MARKET_PRICE.getThreshold()) {
      return ClassificationPrice.MARKET_PRICE.toString();
    } else if (rate <= ClassificationPrice.PREMIUM.getThreshold()) {
      return ClassificationPrice.PREMIUM.toString();
    } else {
      return ClassificationPrice.LUXURY.toString();
    }
  }


}
