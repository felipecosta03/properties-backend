package com.uade.propertiesbackend.repository;

import com.uade.propertiesbackend.core.domain.Rent;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RentRepository extends JpaRepository<Rent, Long> {

  @Query("SELECT r FROM Rent r WHERE r.rentProcess.property.id = :propertyId")
  List<Rent> getRentsByPropertyId(Long propertyId);

  @Query("SELECT r FROM Rent r WHERE r.rentProcess.tenantId = :tenantId")
  List<Rent> getRentalsByTenantId(Long tenantId);

  @Query("SELECT r FROM Rent r WHERE r.rentProcess.property.userId = :ownerId")
  List<Rent> getRentalsByOwnerId(Long ownerId);

  @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Rent r WHERE r.rentProcess.property.id = :propertyId AND r.status <> 'CANCELLED'")
  boolean existsByPropertyId(Long propertyId);

  @Query("SELECT r FROM Rent r WHERE r.rentProcess.id = :rentProcessId")
  Optional<Rent> getRentByRentProcessId(Long rentProcessId);

}
