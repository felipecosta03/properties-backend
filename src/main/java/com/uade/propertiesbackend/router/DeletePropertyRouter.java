package com.uade.propertiesbackend.router;

import com.uade.propertiesbackend.core.usecase.DeleteProperty;
import com.uade.propertiesbackend.router.exception.ApiError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Tag(name = "Properties")
public class DeletePropertyRouter {

  private final DeleteProperty deleteProperty;

  public DeletePropertyRouter(DeleteProperty deleteProperty) {
    this.deleteProperty = deleteProperty;
  }

  @Operation(summary = "Delete a property by id")
  @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Property deleted"),
      @ApiResponse(responseCode = "400", description = "Bad request", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
      @ApiResponse(responseCode = "424", description = "Failed dependency", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))})})
  @DeleteMapping("/properties/{propertyId}")
  public ResponseEntity<Void> delete(@PathVariable Long propertyId) {
    log.info("Deleting property with id: {}", propertyId);
    deleteProperty.accept(propertyId);
    return ResponseEntity.noContent().build();
  }
}
