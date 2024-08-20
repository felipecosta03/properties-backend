package com.uade.propertiesbackend.router;

import com.uade.propertiesbackend.core.domain.dto.PropertyDto;
import com.uade.propertiesbackend.core.usecase.RetrieveProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RetrievePropertyRouter {

  private final RetrieveProperty retrieveProperty;

  public RetrievePropertyRouter(RetrieveProperty retrieveProperty) {
    this.retrieveProperty = retrieveProperty;
  }

  @GetMapping("/properties/{propertyId}")
  public ResponseEntity<PropertyDto> get(@PathVariable Long propertyId) {
    log.info("Retrieving property with id: {}", propertyId);
    return ResponseEntity.ok(retrieveProperty.apply(propertyId));
  }
}
