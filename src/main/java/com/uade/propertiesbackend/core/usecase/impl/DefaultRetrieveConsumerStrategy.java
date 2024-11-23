package com.uade.propertiesbackend.core.usecase.impl;

import com.uade.propertiesbackend.core.usecase.ContractCancelled;
import com.uade.propertiesbackend.core.usecase.ContractCancelledDefinitively;
import com.uade.propertiesbackend.core.usecase.ContractCreated;
import com.uade.propertiesbackend.core.usecase.ContractRejected;
import com.uade.propertiesbackend.core.usecase.ContractSigned;
import com.uade.propertiesbackend.core.usecase.RentPaid;
import com.uade.propertiesbackend.core.usecase.RetrieveConsumerStrategy;
import com.uade.propertiesbackend.core.usecase.UserCreated;
import java.util.Map;
import java.util.function.Consumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DefaultRetrieveConsumerStrategy implements RetrieveConsumerStrategy {

  private final Map<String, Consumer> consumers;

  public DefaultRetrieveConsumerStrategy(@Value("${aws.queue.rent-paid}") String rentPaidName,
      RentPaid rentPaid, @Value("${aws.queue.contract-rejected}") String contractRejectedName,
      ContractRejected contractRejected,
      @Value("${aws.queue.contract-cancelled}") String contractCancelledName,
      ContractCancelled contractCancelled,
      @Value("${aws.queue.contract-created}") String contractCreatedName,
      ContractCreated contractCreated,
      @Value("${aws.queue.contract-signed}") String contractSignedName,
      ContractSigned contractSigned, @Value("${aws.queue.user-created}") String userCreatedName,
      UserCreated userCreated,
      @Value("${aws.queue.contract-cancelled-definitively}") String contractCancelledDefinitivelyName,
      ContractCancelledDefinitively contractCancelledDefinitively) {

    this.consumers = Map.of(contractCancelledName, contractCancelled, contractSignedName,
        contractSigned, contractCreatedName, contractCreated, contractRejectedName,
        contractRejected, rentPaidName, rentPaid, userCreatedName, userCreated,
        contractCancelledDefinitivelyName, contractCancelledDefinitively, "UsuarioModificado",
        (e) -> {
        });
  }

  @Override
  public void accept(Model model) {
    Consumer consumer = consumers.get(model.getDetailEvent());
    consumer.accept(model.getDetail());
  }
}
