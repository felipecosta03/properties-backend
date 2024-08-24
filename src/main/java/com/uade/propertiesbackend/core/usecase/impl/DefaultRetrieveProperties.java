package com.uade.propertiesbackend.core.usecase.impl;

import static java.util.Objects.isNull;

import com.uade.propertiesbackend.core.domain.Property;
import com.uade.propertiesbackend.core.domain.dto.PropertyDto;
import com.uade.propertiesbackend.core.exception.BadRequestException;
import com.uade.propertiesbackend.core.usecase.PropertyMapper;
import com.uade.propertiesbackend.core.usecase.RetrieveProperties;
import com.uade.propertiesbackend.core.usecase.RetrievePropertySpecs;
import com.uade.propertiesbackend.repository.PropertyRepository;
import com.uade.propertiesbackend.util.ValidationUtils;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class DefaultRetrieveProperties implements RetrieveProperties {

  private static final Integer PAGE_SIZE = 20;
  private final PropertyRepository propertyRepository;
  private final RetrievePropertySpecs retrievePropertySpecs;

  public DefaultRetrieveProperties(PropertyRepository propertyRepository,
      RetrievePropertySpecs retrievePropertySpecs) {
    this.propertyRepository = propertyRepository;
    this.retrievePropertySpecs = retrievePropertySpecs;
  }

  @Override
  public Page<PropertyDto> apply(Model model) {
    validateModel(model);

    Specification<Property> specification = retrievePropertySpecs.apply(
        RetrievePropertySpecs.Model.builder()
            .minPrice(model.getMinPrice())
            .maxPrice(model.getMaxPrice())
            .minRooms(model.getMinRooms())
            .maxRooms(model.getMaxRooms())
            .rooms(model.getRooms())
            .minBeds(model.getMinBeds())
            .maxBeds(model.getMaxBeds())
            .beds(model.getBeds())
            .minBathrooms(model.getMinBathrooms())
            .maxBathrooms(model.getMaxBathrooms())
            .bathrooms(model.getBathrooms())
            .minGarages(model.getMinGarages())
            .maxGarages(model.getMaxGarages())
            .garages(model.getGarages())
            .minStoreys(model.getMinStoreys())
            .maxStoreys(model.getMaxStoreys())
            .storeys(model.getStoreys())
            .propertyType(model.getPropertyType())
            .minSurfaceCovered(model.getMinSurfaceCovered())
            .maxSurfaceCovered(model.getMaxSurfaceCovered())
            .minSurfaceTotal(model.getMinSurfaceTotal())
            .maxSurfaceTotal(model.getMaxSurfaceTotal())
            .build());

    return propertyRepository.findAll(specification,
            PageRequest.of(model.getPage().orElse(0), PAGE_SIZE))
        .map(PropertyMapper.INSTANCE::propertyToPropertyDto);
  }

  private void validateModel(Model model) {
    if (isNull(model)) {
      throw new BadRequestException("Model is required");
    }
    validateMinMax(model.getMinPrice(), model.getMaxPrice(), "price");
    validateMinMax(model.getMinGarages(), model.getMaxGarages(), "garages");
    validateMinMax(model.getMinBeds(), model.getMaxBeds(), "beds");
    validateMinMax(model.getMinRooms(), model.getMaxRooms(), "rooms");
    validateMinMax(model.getMinStoreys(), model.getMaxStoreys(), "storeys");
    validateMinMax(model.getMinBathrooms(), model.getMaxBathrooms(), "bathrooms");
    validateMinMax(model.getMinSurfaceCovered(), model.getMaxSurfaceCovered(), "surface covered");
    validateMinMax(model.getMinSurfaceTotal(), model.getMaxSurfaceTotal(), "surface total");

    model.getRooms().ifPresent(ValidationUtils::validateRooms);
    model.getBeds().ifPresent(ValidationUtils::validateBeds);
    model.getBathrooms().ifPresent(ValidationUtils::validateBathrooms);
    model.getGarages().ifPresent(ValidationUtils::validateGarages);
    model.getStoreys().ifPresent(ValidationUtils::validateStoreys);
    model.getPropertyType().ifPresent(ValidationUtils::validatePropertyType);

    model.getMinRooms().ifPresent(ValidationUtils::validateRooms);
    model.getMinBeds().ifPresent(ValidationUtils::validateBeds);
    model.getMinBathrooms().ifPresent(ValidationUtils::validateBathrooms);
    model.getMinGarages().ifPresent(ValidationUtils::validateGarages);
    model.getMinStoreys().ifPresent(ValidationUtils::validateStoreys);
    model.getMinSurfaceCovered().ifPresent(ValidationUtils::validateSurfaceCovered);
    model.getMinSurfaceTotal().ifPresent(ValidationUtils::validateSurfaceTotal);
    model.getMinPrice().ifPresent(ValidationUtils::validatePrice);

    model.getMaxRooms().ifPresent(ValidationUtils::validateRooms);
    model.getMaxBeds().ifPresent(ValidationUtils::validateBeds);
    model.getMaxBathrooms().ifPresent(ValidationUtils::validateBathrooms);
    model.getMaxGarages().ifPresent(ValidationUtils::validateGarages);
    model.getMaxStoreys().ifPresent(ValidationUtils::validateStoreys);
    model.getMaxSurfaceCovered().ifPresent(ValidationUtils::validateSurfaceCovered);
    model.getMaxSurfaceCovered().ifPresent(ValidationUtils::validateSurfaceTotal);
    model.getMaxPrice().ifPresent(ValidationUtils::validatePrice);

    model.getPage().ifPresent(ValidationUtils::validatePage);

  }

  private <T extends Number> void validateMinMax(Optional<T> min, Optional<T> max, String field) {
    if (min.isPresent() && max.isPresent() && min.get().doubleValue() > max.get().doubleValue()) {
      throw new BadRequestException(String.format("Min %s must be less than max %s", field, field));
    }
  }
}
