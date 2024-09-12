package com.uade.propertiesbackend.router.property;

import com.uade.propertiesbackend.core.domain.dto.PropertyDto;
import com.uade.propertiesbackend.core.domain.dto.PropertyParametersDTO;
import com.uade.propertiesbackend.core.usecase.RetrieveProperty;
import com.uade.propertiesbackend.router.exception.ApiError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Tag(name = "Properties")
public class RetrievePropertyRouter {

  private final RetrieveProperty retrieveProperty;

  public RetrievePropertyRouter(RetrieveProperty retrieveProperty) {
    this.retrieveProperty = retrieveProperty;
  }

  @Operation(summary = "Retrieve a property by id")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Property retrieved"),
      @ApiResponse(responseCode = "404", description = "Not found", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
      @ApiResponse(responseCode = "424", description = "Failed dependency", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))})})
  @GetMapping("/properties/{propertyId}")
  public ResponseEntity<PropertyDto> get(@PathVariable Long propertyId, @RequestHeader(name = "user_id") Long userId) {
    log.info("Retrieving property with id: {}", propertyId);
    return ResponseEntity.ok(retrieveProperty.apply(PropertyParametersDTO.builder().propertyId(propertyId).userId(userId).build()));
  }
}
