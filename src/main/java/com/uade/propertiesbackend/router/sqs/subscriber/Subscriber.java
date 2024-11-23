package com.uade.propertiesbackend.router.sqs.subscriber;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uade.propertiesbackend.core.usecase.RetrieveConsumerStrategy;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Subscriber {

  private final AmazonSQS amazonSQSClient;
  private final ObjectMapper objectMapper;
  private final RetrieveConsumerStrategy retrieveConsumerStrategy;

  protected Subscriber(AmazonSQS amazonSQSClient, ObjectMapper objectMapper,
      RetrieveConsumerStrategy retrieveConsumerStrategy) {
    this.amazonSQSClient = amazonSQSClient;
    this.retrieveConsumerStrategy = retrieveConsumerStrategy;
    this.objectMapper = objectMapper;
  }

  @Scheduled(fixedDelay = 5000)
  public void consumeMessages() {
    try {
      String queueUrl = amazonSQSClient.getQueueUrl("inmuebles").getQueueUrl();

      ReceiveMessageResult receiveMessageResult = amazonSQSClient.receiveMessage(
          new ReceiveMessageRequest(queueUrl).withVisibilityTimeout(5).withWaitTimeSeconds(5)
              .withMaxNumberOfMessages(10));

      if (!receiveMessageResult.getMessages().isEmpty()) {
        Message message = receiveMessageResult.getMessages().get(0);
        Map<String, Object> event = objectMapper.readValue(message.getBody(), Map.class);
        log.info("Message: {}", event);
        retrieveConsumerStrategy.accept(RetrieveConsumerStrategy.Model.builder()
            .detailEvent(event.get("detail-type").toString())
            .detail(objectMapper.writeValueAsString(event.get("detail"))).build());

        amazonSQSClient.deleteMessage(queueUrl, message.getReceiptHandle());
      }

    } catch (Exception e) {
      log.error("Queue Exception Message: {}", e.getMessage());
    }
  }
}
