package com.uade.propertiesbackend.router.sqs.publisher;

import com.amazonaws.services.sqs.AmazonSQS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uade.propertiesbackend.core.domain.RentProcess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CreateRentProcessPublisher extends Publisher<RentProcess> {

  protected CreateRentProcessPublisher(AmazonSQS amazonSQSClient,
      @Value("aws.queue.rent-process-created") String queueName, ObjectMapper objectMapper) {
    super(amazonSQSClient, queueName, objectMapper);
  }

}