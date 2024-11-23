package com.uade.propertiesbackend.core.usecase.impl.rent.contract;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uade.propertiesbackend.core.domain.Contract;
import com.uade.propertiesbackend.core.domain.RentProcessStatus;
import com.uade.propertiesbackend.core.domain.dto.ContractDto;
import com.uade.propertiesbackend.core.usecase.ContractCreated;
import com.uade.propertiesbackend.core.usecase.CreateContract;
import com.uade.propertiesbackend.core.usecase.HandleRentProcessNews;
import org.springframework.stereotype.Component;

@Component
public class DefaultContractCreated implements ContractCreated {

  private final CreateContract createContract;
  private final HandleRentProcessNews handleRentProcessNews;
  private final ObjectMapper mapper;

  public DefaultContractCreated(CreateContract createContract,
      HandleRentProcessNews handleRentProcessNews, ObjectMapper mapper) {
    this.createContract = createContract;
    this.handleRentProcessNews = handleRentProcessNews;
    this.mapper = mapper;
  }

  public void accept(ContractDto contract) {
    createContract.accept(map(contract));
    handleRentProcessNews.accept(
        HandleRentProcessNews.Model.builder().rentProcessId(contract.getRentId())
            .status(RentProcessStatus.CONTRACT_CREATED).build());
  }

  private Contract map(ContractDto contractDto) {
    return Contract.builder().rentProcessId(contractDto.getRentId()).id(contractDto.getContractId())
        .build();
  }

  public void accept(String s) {
    try {
      this.accept(mapper.readValue(s, ContractDto.class));
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
