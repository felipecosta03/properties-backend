package com.uade.propertiesbackend.core.usecase.impl.rent;

import static com.uade.propertiesbackend.util.ValidationUtils.validatePropertyId;
import static com.uade.propertiesbackend.util.ValidationUtils.validateUserId;
import static java.util.Objects.isNull;

import com.uade.propertiesbackend.core.domain.Property;
import com.uade.propertiesbackend.core.domain.RentProcess;
import com.uade.propertiesbackend.core.domain.RentProcessStatus;
import com.uade.propertiesbackend.core.exception.BadRequestException;
import com.uade.propertiesbackend.core.usecase.CreateRentProcess;
import com.uade.propertiesbackend.core.usecase.SendRentProcessNews;
import com.uade.propertiesbackend.core.usecase.UserExists;
import com.uade.propertiesbackend.repository.PropertyRepository;
import com.uade.propertiesbackend.repository.RentProcessRepository;
import org.springframework.stereotype.Component;

@Component
public class DefaultCreateRentProcess implements CreateRentProcess {

  private final RentProcessRepository rentProcessRepository;
  private final UserExists userExists;
  private final PropertyRepository propertyRepository;
  private final SendRentProcessNews sendRentProcessNews;

  public DefaultCreateRentProcess(RentProcessRepository rentProcessRepository,
      UserExists userExists, PropertyRepository propertyRepository,
      SendRentProcessNews sendRentProcessNews) {
    this.rentProcessRepository = rentProcessRepository;
    this.userExists = userExists;
    this.propertyRepository = propertyRepository;
    this.sendRentProcessNews = sendRentProcessNews;
  }

  @Override
  public void accept(Model model) {
    validateModel(model);

    if (!userExists.test(model.getTenantId())) {
      throw new BadRequestException("User does not exist");
    }

    Property property = propertyRepository.findById(model.getPropertyId())
        .orElseThrow(() -> new BadRequestException("Property does not exist"));

    if (model.getTenantId().equals(property.getUserId())) {
      throw new BadRequestException("Owner cannot rent his own property");
    }

    rentProcessRepository.findByPropertyIdAndTenantIdAndStatusNotRejected(model.getPropertyId(),
            model.getTenantId()).stream().findAny()
        .ifPresent(rentProcess -> {
          throw new BadRequestException("Rent process already exists");
        });

    RentProcess rentProcess = rentProcessRepository.save(
        RentProcess.builder().property(property).tenantId(model.getTenantId())
            .status(RentProcessStatus.PENDING_APPROVAL).build());

    sendRentProcessNews.accept(rentProcess);
  }

  private void validateModel(Model model) {
    if (isNull(model)) {
      throw new BadRequestException("Model is required");
    }
    validateUserId(model.getTenantId());
    validatePropertyId(model.getPropertyId());
  }
}
