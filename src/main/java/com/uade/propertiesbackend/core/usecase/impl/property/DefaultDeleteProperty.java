package com.uade.propertiesbackend.core.usecase.impl.property;

import static com.uade.propertiesbackend.util.ValidationUtils.validatePropertyId;

import com.uade.propertiesbackend.core.usecase.DeleteProperty;
import com.uade.propertiesbackend.repository.PropertyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DefaultDeleteProperty implements DeleteProperty {

  private final PropertyRepository propertyRepository;

  public DefaultDeleteProperty(PropertyRepository propertyRepository) {
    this.propertyRepository = propertyRepository;
  }

  @Override
  public void accept(Long propertyId) {
    validatePropertyId(propertyId);
    propertyRepository.deleteById(propertyId);
    log.info("Property with id={} deleted", propertyId);
  }
}
