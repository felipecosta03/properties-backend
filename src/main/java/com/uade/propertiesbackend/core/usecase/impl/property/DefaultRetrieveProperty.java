package com.uade.propertiesbackend.core.usecase.impl;

import static com.uade.propertiesbackend.util.ValidationUtils.validatePropertyId;

import com.uade.propertiesbackend.core.domain.dto.PropertyDto;
import com.uade.propertiesbackend.core.exception.NotFoundException;
import com.uade.propertiesbackend.core.usecase.PropertyMapper;
import com.uade.propertiesbackend.core.usecase.RetrieveProperty;
import com.uade.propertiesbackend.repository.PropertyRepository;
import org.springframework.stereotype.Component;

@Component
public class DefaultRetrieveProperty implements RetrieveProperty {

  private final PropertyRepository propertyRepository;

  public DefaultRetrieveProperty(PropertyRepository propertyRepository) {
    this.propertyRepository = propertyRepository;
  }

  @Override
  public PropertyDto apply(Long propertyId) {
    validatePropertyId(propertyId);
    return propertyRepository.findById(propertyId)
        .map(PropertyMapper.INSTANCE::propertyToPropertyDto).orElseThrow(
            () -> new NotFoundException(
                String.format("Property with id=%s not found.", propertyId)));
  }
}
