package com.uade.propertiesbackend.repository;

import com.uade.propertiesbackend.core.domain.RentProcess;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RentProcessRepository extends JpaRepository<RentProcess, Long> {

  @Query("SELECT r FROM RentProcess r WHERE r.property.id = :propertyId AND r.tenantId = :userId AND r.status <> 'REJECTED'")
  Optional<RentProcess> findByPropertyIdAndTenantIdAndStatusNotRejected(Long propertyId,
      Long userId);
}
