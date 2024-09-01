package com.uade.propertiesbackend.core.usecase;

import com.uade.propertiesbackend.core.domain.Property;
import com.uade.propertiesbackend.core.domain.dto.PropertyDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-01T11:47:20-0300",
    comments = "version: 1.5.0.RC1, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
public class PropertyMapperImpl implements PropertyMapper {

    @Override
    public PropertyDto propertyToPropertyDto(Property car) {
        if ( car == null ) {
            return null;
        }

        PropertyDto.PropertyDtoBuilder propertyDto = PropertyDto.builder();

        propertyDto.id( car.getId() );
        propertyDto.beds( car.getBeds() );
        propertyDto.bathrooms( car.getBathrooms() );
        propertyDto.country( car.getCountry() );
        propertyDto.city( car.getCity() );
        propertyDto.state( car.getState() );
        propertyDto.rooms( car.getRooms() );
        propertyDto.surfaceCovered( car.getSurfaceCovered() );
        propertyDto.surfaceTotal( car.getSurfaceTotal() );
        propertyDto.title( car.getTitle() );
        propertyDto.description( car.getDescription() );
        propertyDto.latitude( car.getLatitude() );
        propertyDto.longitude( car.getLongitude() );
        List<String> list = car.getImages();
        if ( list != null ) {
            propertyDto.images( new ArrayList<String>( list ) );
        }
        propertyDto.userId( car.getUserId() );
        propertyDto.address( car.getAddress() );
        propertyDto.storeys( car.getStoreys() );
        propertyDto.price( car.getPrice() );
        propertyDto.garages( car.getGarages() );
        propertyDto.type( car.getType() );
        propertyDto.createdAt( car.getCreatedAt() );
        propertyDto.active( car.isActive() );

        return propertyDto.build();
    }
}
