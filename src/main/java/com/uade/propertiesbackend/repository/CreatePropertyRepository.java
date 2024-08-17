package com.uade.propertiesbackend.repository;

import com.uade.propertiesbackend.core.domain.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreatePropertyRepository extends JpaRepository<Property, Long> {
}
