package com.uade.propertiesbackend.router.sqs.publisher;

import com.amazonaws.services.sqs.AmazonSQS;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Publisher<T> {

  private final AmazonSQS amazonSQSClient;
  private final String queueName;
  private final ObjectMapper objectMapper;

  protected Publisher(AmazonSQS amazonSQSClient, String queueName, ObjectMapper objectMapper) {
    this.amazonSQSClient = amazonSQSClient;
    this.queueName = queueName;
    this.objectMapper = objectMapper;
  }

  public void apply(T msg) {
    try {
      String object = objectMapper.writeValueAsString(msg);
      amazonSQSClient.sendMessage(queueName, object);
    } catch (Exception e) {
      log.error("Queue Exception Message: {}", e.getMessage());
    }
  }
}
