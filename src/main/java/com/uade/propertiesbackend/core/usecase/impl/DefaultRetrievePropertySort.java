package com.uade.propertiesbackend.core.usecase.impl;

import static java.util.Objects.isNull;

import com.uade.propertiesbackend.core.domain.PropertySortBy;
import com.uade.propertiesbackend.core.exception.BadRequestException;
import com.uade.propertiesbackend.core.usecase.RetrievePropertySort;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class DefaultRetrievePropertySort implements RetrievePropertySort {

  @Override
  public Sort apply(PropertySortBy sortBy) {
    if (isNull(sortBy)) {
      throw new BadRequestException("SortBy cannot be null");
    }
    return switch (sortBy) {
      case PRICE_ASC -> Sort.by(Sort.Order.asc("price"));
      case PRICE_DESC -> Sort.by(Sort.Order.desc("price"));
      case RECENT -> Sort.by(Sort.Order.desc("createdAt"));
    };
  }
}
