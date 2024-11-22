package com.uade.propertiesbackend.router.sqs.publisher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uade.propertiesbackend.core.domain.Property;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.eventbridge.EventBridgeClient;

@Component
@Slf4j
public class PropertyCreatedPublisher extends Publisher<Property> {

  protected PropertyCreatedPublisher(EventBridgeClient amazonSQSClient,
      @Value("${aws.queue.property-created}") String queueName, ObjectMapper objectMapper) {
    super(amazonSQSClient, queueName, objectMapper);
  }

}
