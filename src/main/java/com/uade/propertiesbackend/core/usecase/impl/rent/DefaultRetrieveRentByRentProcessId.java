package com.uade.propertiesbackend.core.usecase.impl.rent;

import com.uade.propertiesbackend.core.domain.Rent;
import com.uade.propertiesbackend.core.exception.NotFoundException;
import com.uade.propertiesbackend.core.usecase.RetrieveRentByRentProcessId;
import com.uade.propertiesbackend.repository.RentRepository;
import org.springframework.stereotype.Component;

@Component
public class DefaultRetrieveRentByRentProcessId implements RetrieveRentByRentProcessId {


  private final RentRepository rentRepository;

  public DefaultRetrieveRentByRentProcessId(RentRepository rentRepository) {
    this.rentRepository = rentRepository;
  }

  @Override
  public Rent apply(Long rentProcessId) {
    return rentRepository.getRentByRentProcessId(rentProcessId)
        .orElseThrow(() -> new NotFoundException("Rent not found"));
  }
}
