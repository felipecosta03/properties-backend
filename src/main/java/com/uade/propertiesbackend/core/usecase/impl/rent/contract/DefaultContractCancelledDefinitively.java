package com.uade.propertiesbackend.core.usecase.impl.rent.contract;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uade.propertiesbackend.core.domain.ContractEvent;
import com.uade.propertiesbackend.core.domain.RentStatus;
import com.uade.propertiesbackend.core.usecase.ContractCancelledDefinitively;
import com.uade.propertiesbackend.core.usecase.HandleRentNews;
import com.uade.propertiesbackend.core.usecase.RetrieveContractById;
import org.springframework.stereotype.Component;

@Component
public class DefaultContractCancelledDefinitively implements ContractCancelledDefinitively {

  private final RetrieveContractById retrieveContractById;
  private final ObjectMapper mapper;
  private final HandleRentNews handleRentNews;

  public DefaultContractCancelledDefinitively(RetrieveContractById retrieveContractById,
      HandleRentNews handleRentNews, ObjectMapper mapper) {
    this.retrieveContractById = retrieveContractById;
    this.handleRentNews = handleRentNews;
    this.mapper = mapper;
  }

  public void accept(ContractEvent contractEvent) {

    Long rentProcessId = retrieveContractById.apply(contractEvent.getContractId()).get()
        .getRentProcessId();

    handleRentNews.accept(
        HandleRentNews.Model.builder().status(RentStatus.PENDING_CANCELLED)
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
