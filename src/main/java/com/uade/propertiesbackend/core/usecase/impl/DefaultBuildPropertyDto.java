package com.uade.propertiesbackend.core.usecase.impl;

import com.uade.propertiesbackend.core.domain.Property;
import com.uade.propertiesbackend.core.domain.dto.PropertyDto;
import com.uade.propertiesbackend.core.usecase.BuildPropertyDto;
import org.springframework.stereotype.Component;

@Component
public class DefaultBuildPropertyDto implements BuildPropertyDto {

  @Override
  public PropertyDto apply(Property property) {
    return PropertyDto.builder()
        .id(property.getId())
        .title(property.getTitle())
        .description(property.getDescription())
        .price(property.getPrice())
        .streetNumber(property.getStreetNumber())
        .bathrooms(property.getBathrooms())
        .beds(property.getBeds())
        .street(property.getStreet())
        .rooms(property.getRooms())
        .state(property.getState())
        .images(property.getImages())
        .city(property.getCity())
        .country(property.getCountry())
        .latitude(property.getLatitude())
        .longitude(property.getLongitude())
        .storeys(property.getStoreys())
        .surface(property.getSurface())
        .userId(property.getUserId())
        .build();
  }
}
