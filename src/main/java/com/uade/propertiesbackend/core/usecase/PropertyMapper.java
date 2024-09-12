package com.uade.propertiesbackend.core.usecase;

import com.uade.propertiesbackend.core.domain.Property;
import com.uade.propertiesbackend.core.domain.dto.PropertyDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PropertyMapper {

  PropertyMapper INSTANCE = Mappers.getMapper(PropertyMapper.class);

  PropertyDto propertyToPropertyDto(Property car);

  @Mapping(target = "favorite", source = "isFavorite")
  PropertyDto propertyToPropertyDto(Property car, boolean isFavorite);
}
