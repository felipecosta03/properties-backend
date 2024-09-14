package com.uade.propertiesbackend.router.rent;

import com.uade.propertiesbackend.core.domain.Role;
import com.uade.propertiesbackend.core.domain.dto.RentalsDto;
import com.uade.propertiesbackend.core.usecase.RetrieveRentalsByUserId;
import com.uade.propertiesbackend.router.exception.ApiError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Rent", description = "Operations related to rentals")
public class RetrieveRentalsByUserIdRouter {

  private final RetrieveRentalsByUserId retrieveRentalsByUserId;

  public RetrieveRentalsByUserIdRouter(RetrieveRentalsByUserId retrieveRentalsByUserId) {
    this.retrieveRentalsByUserId = retrieveRentalsByUserId;
  }

  @Operation(summary = "Retrieve rentals by user id")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Rentals retrieved"),
      @ApiResponse(responseCode = "400", description = "Bad request", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
      @ApiResponse(responseCode = "424", description = "Failed dependency", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))})})
  @GetMapping("/rentals")
  public ResponseEntity<RentalsDto> retrieveRentalsByUserId(@RequestHeader(name = "userId") Long userId,
      @RequestParam Role role) {

    return ResponseEntity.ok(retrieveRentalsByUserId.apply(
        RetrieveRentalsByUserId.Model.builder().role(role).userId(userId).build()));
  }

}
