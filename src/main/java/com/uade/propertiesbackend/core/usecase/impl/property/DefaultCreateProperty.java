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
import static com.uade.propertiesbackend.util.ValidationUtils.validatePropertyType;
import static com.uade.propertiesbackend.util.ValidationUtils.validateRooms;
import static com.uade.propertiesbackend.util.ValidationUtils.validateSurfaceCovered;
import static com.uade.propertiesbackend.util.ValidationUtils.validateTitle;
import static com.uade.propertiesbackend.util.ValidationUtils.validateUserId;
import static com.uade.propertiesbackend.util.ValidationUtils.validateZipcode;
import static java.util.Objects.isNull;

import com.uade.propertiesbackend.core.domain.Property;
import com.uade.propertiesbackend.core.domain.dto.PropertyDto;
import com.uade.propertiesbackend.core.exception.BadRequestException;
import com.uade.propertiesbackend.core.exception.NotFoundException;
import com.uade.propertiesbackend.core.usecase.CreateImages;
import com.uade.propertiesbackend.core.usecase.CreateProperty;
import com.uade.propertiesbackend.core.usecase.PropertyMapper;
import com.uade.propertiesbackend.core.usecase.UserExists;
import com.uade.propertiesbackend.repository.PropertyRepository;
import com.uade.propertiesbackend.router.sqs.publisher.PropertyCreatedPublisher;
import com.uade.propertiesbackend.router.sqs.publisher.Publisher;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

/**
 * Use case for create property
 */
@Component
public class DefaultCreateProperty implements CreateProperty {

  private final PropertyRepository propertyRepository;
  private final UserExists userExists;
  private final CreateImages createImages;
  private final Publisher<Property> propertyCreatedPublisher;

  public DefaultCreateProperty(PropertyRepository propertyRepository, UserExists userExists,
      CreateImages createImages, PropertyCreatedPublisher propertyCreatedPublisher) {
    this.propertyRepository = propertyRepository;
    this.userExists = userExists;
    this.createImages = createImages;
    this.propertyCreatedPublisher = propertyCreatedPublisher;
  }

  @Override
  public PropertyDto apply(Model model) {
    validateModel(model);

    //TODO Validate user exists
    if (!userExists.test(model.getUserId())) {
      throw new NotFoundException("User does not exist");
    }

    Property property = propertyRepository.save(
        Property.builder().beds(model.getBeds()).bathrooms(model.getBathrooms())
            .district(model.getDistrict()).rooms(model.getRooms())
            .surfaceCovered(model.getSurfaceCovered()).surfaceTotal(model.getSurfaceTotal())
            .title(model.getTitle()).description(model.getDescription())
            .latitude(model.getLatitude()).longitude(model.getLongitude())
            .images(createImages.apply(model.getImages())).userId(model.getUserId())
            .zipcode(model.getZipcode()).address(model.getAddress()).price(model.getPrice())
            .type(model.getType()).createdAt(LocalDateTime.now()).active(model.getActive())
            .build());

    propertyCreatedPublisher.apply(property);

    return PropertyMapper.INSTANCE.propertyToPropertyDto(property);
  }

  private void validateModel(Model model) {
    if (isNull(model)) {
      throw new BadRequestException("Model is required");
    }
    validateBeds(model.getBeds());
    validateBathrooms(model.getBathrooms());
    validateDistrict(model.getDistrict());
    validateRooms(model.getRooms());
    validateSurfaceCovered(model.getSurfaceCovered());
    validateSurfaceCovered(model.getSurfaceTotal());
    validateTitle(model.getTitle());
    validateDescription(model.getDescription());
    validateLatitude(model.getLatitude());
    validateLongitude(model.getLongitude());
    validateImages(model.getImages());
    validateUserId(model.getUserId());
    validateAddress(model.getAddress());
    validateZipcode(model.getZipcode());
    validatePrice(model.getPrice());
    validatePropertyType(model.getType());
    validateActive(model.getActive());
  }
}
