package com.uade.propertiesbackend.core.usecase.impl.rent;

import static com.uade.propertiesbackend.core.domain.Role.OWNER;
import static com.uade.propertiesbackend.util.ValidationUtils.validateRole;
import static com.uade.propertiesbackend.util.ValidationUtils.validateUserId;
import static java.util.Objects.isNull;

import com.uade.propertiesbackend.core.domain.Rent;
import com.uade.propertiesbackend.core.domain.RentProcess;
import com.uade.propertiesbackend.core.domain.dto.RentDto;
import com.uade.propertiesbackend.core.domain.dto.RentalsDto;
import com.uade.propertiesbackend.core.exception.BadRequestException;
import com.uade.propertiesbackend.core.usecase.PropertyMapper;
import com.uade.propertiesbackend.core.usecase.RetrieveRentalsByUserId;
import com.uade.propertiesbackend.repository.RentProcessRepository;
import com.uade.propertiesbackend.repository.RentRepository;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class DefaultRetrieveRentalsByUserId implements RetrieveRentalsByUserId {


  private final RentRepository rentRepository;
  private final RentProcessRepository rentProcessRepository;

  public DefaultRetrieveRentalsByUserId(RentRepository rentRepository,
      RentProcessRepository rentProcessRepository) {
    this.rentRepository = rentRepository;
    this.rentProcessRepository = rentProcessRepository;
  }

  @Override

  public RentalsDto apply(Model model) {
    validateModel(model);
    List<Rent> rentals;
    List<RentProcess> rentProcesses;
    if (model.getRole().equals(OWNER)) {

      rentals = rentRepository.getRentalsByOwnerId(model.getUserId());
      rentProcesses = rentProcessRepository.getRentProcessesByOwnerId(model.getUserId());

    } else {

      rentals = rentRepository.getRentalsByTenantId(model.getUserId());
      rentProcesses = rentProcessRepository.getRentProcessesByTenantId(model.getUserId());

    }
    return RentalsDto.builder().rentals(rentals.stream().map(this::toRentDto).toList())
        .rentProcesses(rentProcesses.stream().map(this::toRentProcessDto).toList()).build();
  }

  private void validateModel(Model model) {
    if (isNull(model)) {
      throw new BadRequestException("Model cannot be null");
    }
    validateRole(model.getRole());
    validateUserId(model.getUserId());
  }

  private RentDto toRentDto(Rent rent) {
    return RentDto.builder().property(
            PropertyMapper.INSTANCE.propertyToPropertyDto(rent.getRentProcess().getProperty()))
        .id(rent.getId())
        .status(rent.getStatus().name()).build();
  }

  private RentDto toRentProcessDto(RentProcess rentProcess) {
    return RentDto.builder()
        .id(rentProcess.getId())
        .property(PropertyMapper.INSTANCE.propertyToPropertyDto(rentProcess.getProperty()))
        .status(rentProcess.getStatus().name()).build();
  }
}
