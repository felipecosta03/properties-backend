package com.uade.propertiesbackend.core.usecase.impl.favorite;

import static java.util.Objects.isNull;

import com.uade.propertiesbackend.core.usecase.PropertyIsFavorite;
import com.uade.propertiesbackend.repository.FavoritePropertyRepository;
import org.springframework.stereotype.Component;

@Component
public class DefaultPropertyIsFavorite implements PropertyIsFavorite {

  private final FavoritePropertyRepository favoritePropertyRepository;

  public DefaultPropertyIsFavorite(FavoritePropertyRepository favoritePropertyRepository) {
    this.favoritePropertyRepository = favoritePropertyRepository;
  }

  @Override
  public boolean test(Model model) {
    if (isNull(model.getUserId())) {
      return false;
    }
    return favoritePropertyRepository.findByUserIdAndPropertyId(model.getUserId(),
        model.getPropertyId()).isPresent();
  }
}
