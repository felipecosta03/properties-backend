package com.uade.propertiesbackend.repository;

import com.uade.propertiesbackend.core.domain.Rent;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RentRepository extends JpaRepository<Rent, Long> {

  @Query("SELECT r FROM Rent r WHERE r.rentProcess.property.id = :propertyId")
  List<Rent> getRentsByPropertyId(Long propertyId);

  @Query("SELECT r FROM Rent r WHERE r.rentProcess.tenantId = :tenantId")
  List<Rent> getRentalsByTenantId(Long tenantId);

  @Query("SELECT r FROM Rent r WHERE r.rentProcess.property.userId = :ownerId")
  List<Rent> getRentalsByOwnerId(Long ownerId);

}
