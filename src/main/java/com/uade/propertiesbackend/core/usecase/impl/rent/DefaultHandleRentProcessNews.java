package com.uade.propertiesbackend.core.usecase.impl.rent;

import static java.util.Objects.isNull;

import com.uade.propertiesbackend.core.domain.RentProcess;
import com.uade.propertiesbackend.core.domain.RentProcessStatus;
import com.uade.propertiesbackend.core.exception.BadRequestException;
import com.uade.propertiesbackend.core.usecase.CreateRent;
import com.uade.propertiesbackend.core.usecase.HandleRentProcessNews;
import com.uade.propertiesbackend.core.usecase.HasPropertyCurrentRent;
import com.uade.propertiesbackend.repository.PropertyRepository;
import com.uade.propertiesbackend.repository.RentProcessRepository;
import org.springframework.stereotype.Component;

@Component
public class DefaultHandleRentProcessNews implements HandleRentProcessNews {


  private final RentProcessRepository rentProcessRepository;
  private final PropertyRepository propertyRepository;
  private final CreateRent createRent;
  private final HasPropertyCurrentRent hasPropertyCurrentRent;

  public DefaultHandleRentProcessNews(RentProcessRepository rentProcessRepository,
      PropertyRepository propertyRepository, CreateRent createRent,
      HasPropertyCurrentRent hasPropertyCurrentRent) {
    this.rentProcessRepository = rentProcessRepository;
    this.propertyRepository = propertyRepository;
    this.createRent = createRent;
    this.hasPropertyCurrentRent = hasPropertyCurrentRent;
  }

  @Override
  public void accept(Model model) {
    validateModel(model);

    RentProcess rentProcess = rentProcessRepository.findById(model.getRentProcessId())
        .orElseThrow(() -> new BadRequestException("Rent process does not exist"));

    if (RentProcessStatus.SUCCESS.equals(rentProcess.getStatus())) {
      throw new BadRequestException("Rent process already finished");
    }

    if (hasPropertyCurrentRent.test(rentProcess.getProperty().getId())) {
      throw new BadRequestException("Property already has a current rent");
    }

    if (!RentProcessStatus.REJECTED.equals(model.getStatus())) {
      switch (rentProcess.getStatus()) {
        case PENDING_APPROVAL:
          if (!RentProcessStatus.ACCEPTED.equals(model.getStatus())) {
            throw new BadRequestException("Rent process status must be accepted");
          }
          break;
        case ACCEPTED:
          if (!RentProcessStatus.PENDING_CONTRACT.equals(model.getStatus())) {
            throw new BadRequestException("Rent process status must be pending contract");
          }
          break;
        case PENDING_CONTRACT:
          if (!RentProcessStatus.CONTRACT_CREATED.equals(model.getStatus())) {
            throw new BadRequestException("Rent process status must be contract created");
          }
          break;
        case CONTRACT_CREATED:
          if (!RentProcessStatus.SUCCESS.equals(model.getStatus())) {
            throw new BadRequestException("Rent process status must be success");
          }
          break;
      }
    }

    rentProcess.setStatus(model.getStatus());

    if (RentProcessStatus.SUCCESS.equals(model.getStatus())) {
      rentProcess.getProperty().setActive(false);
      propertyRepository.save(rentProcess.getProperty());
      createRent.accept(CreateRent.Model.builder().rentProcess(rentProcess).build());
    }

    rentProcessRepository.save(rentProcess);
  }

  private void validateModel(Model model) {
    if (isNull(model)) {
      throw new BadRequestException("Model is required");
    }
    if (isNull(model.getRentProcessId())) {
      throw new BadRequestException("Rent process id is required");
    }
    if (isNull(model.getStatus())) {
      throw new BadRequestException("Status is required");
    }
  }
}
