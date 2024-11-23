package com.uade.propertiesbackend.router.sqs.publisher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uade.propertiesbackend.router.request.RentProcessNews;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.eventbridge.EventBridgeClient;

@Component
@Slf4j
public class CreateRentProcessPublisher extends Publisher<RentProcessNews> {

  protected CreateRentProcessPublisher(EventBridgeClient amazonSQSClient,
      @Value("${aws.queue.rent-process-created}") String queueName, ObjectMapper objectMapper) {
    super(amazonSQSClient, queueName, objectMapper);
  }

}
