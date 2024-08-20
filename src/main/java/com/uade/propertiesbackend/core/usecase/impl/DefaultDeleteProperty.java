package com.uade.propertiesbackend.core.usecase.impl;

import static com.uade.propertiesbackend.util.ValidationUtils.validatePropertyId;

import com.uade.propertiesbackend.core.usecase.DeleteProperty;
import com.uade.propertiesbackend.repository.PropertyRepository;
import org.springframework.stereotype.Component;

@Component
public class DefaultDeleteProperty implements DeleteProperty {

  private final PropertyRepository propertyRepository;

  public DefaultDeleteProperty(PropertyRepository propertyRepository) {
    this.propertyRepository = propertyRepository;
  }

  @Override
  public void accept(Long propertyId) {
    validatePropertyId(propertyId);
    propertyRepository.deleteById(propertyId);
  }
}
