package com.uade.propertiesbackend.router;

import com.uade.propertiesbackend.core.domain.dto.PropertyDto;
import com.uade.propertiesbackend.core.usecase.UpdateProperty;
import com.uade.propertiesbackend.router.exception.ApiError;
import com.uade.propertiesbackend.router.request.PropertyRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Properties", description = "Operations related to properties")
@Slf4j
public class UpdatePropertyRouter {

  private final UpdateProperty updateProperty;

  public UpdatePropertyRouter(UpdateProperty updateProperty) {
    this.updateProperty = updateProperty;
  }

  @Operation(summary = "Update property")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Property updated", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = PropertyDto.class))}),
      @ApiResponse(responseCode = "400", description = "Bad request", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
      @ApiResponse(responseCode = "404", description = "Not found", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
      @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
      @ApiResponse(responseCode = "424", description = "Failed dependency", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))})})
  @PutMapping("/properties/{propertyId}")
  public ResponseEntity<PropertyDto> update(@PathVariable Long propertyId,
      @RequestBody PropertyRequest propertyRequest) {
    log.info("Updating property with id: {}", propertyId);

    UpdateProperty.Model model =
        UpdateProperty.Model.builder().id(propertyId)
            .title(propertyRequest.getTitle())
            .description(propertyRequest.getDescription())
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
            .address(propertyRequest.getAddress())
            .garages(propertyRequest.getGarages())
            .type(propertyRequest.getType())
            .build();

    return ResponseEntity.ok(updateProperty.apply(model));
  }
}
