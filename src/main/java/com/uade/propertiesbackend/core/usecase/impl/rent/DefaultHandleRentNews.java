package com.uade.propertiesbackend.core.usecase.impl.rent;

import static java.util.Objects.isNull;

import com.uade.propertiesbackend.core.domain.Rent;
import com.uade.propertiesbackend.core.domain.RentProcessStatus;
import com.uade.propertiesbackend.core.domain.RentStatus;
import com.uade.propertiesbackend.core.exception.BadRequestException;
import com.uade.propertiesbackend.core.usecase.HandleRentNews;
import com.uade.propertiesbackend.repository.RentRepository;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class DefaultHandleRentNews implements HandleRentNews {

  private static final List<RentStatus> CANCELLED_STATUSES = List.of(RentStatus.CANCELLED,
      RentStatus.PENDING_CANCELLED);
  private final RentRepository rentRepository;

  public DefaultHandleRentNews(RentRepository rentRepository) {
    this.rentRepository = rentRepository;
  }

  @Override
  public void accept(Model model) {
    validateModel(model);
    Rent rent;
    if (!isNull(model.getRentId())) {
      rent = rentRepository.findById(model.getRentId())
          .orElseThrow(() -> new BadRequestException("Rent not found"));
    } else {
      rent = rentRepository.getRentByRentProcessId(model.getRentProcessId())
          .orElseThrow(() -> new BadRequestException("Rent not found"));
    }

    rent.setStatus(model.getStatus());
    rent.setLastUpdatedDate(OffsetDateTime.now());
    if (CANCELLED_STATUSES.contains(model.getStatus())) {
      rent.getRentProcess().setStatus(RentProcessStatus.REJECTED);
      rent.getRentProcess().getProperty().setActive(true);
    }
    rentRepository.save(rent);
  }

  private void validateModel(Model model) {
    if (isNull(model)) {
      throw new BadRequestException("Model is required");
    }
    if (isNull(model.getStatus())) {
      throw new BadRequestException("Status is required");
    }
  }

}
