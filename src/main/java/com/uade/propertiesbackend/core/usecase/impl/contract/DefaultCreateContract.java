package com.uade.propertiesbackend.core.usecase.impl.contract;

import com.uade.propertiesbackend.core.domain.Contract;
import com.uade.propertiesbackend.core.usecase.CreateContract;
import com.uade.propertiesbackend.repository.ContractRepository;
import org.springframework.stereotype.Component;

@Component
public class DefaultCreateContract implements CreateContract {

  private final ContractRepository contractRepository;

  public DefaultCreateContract(ContractRepository contractRepository) {
    this.contractRepository = contractRepository;
  }

  @Override
  public void accept(Contract contract) {
    contractRepository.save(contract);
  }
}
