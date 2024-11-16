package com.uade.propertiesbackend.router.sqs.subscriber;

import com.amazonaws.services.sqs.AmazonSQS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uade.propertiesbackend.core.domain.ContractEvent;
import com.uade.propertiesbackend.core.usecase.ContractCancelled;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ContractCancelledSubscriber extends Subscriber<ContractEvent> {

  public ContractCancelledSubscriber(AmazonSQS amazonSQSClient,
      @Value("aws.queue.contract-cancelled") String queueName,
      ObjectMapper objectMapper, ContractCancelled contractCancelled) {
    super(amazonSQSClient, queueName, objectMapper, contractCancelled);

  }
}
