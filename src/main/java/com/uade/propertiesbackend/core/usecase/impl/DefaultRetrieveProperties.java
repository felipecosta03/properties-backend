package com.uade.propertiesbackend.core.usecase.impl;

import static com.uade.propertiesbackend.util.ValidationUtils.validateMinPrice;
import static java.util.Objects.isNull;

import com.uade.propertiesbackend.core.domain.Property;
import com.uade.propertiesbackend.core.domain.dto.PropertyDto;
import com.uade.propertiesbackend.core.exception.BadRequestException;
import com.uade.propertiesbackend.core.usecase.PropertyMapper;
import com.uade.propertiesbackend.core.usecase.RetrieveProperties;
import com.uade.propertiesbackend.core.usecase.RetrievePropertySpecs;
import com.uade.propertiesbackend.repository.PropertyRepository;
import com.uade.propertiesbackend.util.ValidationUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class DefaultRetrieveProperties implements RetrieveProperties {

  private final PropertyRepository propertyRepository;
  private final RetrievePropertySpecs retrievePropertySpecs;

  public DefaultRetrieveProperties(PropertyRepository propertyRepository,
      RetrievePropertySpecs retrievePropertySpecs) {
    this.propertyRepository = propertyRepository;
    this.retrievePropertySpecs = retrievePropertySpecs;
  }

  @Override
  public Page<PropertyDto> apply(Model model) {
    validateModel(model);

    Specification<Property> specification = retrievePropertySpecs.apply(
        RetrievePropertySpecs.Model.builder()
            .minPrice(model.getMinPrice())
            .maxPrice(model.getMaxPrice())
            .build());
    return propertyRepository
        .findAll(specification, PageRequest.of(model.getPage().get(), 10))
        .map(PropertyMapper.INSTANCE::propertyToPropertyDto);
  }

  private void validateModel(Model model) {
    if (isNull(model)) {
      throw new BadRequestException("Model is required");
    }
    if (model.getMinPrice().isPresent()) {
      validateMinPrice(model.getMinPrice().get());
      if (model.getMaxPrice().isPresent() && model.getMinPrice().get() > model.getMaxPrice().get()) {
        throw new BadRequestException("Min price must be less than max price");
      }
    }
    model.getMaxPrice().ifPresent(ValidationUtils::validatePrice);
    model.getPage().ifPresent(ValidationUtils::validatePage);

  }
}
