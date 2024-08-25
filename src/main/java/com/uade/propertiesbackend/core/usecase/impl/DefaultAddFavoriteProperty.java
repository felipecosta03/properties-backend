package com.uade.propertiesbackend.core.usecase.impl;

import static java.util.Objects.isNull;

import com.uade.propertiesbackend.core.domain.FavoriteProperty;
import com.uade.propertiesbackend.core.domain.Property;
import com.uade.propertiesbackend.core.exception.BadRequestException;
import com.uade.propertiesbackend.core.usecase.AddFavoriteProperty;
import com.uade.propertiesbackend.core.usecase.UserExists;
import com.uade.propertiesbackend.repository.FavoritePropertyRepository;
import com.uade.propertiesbackend.repository.PropertyRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

@Component
public class DefaultAddFavoriteProperty implements AddFavoriteProperty {

  private final FavoritePropertyRepository favoritePropertyRepository;
  private final UserExists userExists;
  private final PropertyRepository propertyRepository;

  public DefaultAddFavoriteProperty(FavoritePropertyRepository favoritePropertyRepository,
      UserExists userExists, PropertyRepository propertyRepository) {
    this.favoritePropertyRepository = favoritePropertyRepository;
    this.userExists = userExists;
    this.propertyRepository = propertyRepository;
  }

  @Override
  public void accept(Model model) {
    validateModel(model);

    if (!userExists.test(model.getUserId())) {
      throw new BadRequestException("User does not exist");
    }

    Property property = propertyRepository.findById(model.getPropertyId())
        .orElseThrow(() -> new BadRequestException("Property does not exist"));

    // Cannot add property to favorites if it is inactive
    if (!property.isActive()) {
      throw new BadRequestException("Property is not active");
    }

    if (favoritePropertyRepository.exists(Example.of(
        FavoriteProperty.builder().propertyId(model.getPropertyId()).userId(model.getUserId())
            .build()))) {
      throw new BadRequestException("Property is already a favorite");
    }

    favoritePropertyRepository.save(FavoriteProperty.builder().propertyId(model.getPropertyId())
        .userId(model.getUserId()).build());
  }

  private void validateModel(Model model) {
    if (isNull(model)) {
      throw new BadRequestException("Model is required");
    }
    if (isNull(model.getUserId())) {
      throw new BadRequestException("userId is required");
    }
    if (isNull(model.getPropertyId())) {
      throw new BadRequestException("propertyId is required");
    }
  }
}
