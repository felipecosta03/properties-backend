package com.uade.propertiesbackend.router.rent;

import com.uade.propertiesbackend.core.usecase.CreateRentProcess;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateRentProcessRouter {

  private final CreateRentProcess createRentProcess;

  public CreateRentProcessRouter(CreateRentProcess createRentProcess) {
    this.createRentProcess = createRentProcess;
  }

  @PostMapping("/rents/{propertyId}")
  public ResponseEntity<Void> createRentProcess(@PathVariable Long propertyId,
      @RequestParam Long userId) {
    createRentProcess.accept(
        CreateRentProcess.Model.builder().userId(userId).propertyId(propertyId).build());
    return ResponseEntity.noContent().build();
  }
}
