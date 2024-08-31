package com.uade.propertiesbackend.repository;

import com.uade.propertiesbackend.core.domain.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRepository extends JpaRepository<Rent, Long> {

}
