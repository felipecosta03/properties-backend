package com.uade.propertiesbackend.core.usecase.impl.favorite;

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
        return favoritePropertyRepository.findByUserIdAndPropertyId(model.getUserId(), model.getPropertyId()).isPresent();
    }
}
