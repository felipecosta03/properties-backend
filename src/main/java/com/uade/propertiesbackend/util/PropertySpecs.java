package com.uade.propertiesbackend.util;

import com.uade.propertiesbackend.core.domain.Property;
import com.uade.propertiesbackend.core.domain.PropertyType;
import java.util.List;
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

  public static Specification<Property> withMinRooms(Integer minRooms) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("rooms"),
        minRooms);
  }

  public static Specification<Property> withMaxRooms(Integer maxRooms) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("rooms"),
        maxRooms);
  }

  public static Specification<Property> withRooms(Integer rooms) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("rooms"), rooms);
  }

  public static Specification<Property> withMinBeds(Integer minBeds) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("beds"),
        minBeds);
  }

  public static Specification<Property> withMaxBeds(Integer maxBeds) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("beds"),
        maxBeds);
  }

  public static Specification<Property> withBeds(Integer beds) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("beds"), beds);
  }

  public static Specification<Property> withMinBathrooms(Integer minBathrooms) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(
        root.get("bathrooms"), minBathrooms);
  }

  public static Specification<Property> withMaxBathrooms(Integer maxBathrooms) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(
        root.get("bathrooms"), maxBathrooms);
  }

  public static Specification<Property> withBathrooms(Integer bathrooms) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("bathrooms"),
        bathrooms);
  }

  public static Specification<Property> withMinSurfaceCovered(Double minSurfaceCovered) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(
        root.get("surfaceCovered"), minSurfaceCovered);
  }

  public static Specification<Property> withMaxSurfaceCovered(Double maxSurfaceCovered) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(
        root.get("surfaceCovered"), maxSurfaceCovered);
  }

  public static Specification<Property> withMinSurfaceTotal(Double minSurfaceTotal) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(
        root.get("surfaceTotal"), minSurfaceTotal);
  }

  public static Specification<Property> withMaxSurfaceTotal(Double maxSurfaceTotal) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(
        root.get("surfaceTotal"), maxSurfaceTotal);
  }

  public static Specification<Property> withPropertyType(PropertyType propertyType) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("type"), propertyType);
  }

  public static Specification<Property> withActive(Boolean active) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("active"), active);
  }

  public static Specification<Property> withUserId(Long userId) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("userId"), userId);
  }

  public static Specification<Property> withDistricts(List<String> districts) {
    return (root, query, criteriaBuilder) -> root.get("district").in(districts);
  }
}
