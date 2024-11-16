package com.uade.propertiesbackend.router.sqs.subscriber;

import com.amazonaws.services.sqs.AmazonSQS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uade.propertiesbackend.core.domain.ContractEvent;
import com.uade.propertiesbackend.core.usecase.ContractSigned;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ContractCreatedSubscriber extends Subscriber<ContractEvent> {

  public ContractCreatedSubscriber(AmazonSQS amazonSQSClient,
      @Value("aws.queue.contract-created") String queueName, ObjectMapper objectMapper,
      ContractSigned contractCreated) {
    super(amazonSQSClient, queueName, objectMapper, contractCreated);

  }
}
