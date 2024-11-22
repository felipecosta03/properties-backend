package com.uade.propertiesbackend.core.usecase.impl.rent;

import static java.util.Objects.isNull;

import com.uade.propertiesbackend.core.domain.Rent;
import com.uade.propertiesbackend.core.exception.BadRequestException;
import com.uade.propertiesbackend.core.usecase.HandleRentNews;
import com.uade.propertiesbackend.repository.RentRepository;
import java.time.OffsetDateTime;
import org.springframework.stereotype.Component;

@Component
public class DefaultHandleRentNews implements HandleRentNews {

  private final RentRepository rentRepository;

  public DefaultHandleRentNews(RentRepository rentRepository) {
    this.rentRepository = rentRepository;
  }

  @Override
  public void accept(Model model) {
    validateModel(model);
    Rent rent = rentRepository.findById(model.getRentId())
        .orElseThrow(() -> new BadRequestException("Rent not found"));

    rent.setStatus(model.getStatus());
    rent.setLastUpdatedDate(OffsetDateTime.now());
    rentRepository.save(rent);
  }

  private void validateModel(Model model) {
    if (isNull(model)) {
      throw new BadRequestException("Model is required");
    }
    if (isNull(model.getRentId())) {
      throw new BadRequestException("Rent id is required");
    }
    if (isNull(model.getStatus())) {
      throw new BadRequestException("Status is required");
    }
  }

}
