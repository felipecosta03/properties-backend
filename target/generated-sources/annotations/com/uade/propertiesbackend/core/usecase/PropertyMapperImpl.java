package com.uade.propertiesbackend.core.usecase;

import com.uade.propertiesbackend.core.domain.Property;
import com.uade.propertiesbackend.core.domain.dto.PropertyDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-29T12:37:33-0300",
    comments = "version: 1.5.0.RC1, compiler: Eclipse JDT (IDE) 3.39.0.v20240725-1906, environment: Java 17.0.11 (Eclipse Adoptium)"
)
public class PropertyMapperImpl implements PropertyMapper {

    @Override
    public PropertyDto propertyToPropertyDto(Property car) {
        if ( car == null ) {
            return null;
        }

        PropertyDto.PropertyDtoBuilder propertyDto = PropertyDto.builder();

        propertyDto.active( car.isActive() );
        propertyDto.address( car.getAddress() );
        propertyDto.bathrooms( car.getBathrooms() );
        propertyDto.beds( car.getBeds() );
        propertyDto.city( car.getCity() );
        propertyDto.country( car.getCountry() );
        propertyDto.createdAt( car.getCreatedAt() );
        propertyDto.description( car.getDescription() );
        propertyDto.garages( car.getGarages() );
        propertyDto.id( car.getId() );
        List<String> list = car.getImages();
        if ( list != null ) {
            propertyDto.images( new ArrayList<String>( list ) );
        }
        propertyDto.latitude( car.getLatitude() );
        propertyDto.longitude( car.getLongitude() );
        propertyDto.price( car.getPrice() );
        propertyDto.rooms( car.getRooms() );
        propertyDto.state( car.getState() );
        propertyDto.storeys( car.getStoreys() );
        propertyDto.surfaceCovered( car.getSurfaceCovered() );
        propertyDto.surfaceTotal( car.getSurfaceTotal() );
        propertyDto.title( car.getTitle() );
        propertyDto.type( car.getType() );
        propertyDto.userId( car.getUserId() );

        return propertyDto.build();
    }
}
