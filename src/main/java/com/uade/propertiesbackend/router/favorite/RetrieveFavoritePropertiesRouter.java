package com.uade.propertiesbackend.router.favorite;

import com.uade.propertiesbackend.core.domain.dto.PropertyDto;
import com.uade.propertiesbackend.core.usecase.RetrieveFavoriteProperties;
import com.uade.propertiesbackend.router.exception.ApiError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Favorites", description = "Operations related to favorite properties")
public class RetrieveFavoritePropertiesRouter {

  private final RetrieveFavoriteProperties retrieveFavoriteProperties;

  public RetrieveFavoritePropertiesRouter(RetrieveFavoriteProperties retrieveFavoriteProperties) {
    this.retrieveFavoriteProperties = retrieveFavoriteProperties;
  }

  @Operation(summary = "Retrieve favorite properties")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Favorite properties retrieved"),
      @ApiResponse(responseCode = "400", description = "Bad request", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))})})
  @GetMapping("/properties/favorites")
  public ResponseEntity<Page<PropertyDto>> retrieveFavoriteProperties(@RequestHeader(name = "userId") Long userId,
      @RequestParam Integer page) {
    return ResponseEntity.ok(retrieveFavoriteProperties.apply(
        RetrieveFavoriteProperties.Model.builder().userId(userId).page(page).build()));
  }
}
