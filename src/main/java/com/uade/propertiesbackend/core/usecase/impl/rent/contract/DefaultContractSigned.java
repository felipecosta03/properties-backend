package com.uade.propertiesbackend.core.usecase.impl.rent.contract;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uade.propertiesbackend.core.domain.ContractEvent;
import com.uade.propertiesbackend.core.domain.RentProcessStatus;
import com.uade.propertiesbackend.core.usecase.ContractSigned;
import com.uade.propertiesbackend.core.usecase.HandleRentProcessNews;
import com.uade.propertiesbackend.core.usecase.RetrieveContractById;
import org.springframework.stereotype.Component;

@Component
public class DefaultContractSigned implements ContractSigned {

  private final RetrieveContractById retrieveContractById;
  private final HandleRentProcessNews handleRentProcessNews;
  private final ObjectMapper mapper;

  public DefaultContractSigned(RetrieveContractById retrieveContractById,
      HandleRentProcessNews handleRentProcessNews, ObjectMapper mapper) {
    this.retrieveContractById = retrieveContractById;
    this.handleRentProcessNews = handleRentProcessNews;
    this.mapper = mapper;
  }

  public void accept(ContractEvent contractEvent) {
    Long rentProcessId = retrieveContractById.apply(contractEvent.getContractId()).get()
        .getRentProcessId();

    handleRentProcessNews.accept(
        HandleRentProcessNews.Model.builder()
            .rentProcessId(rentProcessId)
            .status(RentProcessStatus.SUCCESS)
            .build());
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
