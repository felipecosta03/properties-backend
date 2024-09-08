package com.uade.propertiesbackend.router.rent;

import com.uade.propertiesbackend.core.domain.Role;
import com.uade.propertiesbackend.core.domain.dto.RentalsDto;
import com.uade.propertiesbackend.core.usecase.RetrieveRentalsByUserId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RetrieveRentalsByUserIdRouter {

  private final RetrieveRentalsByUserId retrieveRentalsByUserId;

  public RetrieveRentalsByUserIdRouter(RetrieveRentalsByUserId retrieveRentalsByUserId) {
    this.retrieveRentalsByUserId = retrieveRentalsByUserId;
  }

  @GetMapping("/rentals/{userId}")
  public ResponseEntity<RentalsDto> retrieveRentalsByUserId(@PathVariable Long userId,
      @RequestParam Role role) {

    return ResponseEntity.ok(retrieveRentalsByUserId.apply(
        RetrieveRentalsByUserId.Model.builder().role(role).userId(userId).build()));
  }

}
