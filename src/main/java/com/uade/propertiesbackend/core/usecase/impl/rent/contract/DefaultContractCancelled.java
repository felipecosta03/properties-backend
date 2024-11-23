package com.uade.propertiesbackend.core.usecase.impl.rent.contract;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uade.propertiesbackend.core.domain.ContractEvent;
import com.uade.propertiesbackend.core.usecase.ContractCancelled;
import com.uade.propertiesbackend.core.usecase.HandleRentProcessNews;
import com.uade.propertiesbackend.core.usecase.RetrieveContractById;
import org.springframework.stereotype.Component;

@Component
public class DefaultContractCancelled implements ContractCancelled {

  private final RetrieveContractById retrieveContractById;
  private final HandleRentProcessNews handleRentProcessNews;
  private final ObjectMapper mapper;

  public DefaultContractCancelled(RetrieveContractById retrieveContractById,
      HandleRentProcessNews handleRentProcessNews,
      ObjectMapper mapper) {
    this.retrieveContractById = retrieveContractById;
    this.handleRentProcessNews = handleRentProcessNews;
    this.mapper = mapper;
  }

  public void accept(ContractEvent contractCancelledEvent) {
    Long rentProcessId = retrieveContractById.apply(contractCancelledEvent.getContractId()).get()
        .getRentProcessId();

    handleRentProcessNews.accept(HandleRentProcessNews.Model.builder()
        .rentProcessId(rentProcessId).build());
  }

  @Override
  public void accept(String s) {
    try {
      this.accept(mapper.readValue(s, ContractEvent.class));
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
