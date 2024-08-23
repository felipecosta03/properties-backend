package com.uade.propertiesbackend.util;

import com.uade.propertiesbackend.core.domain.Property;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public class PropertySpecs {

  public static Specification<Property> withMinPrice(Double minPrice) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"),
        minPrice);
  }

  public static Specification<Property> withMaxPrice(Double maxPrice) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"),
        maxPrice);
  }
}
