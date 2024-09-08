package com.uade.propertiesbackend.core.usecase.impl.property;

import static com.uade.propertiesbackend.util.ValidationUtils.validateActive;
import static com.uade.propertiesbackend.util.ValidationUtils.validateAddress;
import static com.uade.propertiesbackend.util.ValidationUtils.validateBathrooms;
import static com.uade.propertiesbackend.util.ValidationUtils.validateBeds;
import static com.uade.propertiesbackend.util.ValidationUtils.validateDescription;
import static com.uade.propertiesbackend.util.ValidationUtils.validateDistrict;
import static com.uade.propertiesbackend.util.ValidationUtils.validateImages;
import static com.uade.propertiesbackend.util.ValidationUtils.validateLatitude;
import static com.uade.propertiesbackend.util.ValidationUtils.validateLongitude;
import static com.uade.propertiesbackend.util.ValidationUtils.validatePrice;
import static com.uade.propertiesbackend.util.ValidationUtils.validatePropertyId;
import static com.uade.propertiesbackend.util.ValidationUtils.validatePropertyType;
import static com.uade.propertiesbackend.util.ValidationUtils.validateRooms;
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
import com.uade.propertiesbackend.core.usecase.HasPropertyCurrentRent;
import com.uade.propertiesbackend.core.usecase.PropertyMapper;
import com.uade.propertiesbackend.core.usecase.UpdateProperty;
import com.uade.propertiesbackend.repository.PropertyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DefaultUpdateProperty implements UpdateProperty {

  private final PropertyRepository propertyRepository;
  private final HasPropertyCurrentRent hasPropertyCurrentRent;

  public DefaultUpdateProperty(PropertyRepository propertyRepository,
      HasPropertyCurrentRent hasPropertyCurrentRent) {
    this.propertyRepository = propertyRepository;
    this.hasPropertyCurrentRent = hasPropertyCurrentRent;
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

    if (hasPropertyCurrentRent.test(model.getId()) && model.getActive()) {
      throw new BadRequestException("Property already has a current rent");
    }

    property.setBeds(model.getBeds());
    property.setBathrooms(model.getBathrooms());
    property.setDistrict(model.getDistrict());
    property.setRooms(model.getRooms());
    property.setSurfaceCovered(model.getSurfaceCovered());
    property.setSurfaceTotal(model.getSurfaceTotal());
    property.setTitle(model.getTitle());
    property.setDescription(model.getDescription());
    property.setLatitude(model.getLatitude());
    property.setLongitude(model.getLongitude());
    property.setImages(model.getImages());
    property.setAddress(model.getAddress());
    property.setPrice(model.getPrice());
    property.setType(model.getType());
    property.setActive(model.getActive());

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
    validateDistrict(model.getDistrict());
    validateRooms(model.getRooms());
    validateSurfaceCovered(model.getSurfaceCovered());
    validateSurfaceTotal(model.getSurfaceTotal());
    validateTitle(model.getTitle());
    validateDescription(model.getDescription());
    validateLatitude(model.getLatitude());
    validateLongitude(model.getLongitude());
    validateImages(model.getImages());
    validateUserId(model.getUserId());
    validateAddress(model.getAddress());
    validatePrice(model.getPrice());
    validatePropertyType(model.getType());
    validateActive(model.getActive());
  }
}
