package com.uade.propertiesbackend.core.usecase.impl.property;

import static com.uade.propertiesbackend.util.SecurityUtils.getPrincipal;
import static java.util.Objects.isNull;

import com.uade.propertiesbackend.core.domain.Property;
import com.uade.propertiesbackend.core.domain.dto.PropertyDto;
import com.uade.propertiesbackend.core.domain.security.UserAuthenticationPrincipal;
import com.uade.propertiesbackend.core.exception.BadRequestException;
import com.uade.propertiesbackend.core.usecase.PropertyIsFavorite;
import com.uade.propertiesbackend.core.usecase.PropertyIsRented;
import com.uade.propertiesbackend.core.usecase.PropertyMapper;
import com.uade.propertiesbackend.core.usecase.RetrieveProperties;
import com.uade.propertiesbackend.core.usecase.RetrievePropertySort;
import com.uade.propertiesbackend.core.usecase.RetrievePropertySpecs;
import com.uade.propertiesbackend.repository.PropertyRepository;
import com.uade.propertiesbackend.util.ValidationUtils;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class DefaultRetrieveProperties implements RetrieveProperties {

  private static final Integer PAGE_SIZE = 40;
  private final PropertyRepository propertyRepository;
  private final RetrievePropertySpecs retrievePropertySpecs;
  private final RetrievePropertySort retrievePropertySort;
  private final PropertyIsFavorite propertyIsFavorite;
  private final PropertyIsRented propertyIsRented;

  public DefaultRetrieveProperties(PropertyRepository propertyRepository,
      RetrievePropertySpecs retrievePropertySpecs, RetrievePropertySort retrievePropertySort,
      PropertyIsFavorite propertyIsFavorite, PropertyIsRented propertyIsRented) {
    this.propertyRepository = propertyRepository;
    this.retrievePropertySpecs = retrievePropertySpecs;
    this.retrievePropertySort = retrievePropertySort;
    this.propertyIsFavorite = propertyIsFavorite;
    this.propertyIsRented = propertyIsRented;
  }

  @Override
  public Page<PropertyDto> apply(Model model) {
    validateModel(model);

    Specification<Property> specification = retrievePropertySpecs.apply(
        RetrievePropertySpecs.Model.builder().minPrice(model.getMinPrice())
            .active(model.getActive()).districts(model.getDistricts()).maxPrice(model.getMaxPrice())
            .minRooms(model.getMinRooms()).maxRooms(model.getMaxRooms()).rooms(model.getRooms())
            .minBeds(model.getMinBeds()).maxBeds(model.getMaxBeds()).beds(model.getBeds())
            .minBathrooms(model.getMinBathrooms()).maxBathrooms(model.getMaxBathrooms())
            .bathrooms(model.getBathrooms()).propertyType(model.getPropertyType())
            .minSurfaceCovered(model.getMinSurfaceCovered())
            .maxSurfaceCovered(model.getMaxSurfaceCovered())
            .minSurfaceTotal(model.getMinSurfaceTotal()).maxSurfaceTotal(model.getMaxSurfaceTotal())
            .minLat(model.getMinLat()).minLon(model.getMinLon()).maxLat(model.getMaxLat())
            .maxLon(model.getMaxLon()).propertyOwnerId(model.getPropertyOwnerId()).build());

    final Sort sortBy = model.getSortBy().map(retrievePropertySort)
        .orElse(Sort.by(Sort.Order.asc("id")));

    Page<Property> properties = propertyRepository.findAll(specification,
        PageRequest.of(model.getPage().orElse(0), model.getSize().orElse(PAGE_SIZE), sortBy));

    final boolean delete = getPrincipal()
        .map(UserAuthenticationPrincipal::isAdmin).orElse(false);

    return properties.map(property -> PropertyMapper.INSTANCE.propertyToPropertyDto(property,
        propertyIsFavorite.test(PropertyIsFavorite.Model.builder().userId(model.getUserId())
            .propertyId(property.getId()).build()), propertyIsRented.test(property.getId()),
        delete));
  }

  private void validateModel(Model model) {
    if (isNull(model)) {
      throw new BadRequestException("Model is required");
    }

    validateMinMax(model.getMinPrice(), model.getMaxPrice(), "price");
    validateMinMax(model.getMinBeds(), model.getMaxBeds(), "beds");
    validateMinMax(model.getMinRooms(), model.getMaxRooms(), "rooms");
    validateMinMax(model.getMinBathrooms(), model.getMaxBathrooms(), "bathrooms");
    validateMinMax(model.getMinSurfaceCovered(), model.getMaxSurfaceCovered(), "surface covered");
    validateMinMax(model.getMinSurfaceTotal(), model.getMaxSurfaceTotal(), "surface total");
    validateMinMax(model.getMinLon(), model.getMaxLon(), "longitude");
    validateMinMax(model.getMinLat(), model.getMaxLat(), "latitude");

    model.getRooms().ifPresent(ValidationUtils::validateRooms);
    model.getBeds().ifPresent(ValidationUtils::validateBeds);
    model.getBathrooms().ifPresent(ValidationUtils::validateBathrooms);
    model.getPropertyType().ifPresent(ValidationUtils::validatePropertyType);
    model.getPropertyOwnerId().ifPresent(ValidationUtils::validateUserId);
    model.getDistricts()
        .ifPresent(districts -> districts.forEach(ValidationUtils::validateDistrict));
    model.getActive().ifPresent(ValidationUtils::validateActive);

    model.getMinRooms().ifPresent(ValidationUtils::validateRooms);
    model.getMinBeds().ifPresent(ValidationUtils::validateBeds);
    model.getMinBathrooms().ifPresent(ValidationUtils::validateBathrooms);
    model.getMinSurfaceCovered().ifPresent(ValidationUtils::validateSurfaceCovered);
    model.getMinSurfaceTotal().ifPresent(ValidationUtils::validateSurfaceTotal);
    model.getMinPrice().ifPresent(ValidationUtils::validatePrice);
    model.getMinLon().ifPresent(ValidationUtils::validateLongitude);
    model.getMinLat().ifPresent(ValidationUtils::validateLatitude);

    model.getMaxRooms().ifPresent(ValidationUtils::validateRooms);
    model.getMaxBeds().ifPresent(ValidationUtils::validateBeds);
    model.getMaxBathrooms().ifPresent(ValidationUtils::validateBathrooms);
    model.getMaxSurfaceCovered().ifPresent(ValidationUtils::validateSurfaceCovered);
    model.getMaxSurfaceCovered().ifPresent(ValidationUtils::validateSurfaceTotal);
    model.getMaxPrice().ifPresent(ValidationUtils::validatePrice);
    model.getMaxLon().ifPresent(ValidationUtils::validateLongitude);
    model.getMaxLat().ifPresent(ValidationUtils::validateLatitude);

    model.getPage().ifPresent(ValidationUtils::validatePage);
    model.getSortBy().ifPresent(ValidationUtils::validatePropertySortBy);

  }

  private <T extends Number> void validateMinMax(Optional<T> min, Optional<T> max, String field) {
    if (min.isPresent() && max.isPresent() && min.get().doubleValue() > max.get().doubleValue()) {
      throw new BadRequestException(String.format("Min %s must be less than max %s", field, field));
    }
  }
}
