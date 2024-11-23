package com.uade.propertiesbackend.repository;

import com.uade.propertiesbackend.core.domain.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<Contract, String> {

}
