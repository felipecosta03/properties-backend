package com.uade.propertiesbackend.core.usecase.impl.rent.contract;

import com.uade.propertiesbackend.core.domain.ContractEvent;
import com.uade.propertiesbackend.core.domain.RentProcessStatus;
import com.uade.propertiesbackend.core.usecase.ContractCreated;
import com.uade.propertiesbackend.core.usecase.HandleRentProcessNews;
import org.springframework.stereotype.Component;

@Component
public class DefaultContractCreated implements ContractCreated {

  private final HandleRentProcessNews handleRentProcessNews;

  public DefaultContractCreated(HandleRentProcessNews handleRentProcessNews) {
    this.handleRentProcessNews = handleRentProcessNews;
  }

  @Override
  public void accept(ContractEvent contractEvent) {
    handleRentProcessNews.accept(
        HandleRentProcessNews.Model.builder()
            .rentProcessId(Long.valueOf(contractEvent.getRentProcessId()))
            .status(RentProcessStatus.CONTRACT_CREATED)
            .build());
  }
}
