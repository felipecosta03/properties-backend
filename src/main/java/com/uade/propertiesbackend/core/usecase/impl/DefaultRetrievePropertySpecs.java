package com.uade.propertiesbackend.core.usecase.impl;

import com.uade.propertiesbackend.core.domain.Property;
import com.uade.propertiesbackend.core.usecase.RetrievePropertySpecs;
import com.uade.propertiesbackend.util.PropertySpecs;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class DefaultRetrievePropertySpecs implements RetrievePropertySpecs {

  @Override
  public Specification<Property> apply(Model model) {

    return Specification
        .where(model.getMinPrice().map(PropertySpecs::withMinPrice).orElse(null))
        .and(model.getMaxPrice().map(PropertySpecs::withMaxPrice).orElse(null));
  }
}
