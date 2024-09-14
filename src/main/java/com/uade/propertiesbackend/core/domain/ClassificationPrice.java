package com.uade.propertiesbackend.core.domain;

import lombok.Getter;

@Getter
public enum ClassificationPrice {
  ECONOMICAL(1 / 1.3), AFFORDABLE(1 / 1.1), MARKET_PRICE(1.1), PREMIUM(1.3), LUXURY(
      Double.MAX_VALUE);


  private final Double threshold;

  ClassificationPrice(Double threshold) {
    this.threshold = threshold;
  }
}
