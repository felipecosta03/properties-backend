package com.uade.propertiesbackend.core.usecase;

import com.uade.propertiesbackend.core.domain.Property;
import com.uade.propertiesbackend.core.domain.dto.PropertyDetailsDTO;
import com.uade.propertiesbackend.core.domain.dto.PropertyDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PropertyMapper {

  PropertyMapper INSTANCE = Mappers.getMapper(PropertyMapper.class);

  PropertyDto propertyToPropertyDto(Property property);

  @Mapping(target = "favorite", source = "isFavorite")
  @Mapping(target = "rented", source = "isRented")
  @Mapping(target = "deleteEnabled", source = "deleteEnabled")
  PropertyDto propertyToPropertyDto(Property property, boolean isFavorite, boolean isRented,
      boolean deleteEnabled);

  @Mapping(target = "rented", source = "isRented")
  PropertyDto propertyToPropertyDto(Property property, boolean isRented);

  @Mapping(target = "favorite", source = "isFavorite")
  @Mapping(target = "disable", source = "isDisable")
  PropertyDetailsDTO propertyToPropertyDetailsDTO(Property property, boolean isFavorite,
      boolean isDisable);
}
