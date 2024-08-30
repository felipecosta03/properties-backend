package com.uade.propertiesbackend.repository;

import com.uade.propertiesbackend.core.domain.RentProcess;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentProcessRepository extends JpaRepository<RentProcess, Long> {

  Optional<RentProcess> findByPropertyIdAndUserId(Long propertyId, Long userId);
}
