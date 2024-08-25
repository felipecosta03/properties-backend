package com.uade.propertiesbackend.core.usecase.impl;

import static java.util.Objects.isNull;

import com.uade.propertiesbackend.core.domain.FavoriteProperty;
import com.uade.propertiesbackend.core.domain.Property;
import com.uade.propertiesbackend.core.exception.BadRequestException;
import com.uade.propertiesbackend.core.usecase.RemoveFavoriteProperty;
import com.uade.propertiesbackend.core.usecase.UserExists;
import com.uade.propertiesbackend.repository.FavoritePropertyRepository;
import com.uade.propertiesbackend.repository.PropertyRepository;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class DefaultRemoveFavoriteProperty implements RemoveFavoriteProperty {

  private final FavoritePropertyRepository favoritePropertyRepository;
  private final UserExists userExists;
  private final PropertyRepository propertyRepository;

  public DefaultRemoveFavoriteProperty(FavoritePropertyRepository favoritePropertyRepository,
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

    propertyRepository.findById(model.getPropertyId())
        .orElseThrow(() -> new BadRequestException("Property does not exist"));

    Optional<FavoriteProperty> favoriteProperty = favoritePropertyRepository
        .findByUserIdAndPropertyId(model.getUserId(), model.getPropertyId());

    if (favoriteProperty.isEmpty()) {
      throw new BadRequestException("Property is not a favorite");
    }

    favoritePropertyRepository.delete(favoriteProperty.get());
  }

  private void validateModel(RemoveFavoriteProperty.Model model) {
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
