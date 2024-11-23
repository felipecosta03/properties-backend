package com.uade.propertiesbackend.core.usecase.impl.contract;

import com.uade.propertiesbackend.core.domain.Contract;
import com.uade.propertiesbackend.core.usecase.RetrieveContractById;
import com.uade.propertiesbackend.repository.ContractRepository;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class DefaultRetrieveContractById implements RetrieveContractById {

  private final ContractRepository contractRepository;

  public DefaultRetrieveContractById(ContractRepository contractRepository) {
    this.contractRepository = contractRepository;
  }

  @Override
  public Optional<Contract> apply(String contractId) {
    return contractRepository.findById(contractId);
  }
}
