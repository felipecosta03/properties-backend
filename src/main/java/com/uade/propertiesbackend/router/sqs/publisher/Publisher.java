package com.uade.propertiesbackend.router.sqs.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import software.amazon.awssdk.services.eventbridge.EventBridgeClient;
import software.amazon.awssdk.services.eventbridge.model.PutEventsRequest;
import software.amazon.awssdk.services.eventbridge.model.PutEventsRequestEntry;
import software.amazon.awssdk.services.eventbridge.model.PutEventsResponse;


@Slf4j
public abstract class Publisher<T> {

  private final String queueName;
  private final ObjectMapper mapper;
  private final EventBridgeClient eventBridgeClient;
  @Value("${aws.eventbridge.arn}")
  private String eventBusArn;

  public Publisher(EventBridgeClient eventBridgeClient, String queueName, ObjectMapper mapper) {
    this.eventBridgeClient = eventBridgeClient;
    this.mapper = mapper;
    this.queueName = queueName;
  }

  public void apply(T msg) {
    // Convert the event message to JSON
    String messageBody = null;
    try {
      messageBody = mapper.writeValueAsString(msg);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    PutEventsRequestEntry requestEntry = PutEventsRequestEntry.builder().eventBusName(eventBusArn)
        .detailType(queueName).source("SmartMove").detail(messageBody).build();

    PutEventsRequest request = PutEventsRequest.builder().entries(requestEntry).build();

    PutEventsResponse response = eventBridgeClient.putEvents(request);
    log.info("Event sent to EventBridge with response: {}", response);

  }
}
