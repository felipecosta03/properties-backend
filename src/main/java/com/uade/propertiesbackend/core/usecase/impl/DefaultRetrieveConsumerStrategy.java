package com.uade.propertiesbackend.core.usecase.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uade.propertiesbackend.core.domain.ContractEvent;
import com.uade.propertiesbackend.core.domain.PaymentEvent;
import com.uade.propertiesbackend.core.domain.UserEvent;
import com.uade.propertiesbackend.core.usecase.ContractCancelled;
import com.uade.propertiesbackend.core.usecase.ContractCreated;
import com.uade.propertiesbackend.core.usecase.ContractRejected;
import com.uade.propertiesbackend.core.usecase.ContractSigned;
import com.uade.propertiesbackend.core.usecase.RentPaid;
import com.uade.propertiesbackend.core.usecase.RetrieveConsumerStrategy;
import com.uade.propertiesbackend.core.usecase.UserCreated;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DefaultRetrieveConsumerStrategy implements RetrieveConsumerStrategy {

  private final Map<String, Consumer> consumers;
  private final Map<List<Consumer>, Class> events;
  private final ObjectMapper objectMapper;

  public DefaultRetrieveConsumerStrategy(@Value("${aws.queue.rent-paid}") String rentPaidName,
      RentPaid rentPaid, @Value("${aws.queue.contract-rejected}") String contractRejectedName,
      ContractRejected contractRejected,
      @Value("${aws.queue.contract-cancelled}") String contractCancelledName,
      ContractCancelled contractCancelled,
      @Value("${aws.queue.contract-created}") String contractCreatedName,
      ContractCreated contractCreated,
      @Value("${aws.queue.contract-signed}") String contractSignedName,
      ContractSigned contractSigned, @Value("${aws.queue.user-created}") String userCreatedName,
      UserCreated userCreated, ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;

    this.events = Map.of(
        List.of(contractCancelled, contractSigned, contractCreated, contractRejected, rentPaid),
        ContractEvent.class, List.of(rentPaid), PaymentEvent.class,
        List.of(userCreated), UserEvent.class);

    this.consumers = Map.of(contractCancelledName, contractCancelled, contractSignedName,
        contractSigned, contractCreatedName, contractCreated, contractRejectedName,
        contractRejected, rentPaidName, rentPaid, userCreatedName, userCreated);
  }

  @Override
  public void accept(Model model) {
    Consumer consumer = consumers.get(model.getDetailEvent());
    Object event = null;
    for (Entry<List<Consumer>, Class> entry : events.entrySet()) {
      if (entry.getKey().contains(consumer)) {
        try {
          event = objectMapper.readValue(model.getDetail(), entry.getValue());
        } catch (JsonProcessingException e) {
          throw new RuntimeException(e);
        }
        break;
      }
    }
    consumer.accept(event);
  }
}
