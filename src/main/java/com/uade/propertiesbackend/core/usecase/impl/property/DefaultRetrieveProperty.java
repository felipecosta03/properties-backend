package com.uade.propertiesbackend.core.usecase.impl.property;

import static com.uade.propertiesbackend.util.ValidationUtils.validatePropertyId;

import com.uade.propertiesbackend.core.domain.dto.PropertyDto;
import com.uade.propertiesbackend.core.domain.dto.PropertyParametersDTO;
import com.uade.propertiesbackend.core.exception.NotFoundException;
import com.uade.propertiesbackend.core.usecase.PropertyIsFavorite;
import com.uade.propertiesbackend.core.usecase.PropertyMapper;
import com.uade.propertiesbackend.core.usecase.RetrieveProperty;
import com.uade.propertiesbackend.repository.PropertyRepository;
import org.springframework.stereotype.Component;

@Component
public class DefaultRetrieveProperty implements RetrieveProperty {

  private final PropertyRepository propertyRepository;
  private final PropertyIsFavorite propertyIsFavorite;

  public DefaultRetrieveProperty(PropertyRepository propertyRepository, PropertyIsFavorite propertyIsFavorite) {
    this.propertyRepository = propertyRepository;
      this.propertyIsFavorite = propertyIsFavorite;
  }

  @Override
  public PropertyDto apply(Long propertyId) {
    validatePropertyId(propertyId);
    return propertyRepository.findById(propertyId)
        .map(PropertyMapper.INSTANCE::propertyToPropertyDto).orElseThrow(
            () -> new NotFoundException(
                String.format("Property with id=%s not found.", propertyId)));
  }

  @Override
  public PropertyDto apply(PropertyParametersDTO propertyParametersDTO) {
    validatePropertyId(propertyParametersDTO.getPropertyId());
    return propertyRepository.findById(propertyParametersDTO.getPropertyId())
            .map((property -> PropertyMapper.INSTANCE.propertyToPropertyDto(property, propertyIsFavorite.test(PropertyIsFavorite.Model.builder().propertyId(propertyParametersDTO.getPropertyId()).userId(propertyParametersDTO.getUserId()).build())))).orElseThrow(
                    () -> new NotFoundException(
                            String.format("Property with id=%s not found.", propertyParametersDTO.getPropertyId())));
  }
}
