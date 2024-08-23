package com.uade.propertiesbackend.router;

import com.uade.propertiesbackend.core.domain.dto.PropertyDto;
import com.uade.propertiesbackend.core.usecase.CreateProperty;
import com.uade.propertiesbackend.router.exception.ApiError;
import com.uade.propertiesbackend.router.request.PropertyRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Properties", description = "Operations related to properties")
public class CreatePropertyRouter {

  private final CreateProperty createProperty;

  public CreatePropertyRouter(CreateProperty createProperty) {
    this.createProperty = createProperty;
  }

  @Operation(summary = "Create a property")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Property created", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = PropertyDto.class))}),
      @ApiResponse(responseCode = "400", description = "Bad request", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
      @ApiResponse(responseCode = "424", description = "Failed dependency", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))})})
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
            .surfaceCovered(propertyRequest.getSurfaceCovered())
            .surfaceTotal(propertyRequest.getSurfaceTotal())
            .userId(propertyRequest.getUserId())
            .street(propertyRequest.getStreet())
            .garages(propertyRequest.getGarages())
            .type(propertyRequest.getType())
            .build()));
  }
}
