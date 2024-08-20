package com.uade.propertiesbackend.router;

import com.uade.propertiesbackend.core.usecase.DeleteProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeletePropertyRouter {

  private static final Logger log = LoggerFactory.getLogger(DeletePropertyRouter.class);
  private final DeleteProperty deleteProperty;

  public DeletePropertyRouter(DeleteProperty deleteProperty) {
    this.deleteProperty = deleteProperty;
  }

  @DeleteMapping("/properties/{propertyId}")
  public ResponseEntity<Void> delete(@PathVariable Long propertyId) {
    log.info("Deleting property with id: {}", propertyId);
    deleteProperty.accept(propertyId);
    return ResponseEntity.noContent().build();
  }
}
