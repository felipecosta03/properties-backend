package com.uade.propertiesbackend.router;

import com.uade.propertiesbackend.core.domain.dto.PropertyDto;
import com.uade.propertiesbackend.core.usecase.RetrieveProperties;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RetrievePropertiesRouter {

  private final RetrieveProperties retrieveProperties;

  public RetrievePropertiesRouter(RetrieveProperties retrieveProperties) {
    this.retrieveProperties = retrieveProperties;
  }

  @GetMapping("/properties")
  public ResponseEntity<Page<PropertyDto>> retrieveProperties(
      @RequestParam(required = false) Optional<Double> minPrice,
      @RequestParam(required = false) Optional<Double> maxPrice,
      @RequestParam(required = false, defaultValue = "0") Optional<Integer> page) {
    return ResponseEntity.ok(retrieveProperties.apply(
        RetrieveProperties.Model.builder()
            .minPrice(minPrice)
            .maxPrice(maxPrice)
            .page(page)
            .build()));
  }
}
