package com.uade.propertiesbackend.core.usecase.impl;

import static com.uade.propertiesbackend.util.ValidationUtils.validateBathrooms;
import static com.uade.propertiesbackend.util.ValidationUtils.validateBeds;
import static com.uade.propertiesbackend.util.ValidationUtils.validateCity;
import static com.uade.propertiesbackend.util.ValidationUtils.validateCountry;
import static com.uade.propertiesbackend.util.ValidationUtils.validateDescription;
import static com.uade.propertiesbackend.util.ValidationUtils.validateGarages;
import static com.uade.propertiesbackend.util.ValidationUtils.validateImages;
import static com.uade.propertiesbackend.util.ValidationUtils.validateLatitude;
import static com.uade.propertiesbackend.util.ValidationUtils.validateLongitude;
import static com.uade.propertiesbackend.util.ValidationUtils.validatePrice;
import static com.uade.propertiesbackend.util.ValidationUtils.validatePropertyId;
import static com.uade.propertiesbackend.util.ValidationUtils.validatePropertyType;
import static com.uade.propertiesbackend.util.ValidationUtils.validateRooms;
import static com.uade.propertiesbackend.util.ValidationUtils.validateState;
import static com.uade.propertiesbackend.util.ValidationUtils.validateStoreys;
import static com.uade.propertiesbackend.util.ValidationUtils.validateStreet;
import static com.uade.propertiesbackend.util.ValidationUtils.validateStreetNumber;
import static com.uade.propertiesbackend.util.ValidationUtils.validateSurfaceCovered;
import static com.uade.propertiesbackend.util.ValidationUtils.validateSurfaceTotal;
import static com.uade.propertiesbackend.util.ValidationUtils.validateTitle;
import static com.uade.propertiesbackend.util.ValidationUtils.validateUserId;
import static java.util.Objects.isNull;

import com.uade.propertiesbackend.core.domain.Property;
import com.uade.propertiesbackend.core.domain.dto.PropertyDto;
import com.uade.propertiesbackend.core.exception.BadRequestException;
import com.uade.propertiesbackend.core.exception.NotFoundException;
import com.uade.propertiesbackend.core.exception.UnauthorizedException;
import com.uade.propertiesbackend.core.usecase.PropertyMapper;
import com.uade.propertiesbackend.core.usecase.UpdateProperty;
import com.uade.propertiesbackend.repository.PropertyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DefaultUpdateProperty implements UpdateProperty {

  private final PropertyRepository propertyRepository;

  public DefaultUpdateProperty(PropertyRepository propertyRepository) {
    this.propertyRepository = propertyRepository;
  }

  @Override
  public PropertyDto apply(Model model) {
    validateModel(model);
    Property property = propertyRepository.findById(model.getId()).orElseThrow(
        () -> new NotFoundException(String.format("Property with id=%s not found",
            model.getId())));

    if (!property.getUserId().equals(model.getUserId())) {
      throw new UnauthorizedException("User does not own the property");
    }

    property.setBeds(model.getBeds());
    property.setBathrooms(model.getBathrooms());
    property.setCountry(model.getCountry());
    property.setCity(model.getCity());
    property.setState(model.getState());
    property.setRooms(model.getRooms());
    property.setSurfaceCovered(model.getSurfaceCovered());
    property.setSurfaceTotal(model.getSurfaceTotal());
    property.setTitle(model.getTitle());
    property.setDescription(model.getDescription());
    property.setLatitude(model.getLatitude());
    property.setLongitude(model.getLongitude());
    property.setImages(model.getImages());
    property.setStreet(model.getStreet());
    property.setStreetNumber(model.getStreetNumber());
    property.setStoreys(model.getStoreys());
    property.setPrice(model.getPrice());
    property.setType(model.getType());
    property.setGarages(model.getGarages());

    propertyRepository.save(property);

    log.info("Property with id={} updated", model.getId());
    return PropertyMapper.INSTANCE.propertyToPropertyDto(property);
  }

  private void validateModel(UpdateProperty.Model model) {
    if (isNull(model)) {
      throw new BadRequestException("Model is required");
    }
    validatePropertyId(model.getId());
    validateBeds(model.getBeds());
    validateBathrooms(model.getBathrooms());
    validateCountry(model.getCountry());
    validateCity(model.getCity());
    validateState(model.getState());
    validateRooms(model.getRooms());
    validateSurfaceCovered(model.getSurfaceCovered());
    validateSurfaceTotal(model.getSurfaceTotal());
    validateTitle(model.getTitle());
    validateDescription(model.getDescription());
    validateLatitude(model.getLatitude());
    validateLongitude(model.getLongitude());
    validateImages(model.getImages());
    validateUserId(model.getUserId());
    validateStreet(model.getStreet());
    validateStreetNumber(model.getStreetNumber());
    validateStoreys(model.getStoreys());
    validatePrice(model.getPrice());
    validateGarages(model.getGarages());
    validatePropertyType(model.getType());
  }
}
