package com.uade.propertiesbackend.core.usecase.impl.rent;

import static com.uade.propertiesbackend.util.ValidationUtils.validatePropertyId;
import static com.uade.propertiesbackend.util.ValidationUtils.validateUserId;
import static java.util.Objects.isNull;

import com.uade.propertiesbackend.core.domain.Property;
import com.uade.propertiesbackend.core.domain.RentProcess;
import com.uade.propertiesbackend.core.domain.RentProcessStatus;
import com.uade.propertiesbackend.core.exception.BadRequestException;
import com.uade.propertiesbackend.core.usecase.CreateRentProcess;
import com.uade.propertiesbackend.core.usecase.UserExists;
import com.uade.propertiesbackend.repository.PropertyRepository;
import com.uade.propertiesbackend.repository.RentProcessRepository;
import com.uade.propertiesbackend.router.request.RentProcessNews;
import com.uade.propertiesbackend.router.sqs.publisher.CreateRentProcessPublisher;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class DefaultCreateRentProcess implements CreateRentProcess {

  private final RentProcessRepository rentProcessRepository;
  private final UserExists userExists;
  private final PropertyRepository propertyRepository;
  private final CreateRentProcessPublisher createRentProcessPublisher;

  public DefaultCreateRentProcess(RentProcessRepository rentProcessRepository,
      UserExists userExists, PropertyRepository propertyRepository,
      CreateRentProcessPublisher createRentProcessPublisher) {
    this.rentProcessRepository = rentProcessRepository;
    this.userExists = userExists;
    this.propertyRepository = propertyRepository;
    this.createRentProcessPublisher = createRentProcessPublisher;
  }

  @Override
  public void accept(Model model) {
    validateModel(model);

    if (!userExists.test(model.getTenantId())) {
      throw new BadRequestException("User does not exist");
    }

    Property property = propertyRepository.findById(model.getPropertyId())
        .orElseThrow(() -> new BadRequestException("Property does not exist"));

    if (!property.isActive()) {
      throw new BadRequestException("Property is not active");
    }

    if (model.getTenantId().equals(property.getUserId())) {
      throw new BadRequestException("Owner cannot rent his own property");
    }

    rentProcessRepository.findByPropertyIdAndTenantIdAndStatusNotRejected(model.getPropertyId(),
        model.getTenantId()).stream().findAny().ifPresent(rentProcess -> {
      throw new BadRequestException("Rent process already exists");
    });

    RentProcess rentProcess = rentProcessRepository.save(
        RentProcess.builder().property(property).tenantId(model.getTenantId())
            .dateCreated(LocalDateTime.now()).status(RentProcessStatus.PENDING_CONTRACT).build());

    createRentProcessPublisher.apply(createRentProcessNews(rentProcess));
  }

  private RentProcessNews createRentProcessNews(RentProcess rentProcess) {
    return RentProcessNews.builder().rentProcessId(rentProcess.getId())
        .tenantId(rentProcess.getTenantId()).dateCreated(rentProcess.getDateCreated())
        .landLordId(rentProcess.getProperty().getUserId())
        .status(rentProcess.getStatus()).property(rentProcess.getProperty())
        .propertyId(rentProcess.getProperty().getId()).build();
  }

  private void validateModel(Model model) {
    if (isNull(model)) {
      throw new BadRequestException("Model is required");
    }
    validateUserId(model.getTenantId());
    validatePropertyId(model.getPropertyId());
  }
}
