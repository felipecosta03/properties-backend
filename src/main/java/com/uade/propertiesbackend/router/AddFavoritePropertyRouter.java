package com.uade.propertiesbackend.router;

import com.uade.propertiesbackend.core.usecase.AddFavoriteProperty;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Properties", description = "Operations related to properties")
public class AddFavoritePropertyRouter {

  private final AddFavoriteProperty addFavoriteProperty;

  public AddFavoritePropertyRouter(AddFavoriteProperty addFavoriteProperty) {
    this.addFavoriteProperty = addFavoriteProperty;
  }

  @PostMapping("/properties/{propertyId}/favorites")
  public ResponseEntity<Void> addFavoriteProperty(@PathVariable Long propertyId,
      @RequestParam Long userId) {
    addFavoriteProperty.accept(
        AddFavoriteProperty.Model.builder().propertyId(propertyId).userId(userId).build());
    return ResponseEntity.ok().build();
  }
}
