package com.uade.propertiesbackend.router;

import com.uade.propertiesbackend.core.usecase.RetrieveDistricts;
import com.uade.propertiesbackend.router.exception.ApiError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Extra", description = "Retrieve all the districts")
public class RetrieveDistrictsRouter {

  private final RetrieveDistricts retrieveDistricts;

  public RetrieveDistrictsRouter(RetrieveDistricts retrieveDistricts) {
    this.retrieveDistricts = retrieveDistricts;
  }

  @GetMapping("/districts")
  @Operation(summary = "Retrieve all the districts")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Districts retrieved"),
      @ApiResponse(responseCode = "424", description = "Failed dependency", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))})})
  public ResponseEntity<List<String>> retrieveDistricts() {
    return ResponseEntity.ok(retrieveDistricts.get());
  }
}
