package com.uade.propertiesbackend.core.usecase.impl.property;

import com.uade.propertiesbackend.core.usecase.PropertyIsRented;
import com.uade.propertiesbackend.repository.RentRepository;
import org.springframework.stereotype.Component;

@Component
public class DefaultPropertyIsRented implements PropertyIsRented {

  private final RentRepository rentRepository;

  public DefaultPropertyIsRented(RentRepository rentRepository) {
    this.rentRepository = rentRepository;
  }

  @Override
  public boolean test(Long propertyId) {
    return rentRepository.existsByPropertyId(propertyId);
  }
}
