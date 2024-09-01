package com.uade.propertiesbackend.core.usecase.impl.favorite;

import static com.uade.propertiesbackend.util.ValidationUtils.validatePage;
import static com.uade.propertiesbackend.util.ValidationUtils.validateUserId;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;

import com.uade.propertiesbackend.core.domain.FavoriteProperty;
import com.uade.propertiesbackend.core.domain.dto.PropertyDto;
import com.uade.propertiesbackend.core.exception.BadRequestException;
import com.uade.propertiesbackend.core.usecase.PropertyMapper;
import com.uade.propertiesbackend.core.usecase.RetrieveFavoriteProperties;
import com.uade.propertiesbackend.repository.FavoritePropertyRepository;
import com.uade.propertiesbackend.repository.PropertyRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class DefaultRetrieveFavoriteProperties implements RetrieveFavoriteProperties {

  private static final Integer PAGE_SIZE = 20;

  private final PropertyRepository propertyRepository;
  private final FavoritePropertyRepository favoritePropertyRepository;

  public DefaultRetrieveFavoriteProperties(PropertyRepository propertyRepository,
      FavoritePropertyRepository favoritePropertyRepository) {
    this.propertyRepository = propertyRepository;
    this.favoritePropertyRepository = favoritePropertyRepository;
  }

  @Override
  public Page<PropertyDto> apply(Model model) {
    validateModel(model);

    List<Long> propertiesId = favoritePropertyRepository.findAllByUserId(model.getUserId()).stream()
        .map(FavoriteProperty::getPropertyId).collect(toList());

    return propertyRepository.findPropertiesByIdIn(propertiesId,
            PageRequest.of(model.getPage(), PAGE_SIZE))
        .map(PropertyMapper.INSTANCE::propertyToPropertyDto);
  }

  private void validateModel(Model model) {
    if (isNull(model)) {
      throw new BadRequestException("Model is required");
    }
    validateUserId(model.getUserId());
    validatePage(model.getPage());
  }
}
