package com.uade.propertiesbackend.core.usecase.impl.rent;

import static java.util.Objects.isNull;

import com.uade.propertiesbackend.core.domain.Rent;
import com.uade.propertiesbackend.core.domain.RentProcessStatus;
import com.uade.propertiesbackend.core.domain.RentStatus;
import com.uade.propertiesbackend.core.exception.BadRequestException;
import com.uade.propertiesbackend.core.usecase.CreateRent;
import com.uade.propertiesbackend.repository.RentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DefaultCreateRent implements CreateRent {

  private final RentRepository rentRepository;

  public DefaultCreateRent(RentRepository rentRepository) {
    this.rentRepository = rentRepository;
  }

  @Override
  public void accept(Model model) {
    validateModel(model);
    log.info("Creating rent: {}", model);
    rentRepository.save(
        Rent.builder().rentProcess(model.getRentProcess()).status(RentStatus.PENDING_PAYMENT)
            .build());
    log.info("Rent created successfully");
  }

  private void validateModel(Model model) {
    if (isNull(model)) {
      throw new BadRequestException("Model is required");
    }
    if (isNull(model.getRentProcess())) {
      throw new BadRequestException("Rent process is required");
    }
    if (!RentProcessStatus.SUCCESS.equals(model.getRentProcess().getStatus())) {
      throw new BadRequestException("Rent process must be successful");
    }
  }
}