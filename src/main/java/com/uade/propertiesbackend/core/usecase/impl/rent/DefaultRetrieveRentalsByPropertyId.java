package com.uade.propertiesbackend.core.usecase.impl.rent;

import static com.uade.propertiesbackend.util.ValidationUtils.validatePropertyId;

import com.uade.propertiesbackend.core.domain.Rent;
import com.uade.propertiesbackend.core.usecase.RetrieveRentalsByPropertyId;
import com.uade.propertiesbackend.repository.RentRepository;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class DefaultRetrieveRentalsByPropertyId implements RetrieveRentalsByPropertyId {

  private final RentRepository rentRepository;

  public DefaultRetrieveRentalsByPropertyId(RentRepository rentRepository) {
    this.rentRepository = rentRepository;
  }

  @Override
  public List<Rent> apply(Long propertyId) {
    validatePropertyId(propertyId);
    return rentRepository.getRentsByPropertyId(propertyId);
  }
}
