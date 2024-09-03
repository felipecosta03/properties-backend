package com.uade.propertiesbackend.router.property;

import com.uade.propertiesbackend.core.domain.PropertySortBy;
import com.uade.propertiesbackend.core.domain.PropertyType;
import com.uade.propertiesbackend.core.domain.dto.PropertyDto;
import com.uade.propertiesbackend.core.usecase.RetrieveProperties;
import com.uade.propertiesbackend.router.exception.ApiError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Properties", description = "Operations related to properties")
public class RetrievePropertiesRouter {

  private final RetrieveProperties retrieveProperties;

  public RetrievePropertiesRouter(RetrieveProperties retrieveProperties) {
    this.retrieveProperties = retrieveProperties;
  }

  @Operation(summary = "Retrieve properties")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Properties retrieved"),
      @ApiResponse(responseCode = "400", description = "Bad request", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))})})
  @GetMapping("/properties")
  public ResponseEntity<Page<PropertyDto>> retrieveProperties(
      @RequestParam(required = false) Optional<Double> minPrice,
      @RequestParam(required = false) Optional<Double> maxPrice,
      @RequestParam(required = false) Optional<Integer> minRooms,
      @RequestParam(required = false) Optional<Integer> maxRooms,
      @RequestParam(required = false) Optional<Integer> rooms,
      @RequestParam(required = false) Optional<Integer> minBeds,
      @RequestParam(required = false) Optional<Integer> maxBeds,
      @RequestParam(required = false) Optional<Integer> beds,
      @RequestParam(required = false) Optional<Integer> minBathrooms,
      @RequestParam(required = false) Optional<Integer> maxBathrooms,
      @RequestParam(required = false) Optional<Integer> bathrooms,
      @RequestParam(required = false) Optional<Double> minSurfaceCovered,
      @RequestParam(required = false) Optional<Double> maxSurfaceCovered,
      @RequestParam(required = false) Optional<Double> minSurfaceTotal,
      @RequestParam(required = false) Optional<Double> maxSurfaceTotal,
      @RequestParam(required = false) Optional<Double> minLat,
      @RequestParam(required = false) Optional<Double> minLon,
      @RequestParam(required = false) Optional<Double> maxLat,
      @RequestParam(required = false) Optional<Double> maxLon,
      @RequestParam(required = false) Optional<PropertyType> propertyType,
      @RequestParam(required = false) Optional<PropertySortBy> sortBy,
      @RequestParam(required = false) Optional<Long> userId,
      @RequestParam(required = false, defaultValue = "0") Optional<Integer> page) {
    return ResponseEntity.ok(retrieveProperties.apply(
        RetrieveProperties.Model.builder()
            .minPrice(minPrice)
            .maxPrice(maxPrice)
            .minRooms(minRooms)
            .maxRooms(maxRooms)
            .rooms(rooms)
            .minBeds(minBeds)
            .maxBeds(maxBeds)
            .beds(beds)
            .minBathrooms(minBathrooms)
            .maxBathrooms(maxBathrooms)
            .bathrooms(bathrooms)
            .minSurfaceCovered(minSurfaceCovered)
            .maxSurfaceCovered(maxSurfaceCovered)
            .minSurfaceTotal(minSurfaceTotal)
            .maxSurfaceTotal(maxSurfaceTotal)
            .minLat(minLat)
            .minLon(minLon)
            .maxLat(maxLat)
            .maxLon(maxLon)
            .propertyType(propertyType)
            .userId(userId)
            .page(page)
            .sortBy(sortBy)
            .build()));
  }
}
