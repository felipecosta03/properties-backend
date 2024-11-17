package com.uade.propertiesbackend.core.usecase.impl.rent.contract;

import static java.util.Objects.isNull;

import com.uade.propertiesbackend.core.domain.ContractEvent;
import com.uade.propertiesbackend.core.exception.BadRequestException;
import com.uade.propertiesbackend.core.usecase.ContractCancelled;
import com.uade.propertiesbackend.core.usecase.HandleRentProcessNews;
import org.springframework.stereotype.Component;

@Component
public class DefaultContractCancelled implements ContractCancelled {

  private final HandleRentProcessNews handleRentProcessNews;

  public DefaultContractCancelled(HandleRentProcessNews handleRentProcessNews) {
    this.handleRentProcessNews = handleRentProcessNews;
  }

  @Override
  public void accept(ContractEvent contractCancelledEvent) {
    if (isNull(contractCancelledEvent)) {
      throw new BadRequestException("ContractCancelledEvent cannot be null");
    }
    if (isNull(contractCancelledEvent.getRentProcessId())) {
      throw new BadRequestException("RentProcessId cannot be null");
    }
    handleRentProcessNews.accept(HandleRentProcessNews.Model.builder()
        .rentProcessId(contractCancelledEvent.getRentProcessId()).build());
  }
}
