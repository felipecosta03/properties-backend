package com.uade.propertiesbackend.router.favorite;

import static com.uade.propertiesbackend.util.SecurityUtils.getUserId;

import com.uade.propertiesbackend.core.usecase.AddFavoriteProperty;
import com.uade.propertiesbackend.router.exception.ApiError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Favorites", description = "Operations related to favorite properties")
public class AddFavoritePropertyRouter {

  private final AddFavoriteProperty addFavoriteProperty;

  public AddFavoritePropertyRouter(AddFavoriteProperty addFavoriteProperty) {
    this.addFavoriteProperty = addFavoriteProperty;
  }

  @Operation(summary = "Add favorite property")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Added favorite property"),
      @ApiResponse(responseCode = "400", description = "Bad request", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
      @ApiResponse(responseCode = "404", description = "Not found", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
      @ApiResponse(responseCode = "424", description = "Failed dependency", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))})})
  @PostMapping("/properties/{propertyId}/favorites")
  public ResponseEntity<Void> addFavoriteProperty(@PathVariable Long propertyId) {
    addFavoriteProperty.accept(
        AddFavoriteProperty.Model.builder().propertyId(propertyId).userId(getUserId()).build());
    return ResponseEntity.noContent().build();
  }
}
