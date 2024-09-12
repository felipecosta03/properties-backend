package com.uade.propertiesbackend.core.usecase.impl;

import com.uade.propertiesbackend.core.usecase.PropertyIsDisable;
import com.uade.propertiesbackend.repository.RentProcessRepository;
import org.springframework.stereotype.Component;

@Component
public class DefaultPropertyIsDisable implements PropertyIsDisable {
  final RentProcessRepository rentProcessRepository;

  public DefaultPropertyIsDisable(RentProcessRepository rentProcessRepository) {
    this.rentProcessRepository = rentProcessRepository;
  }

  @Override
  public boolean test(Model model) {
    return this.isOwner(model.getPropertyUserId(), model.getUserId()) || this.userHasRentProcess(model.getUserId(), model.getPropertyId());
  }

  private boolean isOwner(Long propertyUserId, Long userId) {
    return propertyUserId.equals(userId);
  }

  private boolean userHasRentProcess(Long userId, Long propertyId) {
    return this.rentProcessRepository.findByPropertyIdAndTenantIdAndStatusNotRejected(propertyId, userId).stream().findAny().isPresent();
  }
}
