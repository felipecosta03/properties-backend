package com.uade.propertiesbackend.router.sqs.subscriber;

import com.amazonaws.services.sqs.AmazonSQS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uade.propertiesbackend.core.domain.ContractEvent;
import com.uade.propertiesbackend.core.usecase.ContractCreated;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ContractRejectedSubscriber extends Subscriber<ContractEvent> {

  public ContractRejectedSubscriber(AmazonSQS amazonSQSClient,
      @Value("aws.queue.contract-rejected") String queueName,
      ObjectMapper objectMapper, ContractCreated contractCreated) {
    super(amazonSQSClient, queueName, objectMapper, contractCreated);

  }
}
