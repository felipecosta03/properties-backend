package com.uade.propertiesbackend.router.property;

import static com.uade.propertiesbackend.util.SecurityUtils.getUserId;

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
      @ApiResponse(responseCode = "200", description = "Property created"),
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
            .bathrooms(propertyRequest.getBathrooms())
            .beds(propertyRequest.getBeds())
            .rooms(propertyRequest.getRooms())
            .district(propertyRequest.getDistrict())
            .images(propertyRequest.getImages())
            .price(propertyRequest.getPrice())
            .latitude(propertyRequest.getLatitude())
            .longitude(propertyRequest.getLongitude())
            .surfaceCovered(propertyRequest.getSurfaceCovered())
            .surfaceTotal(propertyRequest.getSurfaceTotal())
            .userId(getUserId())
            .address(propertyRequest.getAddress())
            .zipcode(propertyRequest.getZipcode())
            .type(propertyRequest.getType())
            .active(propertyRequest.getActive())
            .build()));
  }
}
