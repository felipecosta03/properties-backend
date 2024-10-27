package com.uade.propertiesbackend.router.property;

import static com.uade.propertiesbackend.util.SecurityUtils.getUserId;

import com.uade.propertiesbackend.core.domain.dto.PropertyDto;
import com.uade.propertiesbackend.core.usecase.PartialUpdateProperty;
import com.uade.propertiesbackend.router.exception.ApiError;
import com.uade.propertiesbackend.router.request.PartialPropertyRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Properties", description = "Operations related to properties")
@Slf4j
public class PartialUpdatePropertyRouter {

  private final PartialUpdateProperty partialUpdateProperty;

  public PartialUpdatePropertyRouter(PartialUpdateProperty partialUpdateProperty) {
    this.partialUpdateProperty = partialUpdateProperty;
  }

  @Operation(summary = "Update property")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Property updated"),
      @ApiResponse(responseCode = "400", description = "Bad request", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
      @ApiResponse(responseCode = "404", description = "Not found", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
      @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
      @ApiResponse(responseCode = "424", description = "Failed dependency", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))})})
  @PatchMapping("/properties/{propertyId}")
  public ResponseEntity<PropertyDto> update(@PathVariable Long propertyId,
      @RequestBody PartialPropertyRequest partialPropertyRequest) {
    log.info("Updating property with id: {}", propertyId);

    PartialUpdateProperty.Model model =
        PartialUpdateProperty.Model.builder().id(propertyId)
            .title(Optional.ofNullable(partialPropertyRequest.getTitle()))
            .description(Optional.ofNullable(partialPropertyRequest.getDescription()))
            .bathrooms(Optional.ofNullable(partialPropertyRequest.getBathrooms()))
            .beds(Optional.ofNullable(partialPropertyRequest.getBeds()))
            .rooms(Optional.ofNullable(partialPropertyRequest.getRooms()))
            .district(Optional.ofNullable(partialPropertyRequest.getDistrict()))
            .images(Optional.ofNullable(partialPropertyRequest.getImages()))
            .price(Optional.ofNullable(partialPropertyRequest.getPrice()))
            .latitude(Optional.ofNullable(partialPropertyRequest.getLatitude()))
            .longitude(Optional.ofNullable(partialPropertyRequest.getLongitude()))
            .surfaceCovered(Optional.ofNullable(partialPropertyRequest.getSurfaceCovered()))
            .surfaceTotal(Optional.ofNullable(partialPropertyRequest.getSurfaceTotal()))
            .userId(getUserId())
            .zipcode(Optional.ofNullable(partialPropertyRequest.getZipcode()))
            .address(Optional.ofNullable(partialPropertyRequest.getAddress()))
            .type(Optional.ofNullable(partialPropertyRequest.getType()))
            .active(Optional.ofNullable(partialPropertyRequest.getActive()))
            .build();

    PropertyDto propertyDto = partialUpdateProperty.apply(model);

    return ResponseEntity.ok(propertyDto);
  }
}
