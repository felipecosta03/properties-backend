package com.uade.propertiesbackend.core.usecase.impl.property;

import static com.uade.propertiesbackend.util.ValidationUtils.validatePropertyId;

import com.uade.propertiesbackend.core.domain.dto.PropertyDetailsDTO;
import com.uade.propertiesbackend.core.domain.dto.PropertyDto;
import com.uade.propertiesbackend.core.domain.dto.PropertyParametersDTO;
import com.uade.propertiesbackend.core.exception.NotFoundException;
import com.uade.propertiesbackend.core.usecase.PropertyIsDisable;
import com.uade.propertiesbackend.core.usecase.PropertyIsFavorite;
import com.uade.propertiesbackend.core.usecase.PropertyMapper;
import com.uade.propertiesbackend.core.usecase.RetrieveProperty;
import com.uade.propertiesbackend.repository.PropertyRepository;
import org.springframework.stereotype.Component;

@Component
public class DefaultRetrieveProperty implements RetrieveProperty {

  private final PropertyRepository propertyRepository;
  private final PropertyIsFavorite propertyIsFavorite;
  private final PropertyIsDisable propertyIsDisable;

  public DefaultRetrieveProperty(PropertyRepository propertyRepository, PropertyIsFavorite propertyIsFavorite,
      PropertyIsDisable propertyIsDisable) {
    this.propertyRepository = propertyRepository;
    this.propertyIsFavorite = propertyIsFavorite;
    this.propertyIsDisable = propertyIsDisable;
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
  public PropertyDetailsDTO apply(PropertyParametersDTO propertyParametersDTO) {
    validatePropertyId(propertyParametersDTO.getPropertyId());
    return propertyRepository.findById(propertyParametersDTO.getPropertyId())
        .map((property -> PropertyMapper.INSTANCE.propertyToPropertyDetailsDTO(property,
            this.checkIsFavorite(property.getId(), propertyParametersDTO.getUserId()),
            this.checkIsDisable(property.getId(), propertyParametersDTO.getUserId(),
                property.getUserId())
            ))).orElseThrow(
            () -> new NotFoundException(
                String.format("Property with id=%s not found.",
                    propertyParametersDTO.getPropertyId())));
  }

  private boolean checkIsFavorite(Long id, Long userId) {
    return propertyIsFavorite.test(
        PropertyIsFavorite.Model.builder().propertyId(id).userId(userId).build());
  }

  private boolean checkIsDisable(Long id, Long userId, Long propertyUserId) {
    return propertyIsDisable.test(
        PropertyIsDisable.Model.builder().propertyId(id).propertyUserId(propertyUserId).userId(userId).build());
  }

}

