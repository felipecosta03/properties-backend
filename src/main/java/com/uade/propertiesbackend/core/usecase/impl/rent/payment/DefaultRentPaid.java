package com.uade.propertiesbackend.core.usecase.impl.rent.payment;

import static java.util.Objects.isNull;

import com.uade.propertiesbackend.core.domain.PaymentEvent;
import com.uade.propertiesbackend.core.domain.RentStatus;
import com.uade.propertiesbackend.core.exception.BadRequestException;
import com.uade.propertiesbackend.core.usecase.HandleRentNews;
import com.uade.propertiesbackend.core.usecase.RentPaid;
import org.springframework.stereotype.Component;

@Component
public class DefaultRentPaid implements RentPaid {

  private final HandleRentNews handleRentNews;

  public DefaultRentPaid(HandleRentNews handleRentNews) {
    this.handleRentNews = handleRentNews;
  }

  @Override
  public void accept(PaymentEvent paymentEvent) {
    if (isNull(paymentEvent)) {
      throw new BadRequestException("PaymentEvent can't be null");
    }
    if (isNull(paymentEvent.getRentProcessId())) {
      throw new BadRequestException("RentId can't be null");
    }
    handleRentNews.accept(
        HandleRentNews.Model.builder().rentId(Long.valueOf(paymentEvent.getRentProcessId()))
            .status(RentStatus.ACTIVE).build());
  }
}
