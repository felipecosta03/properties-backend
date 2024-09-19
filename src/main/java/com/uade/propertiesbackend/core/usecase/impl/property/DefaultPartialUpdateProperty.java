package com.uade.propertiesbackend.core.usecase.impl.property;

import static com.uade.propertiesbackend.util.ValidationUtils.validateActive;
import static com.uade.propertiesbackend.util.ValidationUtils.validateBeds;
import static com.uade.propertiesbackend.util.ValidationUtils.validatePropertyId;
import static com.uade.propertiesbackend.util.ValidationUtils.validateUserId;
import static java.util.Objects.isNull;

import com.uade.propertiesbackend.core.domain.Property;
import com.uade.propertiesbackend.core.domain.dto.PropertyDto;
import com.uade.propertiesbackend.core.exception.BadRequestException;
import com.uade.propertiesbackend.core.exception.NotFoundException;
import com.uade.propertiesbackend.core.exception.UnauthorizedException;
import com.uade.propertiesbackend.core.usecase.HasPropertyCurrentRent;
import com.uade.propertiesbackend.core.usecase.PartialUpdateProperty;
import com.uade.propertiesbackend.core.usecase.PropertyMapper;
import com.uade.propertiesbackend.repository.PropertyRepository;
import com.uade.propertiesbackend.util.ValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DefaultPartialUpdateProperty implements PartialUpdateProperty {
  private final PropertyRepository propertyRepository;
  private final HasPropertyCurrentRent hasPropertyCurrentRent;

  public DefaultPartialUpdateProperty(PropertyRepository propertyRepository,
      HasPropertyCurrentRent hasPropertyCurrentRent) {
    this.propertyRepository = propertyRepository;
    this.hasPropertyCurrentRent = hasPropertyCurrentRent;
  }
  @Override
  public PropertyDto apply(Model model) {
    Property property = propertyRepository.findById(model.getId()).orElseThrow(
        () -> new NotFoundException(String.format("Property with id=%s not found",
            model.getId())));

    if (!property.getUserId().equals(model.getUserId())) {
      throw new UnauthorizedException("User does not own the property");
    }

    if (hasPropertyCurrentRent.test(model.getId()) && model.getActive().orElse(property.isActive())) {
      throw new BadRequestException("Property already has a current rent");
    }
    this.validateModel(model);

    property.setBeds(model.getBeds().orElse(property.getBeds()));
    property.setBathrooms(model.getBathrooms().orElse(property.getBathrooms()));
    property.setDistrict(model.getDistrict().orElse(property.getDistrict()));
    property.setRooms(model.getRooms().orElse(property.getRooms()));
    property.setSurfaceCovered(model.getSurfaceCovered().orElse(property.getSurfaceCovered()));
    property.setSurfaceTotal(model.getSurfaceTotal().orElse(property.getSurfaceTotal()));
    property.setTitle(model.getTitle().orElse(property.getTitle()));
    property.setDescription(model.getDescription().orElse(property.getDescription()));
    property.setLatitude(model.getLatitude().orElse(property.getLatitude()));
    property.setLongitude(model.getLongitude().orElse(property.getLongitude()));
    property.setImages(model.getImages().orElse(property.getImages()));
    property.setAddress(model.getAddress().orElse(property.getAddress()));
    property.setZipcode(model.getZipcode().orElse(property.getZipcode()));
    property.setPrice(model.getPrice().orElse(property.getPrice()));
    property.setType(model.getType().orElse(property.getType()));
    property.setActive(model.getActive().orElse(property.isActive()));

    propertyRepository.save(property);

    log.info("Property with id={} updated", model.getId());
    return PropertyMapper.INSTANCE.propertyToPropertyDto(property);
  }

  private void validateModel(Model model) {
    if (isNull(model)) {
      throw new BadRequestException("Model is required");
    }
    validatePropertyId(model.getId());
    model.getBeds().ifPresent(ValidationUtils::validateBeds);
    model.getBathrooms().ifPresent(ValidationUtils::validateBathrooms);
    model.getDistrict().ifPresent(ValidationUtils::validateDistrict);
    model.getRooms().ifPresent(ValidationUtils::validateRooms);
    model.getSurfaceCovered().ifPresent(ValidationUtils::validateSurfaceCovered);
    model.getSurfaceTotal().ifPresent(ValidationUtils::validateSurfaceTotal);
    model.getTitle().ifPresent(ValidationUtils::validateTitle);
    model.getDescription().ifPresent(ValidationUtils::validateDescription);
    model.getLatitude().ifPresent(ValidationUtils::validateLatitude);
    model.getLongitude().ifPresent(ValidationUtils::validateLongitude);
    model.getImages().ifPresent(ValidationUtils::validateImages);
    validateUserId(model.getUserId());
    model.getZipcode().ifPresent(ValidationUtils::validateZipcode);
    model.getAddress().ifPresent(ValidationUtils::validateAddress);
    model.getPrice().ifPresent(ValidationUtils::validatePrice);
    model.getType().ifPresent(ValidationUtils::validatePropertyType);
    model.getActive().ifPresent(ValidationUtils::validateActive);
  }
}
