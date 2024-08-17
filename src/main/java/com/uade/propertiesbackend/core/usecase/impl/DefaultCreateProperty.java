package com.uade.propertiesbackend.core.usecase.impl;

import static java.util.Objects.isNull;

import com.uade.propertiesbackend.core.domain.dto.PropertyDto;
import com.uade.propertiesbackend.core.usecase.CreateProperty;
import com.uade.propertiesbackend.repository.CreatePropertyRepository;
import org.springframework.stereotype.Component;

/**
 * Use case for create property
 */
@Component
public class DefaultCreateProperty implements CreateProperty {

  private final CreatePropertyRepository createPropertyRepository;

  public DefaultCreateProperty(CreatePropertyRepository createPropertyRepository) {
    this.createPropertyRepository = createPropertyRepository;
  }

  @Override
  public PropertyDto apply(Model model) {
    validateModel(model);
    return null;
  }

  private void validateModel(Model model) {
    if (isNull(model)){

    }
  }
}
