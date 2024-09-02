package com.uade.propertiesbackend.router.rent;

import com.uade.propertiesbackend.core.usecase.CreateRentProcess;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Rent Process", description = "Operations related to rent process")
public class CreateRentProcessRouter {

  private final CreateRentProcess createRentProcess;

  public CreateRentProcessRouter(CreateRentProcess createRentProcess) {
    this.createRentProcess = createRentProcess;
  }

  @Operation(summary = "Create a rent process")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Rent process created"),
          @ApiResponse(responseCode = "400", description = "Bad request", content = {
                  @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
          @ApiResponse(responseCode = "424", description = "Failed dependency", content = {
                  @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))})})
  @PostMapping("/rent-process/{propertyId}")
  public ResponseEntity<Void> createRentProcess(@PathVariable Long propertyId,
      @RequestParam Long userId) {
    createRentProcess.accept(
        CreateRentProcess.Model.builder().tenantId(userId).propertyId(propertyId).build());
    return ResponseEntity.noContent().build();
  }
}
