package com.uade.propertiesbackend.core.usecase.impl.rent.contract;

import com.uade.propertiesbackend.core.domain.ContractEvent;
import com.uade.propertiesbackend.core.domain.RentProcessStatus;
import com.uade.propertiesbackend.core.usecase.ContractSigned;
import com.uade.propertiesbackend.core.usecase.HandleRentProcessNews;
import org.springframework.stereotype.Component;

@Component
public class DefaultContractSigned implements ContractSigned {

  private final HandleRentProcessNews handleRentProcessNews;

  public DefaultContractSigned(HandleRentProcessNews handleRentProcessNews) {
    this.handleRentProcessNews = handleRentProcessNews;
  }

  @Override
  public void accept(ContractEvent contractEvent) {
    handleRentProcessNews.accept(
        HandleRentProcessNews.Model.builder()
            .rentProcessId(Long.valueOf(contractEvent.getRentProcessId()))
            .status(RentProcessStatus.SUCCESS)
            .build());
  }
}
