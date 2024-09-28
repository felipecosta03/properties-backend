package com.uade.propertiesbackend.router.rent;

import com.uade.propertiesbackend.core.usecase.HandleRentNews;
import com.uade.propertiesbackend.router.exception.ApiError;
import com.uade.propertiesbackend.router.request.RentNews;
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
@Tag(name = "Rent", description = "Operations related to rents")
public class HandleRentNewsRouter {

  private final HandleRentNews handleRentNews;

  public HandleRentNewsRouter(HandleRentNews handleRentNews) {
    this.handleRentNews = handleRentNews;
  }

  @Operation(summary = "Handle rent news")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Rent status updated"),
      @ApiResponse(responseCode = "400", description = "Bad request", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
      @ApiResponse(responseCode = "404", description = "Not found", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
      @ApiResponse(responseCode = "424", description = "Failed dependency", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))})})
  @PostMapping("/rent-process/news")
  public ResponseEntity<Void> handleRentNews(@RequestBody final RentNews rentNews) {
    handleRentNews.accept(
        HandleRentNews.Model.builder().rentId(rentNews.getRentId()).status(rentNews.getStatus())
            .build());
    return ResponseEntity.ok().build();
  }
}
