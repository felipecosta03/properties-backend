package com.uade.propertiesbackend.core.usecase.impl.rent.payment;

import static java.util.Objects.isNull;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uade.propertiesbackend.core.domain.PaymentEvent;
import com.uade.propertiesbackend.core.domain.RentStatus;
import com.uade.propertiesbackend.core.exception.BadRequestException;
import com.uade.propertiesbackend.core.usecase.HandleRentNews;
import com.uade.propertiesbackend.core.usecase.RentPaid;
import org.springframework.stereotype.Component;

@Component
public class DefaultRentPaid implements RentPaid {

  private final HandleRentNews handleRentNews;
  private final ObjectMapper mapper;

  public DefaultRentPaid(HandleRentNews handleRentNews, ObjectMapper mapper) {
    this.handleRentNews = handleRentNews;
    this.mapper = mapper;
  }

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

  @Override
  public void accept(String s) {
    try {
      this.accept(mapper.readValue(s, PaymentEvent.class));
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
