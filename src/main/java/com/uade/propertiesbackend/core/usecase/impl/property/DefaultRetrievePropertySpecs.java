package com.uade.propertiesbackend.core.usecase.impl.property;

import static com.uade.propertiesbackend.util.PropertySpecs.withActive;

import static com.uade.propertiesbackend.util.PropertySpecs.withActive;

import com.uade.propertiesbackend.core.domain.Property;
import com.uade.propertiesbackend.core.usecase.RetrievePropertySpecs;
import com.uade.propertiesbackend.util.PropertySpecs;
import java.util.Optional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class DefaultRetrievePropertySpecs implements RetrievePropertySpecs {

  @Override
  public Specification<Property> apply(Model model) {

    return Specification
        .where(withActive())
        .and(model.getMinPrice().map(PropertySpecs::withMinPrice).orElse(null))
        .and(model.getMaxPrice().map(PropertySpecs::withMaxPrice).orElse(null))
        .and(model.getMaxSurfaceCovered().map(PropertySpecs::withMaxSurfaceCovered).orElse(null))
        .and(model.getMinSurfaceCovered().map(PropertySpecs::withMinSurfaceCovered).orElse(null))
        .and(model.getMinSurfaceTotal().map(PropertySpecs::withMinSurfaceTotal).orElse(null))
        .and(model.getMaxSurfaceTotal().map(PropertySpecs::withMaxSurfaceTotal).orElse(null))
        .and(model.getPropertyType().map(PropertySpecs::withPropertyType).orElse(null))
        .and(model.getUserId().map(PropertySpecs::withUserId).orElse(null))
        .and(withRooms(model.getRooms(), model.getMinRooms(), model.getMaxRooms()))
        .and(withBeds(model.getBeds(), model.getMinBeds(), model.getMaxBeds()))
        .and(withBathrooms(model.getBathrooms(), model.getMinBathrooms(), model.getMaxBathrooms()))
        .and(withGarages(model.getGarages(), model.getMinGarages(), model.getMaxGarages()))
        .and(withStoreys(model.getStoreys(), model.getMinStoreys(), model.getMaxStoreys()))
        .and(withCoordinates(model.getMinLat(), model.getMinLon(), model.getMaxLat(), model.getMaxLon()));
  }

  private Specification<Property> withRooms(Optional<Integer> rooms, Optional<Integer> minRooms,
      Optional<Integer> maxRooms) {
    return rooms.map(PropertySpecs::withRooms).orElseGet(
        () -> Specification.where(minRooms.map(PropertySpecs::withMinRooms).orElse(null))
            .and(maxRooms.map(PropertySpecs::withMaxRooms).orElse(null)));
  }

  private Specification<Property> withBeds(Optional<Integer> beds, Optional<Integer> minBeds,
      Optional<Integer> maxBeds) {
    return beds.map(PropertySpecs::withBeds).orElseGet(
        () -> Specification.where(minBeds.map(PropertySpecs::withMinBeds).orElse(null))
            .and(maxBeds.map(PropertySpecs::withMaxBeds).orElse(null)));
  }

  private Specification<Property> withBathrooms(Optional<Integer> bathrooms,
      Optional<Integer> minBathrooms,
      Optional<Integer> maxBathrooms) {
    return bathrooms.map(PropertySpecs::withBathrooms).orElseGet(
        () -> Specification.where(minBathrooms.map(PropertySpecs::withMinBathrooms).orElse(null))
            .and(maxBathrooms.map(PropertySpecs::withMaxBathrooms).orElse(null)));
  }

  private Specification<Property> withGarages(Optional<Integer> garages,
      Optional<Integer> minGarages,
      Optional<Integer> maxGarages) {
    return garages.map(PropertySpecs::withGarages).orElseGet(
        () -> Specification.where(minGarages.map(PropertySpecs::withMinGarages).orElse(null))
            .and(maxGarages.map(PropertySpecs::withMaxGarages).orElse(null)));
  }

  private Specification<Property> withStoreys(Optional<Integer> storeys,
      Optional<Integer> minStoreys,
      Optional<Integer> maxStoreys) {
    return storeys.map(PropertySpecs::withStoreys).orElseGet(
        () -> Specification.where(minStoreys.map(PropertySpecs::withMinStoreys).orElse(null))
            .and(maxStoreys.map(PropertySpecs::withMaxStoreys).orElse(null)));
  }

  private Specification<Property> withCoordinates(Optional<Double> minLat,
      Optional<Double> minLon, Optional<Double> maxLat, Optional<Double> maxLon) {
    if (minLat.isEmpty() || minLon.isEmpty() || maxLat.isEmpty() || maxLon.isEmpty()) {
      return null;
    }
    return Specification.<Property>allOf(
        (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("latitude"), minLat.get(),
            maxLat.get())).and(
        (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("longitude"), minLon.get(),
            maxLon.get()));
  }


}
