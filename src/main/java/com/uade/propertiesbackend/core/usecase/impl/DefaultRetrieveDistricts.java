package com.uade.propertiesbackend.core.usecase.impl;

import com.uade.propertiesbackend.core.usecase.RetrieveDistricts;
import com.uade.propertiesbackend.repository.PropertyRepository;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class DefaultRetrieveDistricts implements RetrieveDistricts {

  private final PropertyRepository propertyRepository;

  public DefaultRetrieveDistricts(PropertyRepository propertyRepository) {
    this.propertyRepository = propertyRepository;
  }

  @Override
  public List<String> get() {
    return propertyRepository.retrieveDistricts();
  }
}
