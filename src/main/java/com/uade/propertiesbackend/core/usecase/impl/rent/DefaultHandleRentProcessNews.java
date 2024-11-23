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
import com.uade.propertiesbackend.router.sqs.publisher.PropertyUpdatedPublisher;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class DefaultHandleRentProcessNews implements HandleRentProcessNews {


  private final RentProcessRepository rentProcessRepository;
  private final PropertyRepository propertyRepository;
  private final CreateRent createRent;
  private final HasPropertyCurrentRent hasPropertyCurrentRent;
  private final PropertyUpdatedPublisher propertyUpdatedPublisher;

  public DefaultHandleRentProcessNews(RentProcessRepository rentProcessRepository,
      PropertyRepository propertyRepository, CreateRent createRent,
      HasPropertyCurrentRent hasPropertyCurrentRent,
      PropertyUpdatedPublisher propertyUpdatedPublisher) {
    this.rentProcessRepository = rentProcessRepository;
    this.propertyRepository = propertyRepository;
    this.createRent = createRent;
    this.hasPropertyCurrentRent = hasPropertyCurrentRent;
    this.propertyUpdatedPublisher = propertyUpdatedPublisher;
  }

  @Override
  public void accept(Model model) {
    validateModel(model);

    RentProcess rentProcess = rentProcessRepository.findById(model.getRentProcessId())
        .orElseThrow(() -> new BadRequestException("Rent process does not exist"));
    Hibernate.initialize(rentProcess.getProperty());
    if (RentProcessStatus.SUCCESS.equals(rentProcess.getStatus())) {
      throw new BadRequestException("Rent process already finished");
    }

    if (hasPropertyCurrentRent.test(rentProcess.getProperty().getId())) {
      throw new BadRequestException("Property already has a current rent");
    }

    rentProcess.setStatus(model.getStatus());

    if (RentProcessStatus.SUCCESS.equals(model.getStatus())) {
      rentProcess.getProperty().setActive(false);
      propertyRepository.save(rentProcess.getProperty());
      propertyUpdatedPublisher.apply(rentProcess.getProperty());
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
