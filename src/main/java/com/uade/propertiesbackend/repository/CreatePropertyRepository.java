package com.uade.propertiesbackend.repository;

import com.uade.propertiesbackend.core.domain.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for creating a property.
 */
@Repository
public interface CreatePropertyRepository extends JpaRepository<Property, Long> {
}
