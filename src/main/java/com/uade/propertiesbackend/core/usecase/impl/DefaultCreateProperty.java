package com.uade.propertiesbackend.core.usecase.impl;

import static com.uade.propertiesbackend.util.ValidationUtils.validateBathrooms;
import static com.uade.propertiesbackend.util.ValidationUtils.validateBeds;
import static com.uade.propertiesbackend.util.ValidationUtils.validateCity;
import static com.uade.propertiesbackend.util.ValidationUtils.validateCountry;
import static com.uade.propertiesbackend.util.ValidationUtils.validateDescription;
import static com.uade.propertiesbackend.util.ValidationUtils.validateImages;
import static com.uade.propertiesbackend.util.ValidationUtils.validateLatitude;
import static com.uade.propertiesbackend.util.ValidationUtils.validateLongitude;
import static com.uade.propertiesbackend.util.ValidationUtils.validatePrice;
import static com.uade.propertiesbackend.util.ValidationUtils.validateRooms;
import static com.uade.propertiesbackend.util.ValidationUtils.validateState;
import static com.uade.propertiesbackend.util.ValidationUtils.validateStoreys;
import static com.uade.propertiesbackend.util.ValidationUtils.validateStreet;
import static com.uade.propertiesbackend.util.ValidationUtils.validateStreetNumber;
import static com.uade.propertiesbackend.util.ValidationUtils.validateSurface;
import static com.uade.propertiesbackend.util.ValidationUtils.validateTitle;
import static com.uade.propertiesbackend.util.ValidationUtils.validateUserId;

import com.uade.propertiesbackend.core.domain.Property;
import com.uade.propertiesbackend.core.domain.dto.PropertyDto;
import com.uade.propertiesbackend.core.exception.NotFoundException;
import com.uade.propertiesbackend.core.usecase.CreateProperty;
import com.uade.propertiesbackend.core.usecase.UserExists;
import com.uade.propertiesbackend.repository.PropertyRepository;
import org.springframework.stereotype.Component;

/**
 * Use case for create property
 */
@Component
public class DefaultCreateProperty implements CreateProperty {

  private final PropertyRepository propertyRepository;
  private final UserExists userExists;

  public DefaultCreateProperty(PropertyRepository propertyRepository,
      UserExists userExists) {
    this.propertyRepository = propertyRepository;
    this.userExists = userExists;
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
            .country(model.getCountry()).city(model.getCity()).state(model.getState())
            .rooms(model.getRooms()).surface(model.getSurface()).title(model.getTitle())
            .description(model.getDescription()).latitude(model.getLatitude())
            .longitude(model.getLongitude()).images(model.getImages()).userId(model.getUserId())
            .street(model.getStreet()).streetNumber(model.getStreetNumber())
            .storeys(model.getStoreys()).price(model.getPrice()).build());

    return PropertyDto.builder().beds(property.getBeds()).bathrooms(property.getBathrooms())
        .country(property.getCountry()).city(property.getCity()).state(property.getState())
        .rooms(property.getRooms()).surface(property.getSurface()).title(property.getTitle())
        .description(property.getDescription()).latitude(property.getLatitude())
        .longitude(property.getLongitude()).images(property.getImages())
        .userId(property.getUserId()).street(property.getStreet())
        .streetNumber(property.getStreetNumber()).storeys(property.getStoreys())
        .price(property.getPrice()).id(property.getId()).build();
  }

  private void validateModel(Model model) {
    validateBeds(model.getBeds());
    validateBathrooms(model.getBathrooms());
    validateCountry(model.getCountry());
    validateCity(model.getCity());
    validateState(model.getState());
    validateRooms(model.getRooms());
    validateSurface(model.getSurface());
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
  }
}
