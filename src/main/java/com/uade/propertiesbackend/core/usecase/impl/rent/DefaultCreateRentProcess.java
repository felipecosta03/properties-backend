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
import org.springframework.stereotype.Component;

@Component
public class DefaultCreateRentProcess implements CreateRentProcess {

  private final RentProcessRepository rentProcessRepository;
  private final UserExists userExists;
  private final PropertyRepository propertyRepository;

  public DefaultCreateRentProcess(RentProcessRepository rentProcessRepository,
      UserExists userExists, PropertyRepository propertyRepository) {
    this.rentProcessRepository = rentProcessRepository;
    this.userExists = userExists;
    this.propertyRepository = propertyRepository;
  }

  @Override
  public void accept(Model model) {
    validateModel(model);

    if (!userExists.test(model.getUserId())) {
      throw new BadRequestException("User does not exist");
    }

    Property property = propertyRepository.findById(model.getPropertyId())
        .orElseThrow(() -> new BadRequestException("Property does not exist"));

    rentProcessRepository.findByPropertyIdAndUserId(model.getPropertyId(), model.getUserId())
        .ifPresent(rentProcess -> {
          throw new BadRequestException("Rent process already exists");
        });

    rentProcessRepository.save(RentProcess.builder().property(property).userId(model.getUserId())
        .status(RentProcessStatus.PENDING_APPROVAL).build());
  }

  private void validateModel(Model model) {
    if (isNull(model)) {
      throw new BadRequestException("Model is required");
    }
    validateUserId(model.getUserId());
    validatePropertyId(model.getPropertyId());
  }
}
