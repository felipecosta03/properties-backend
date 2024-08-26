package com.uade.propertiesbackend.repository;

import com.uade.propertiesbackend.core.domain.Property;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Repository for creating a property.
 */
@Repository
public interface PropertyRepository extends JpaRepository<Property, Long>,
    JpaSpecificationExecutor<Property> {

  Page<Property> findAll(Specification<Property> specification, Pageable pageable);

  Page<Property> findPropertiesByIdIn(List<Long> propertiesId, Pageable pageable);
}
