package com.uade.propertiesbackend.core.usecase.impl.rent;

import com.uade.propertiesbackend.core.domain.Rent;
import com.uade.propertiesbackend.core.domain.RentStatus;
import com.uade.propertiesbackend.core.usecase.HasPropertyCurrentRent;
import com.uade.propertiesbackend.core.usecase.RetrieveRentalsByPropertyId;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class DefaultHasPropertyCurrentRent implements HasPropertyCurrentRent {

  private static final List<RentStatus> FINISHED_RENT_STATUSES = List.of(RentStatus.CANCELLED,
      RentStatus.COMPLETED);

  private final RetrieveRentalsByPropertyId retrieveRentalsByPropertyId;

  public DefaultHasPropertyCurrentRent(RetrieveRentalsByPropertyId retrieveRentalsByPropertyId) {
    this.retrieveRentalsByPropertyId = retrieveRentalsByPropertyId;
  }

  @Override
  public boolean test(Long propertyId) {
    List<Rent> rentList = retrieveRentalsByPropertyId.apply(propertyId);
    return !rentList.stream().allMatch(rent -> FINISHED_RENT_STATUSES.contains(rent.getStatus()));
  }
}
