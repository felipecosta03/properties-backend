package com.uade.propertiesbackend.router;

import com.uade.propertiesbackend.core.domain.dto.PropertyDto;
import com.uade.propertiesbackend.core.usecase.CreateProperty;
import com.uade.propertiesbackend.router.request.PropertyRequest;
import jakarta.validation.Valid;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreatePropertyRouter {

  private final CreateProperty createProperty;

  public CreatePropertyRouter(CreateProperty createProperty) {
    this.createProperty = createProperty;
  }

  @PostMapping("/properties")
  public ResponseEntity<PropertyDto> createProperty(
      @RequestBody PropertyRequest propertyRequest) {
    return ResponseEntity.ok(createProperty.apply(
         CreateProperty.Model.builder().title(propertyRequest.getTitle())
            .description(propertyRequest.getDescription())
            .streetNumber(propertyRequest.getStreetNumber())
            .bathrooms(propertyRequest.getBathrooms())
            .beds(propertyRequest.getBeds())
            .city(propertyRequest.getCity())
            .rooms(propertyRequest.getRooms())
            .country(propertyRequest.getCountry())
            .images(propertyRequest.getImages())
            .price(propertyRequest.getPrice())
            .state(propertyRequest.getState())
            .latitude(propertyRequest.getLatitude())
            .longitude(propertyRequest.getLongitude())
            .storeys(propertyRequest.getStoreys())
            .surface(propertyRequest.getSurface())
            .userId(propertyRequest.getUserId())
            .street(propertyRequest.getStreet())
            .build()));
  }
}
