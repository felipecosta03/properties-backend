package com.uade.propertiesbackend.router;

import com.uade.propertiesbackend.core.domain.dto.PricePredictDto;
import com.uade.propertiesbackend.core.usecase.PredictPropertyPrice;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Predict", description = "Predict the price of a property")
public class PredictPropertyPriceRouter {

  private final PredictPropertyPrice predictPropertyPrice;

  public PredictPropertyPriceRouter(PredictPropertyPrice predictPropertyPrice) {
    this.predictPropertyPrice = predictPropertyPrice;
  }

  @GetMapping("/predict/{propertyId}")
  @Operation(summary = "Predict the price of a property by its id")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Price predicted"),
      @ApiResponse(responseCode = "424", description = "Failed dependency", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))})})
  public ResponseEntity<PricePredictDto> predictPrice(@PathVariable Long propertyId) {
    return ResponseEntity.ok(predictPropertyPrice.apply(propertyId));
  }

}
