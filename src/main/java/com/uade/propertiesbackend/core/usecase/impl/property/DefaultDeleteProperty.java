package com.uade.propertiesbackend.core.usecase.impl.property;

import static com.uade.propertiesbackend.util.ValidationUtils.validatePropertyId;

import com.uade.propertiesbackend.core.exception.BadRequestException;
import com.uade.propertiesbackend.core.usecase.DeleteProperty;
import com.uade.propertiesbackend.core.usecase.PropertyIsRented;
import com.uade.propertiesbackend.repository.PropertyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DefaultDeleteProperty implements DeleteProperty {

  private final PropertyRepository propertyRepository;
  private final PropertyIsRented propertyIsRented;

  public DefaultDeleteProperty(PropertyRepository propertyRepository,
      PropertyIsRented propertyIsRented) {
    this.propertyRepository = propertyRepository;
    this.propertyIsRented = propertyIsRented;
  }

  @Override
  public void accept(Long propertyId) {
    validatePropertyId(propertyId);
    if (propertyIsRented.test(propertyId)) {
      throw new BadRequestException(
          String.format("Property with id=%s is rented and cannot be deleted", propertyId));
    }
    propertyRepository.deleteById(propertyId);
    log.info("Property with id={} deleted", propertyId);
  }
}
