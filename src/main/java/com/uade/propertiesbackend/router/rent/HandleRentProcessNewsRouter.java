package com.uade.propertiesbackend.router.rent;

import com.uade.propertiesbackend.core.usecase.HandleRentProcessNews;
import com.uade.propertiesbackend.router.exception.ApiError;
import com.uade.propertiesbackend.router.request.RentProcessNews;
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
@Tag(name = "Rent Process", description = "Operations related to rent process")
public class HandleRentProcessNewsRouter {

  private final HandleRentProcessNews handleRentProcessNews;

  public HandleRentProcessNewsRouter(HandleRentProcessNews handleRentProcessNews) {
    this.handleRentProcessNews = handleRentProcessNews;
  }
  @Operation(summary = "Handle rent process news")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Property created"),
          @ApiResponse(responseCode = "400", description = "Bad request", content = {
                  @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
          @ApiResponse(responseCode = "424", description = "Failed dependency", content = {
                  @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))})})
  @PostMapping("/rent-process/news")
  public ResponseEntity<Void> handleRentProcessNews(
      @RequestBody final RentProcessNews rentProcessNews) {
    handleRentProcessNews.accept(
        HandleRentProcessNews.Model.builder().rentProcessId(rentProcessNews.getRentProcessId())
            .status(rentProcessNews.getStatus()).build());
    return ResponseEntity.ok().build();
  }
}
