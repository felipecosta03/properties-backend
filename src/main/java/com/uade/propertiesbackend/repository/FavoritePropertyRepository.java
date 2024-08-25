package com.uade.propertiesbackend.repository;

import com.uade.propertiesbackend.core.domain.FavoriteProperty;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoritePropertyRepository extends JpaRepository<FavoriteProperty, Long> {
  Optional<FavoriteProperty> findByUserIdAndPropertyId(Long userId, Long propertyId);
}
