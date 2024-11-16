package com.uade.propertiesbackend.router.sqs.subscriber;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
public abstract class Subscriber<T> {

  private final AmazonSQS amazonSQSClient;
  private final String queueName;
  private final ObjectMapper objectMapper;
  private final Consumer<T> consumer;

  protected Subscriber(AmazonSQS amazonSQSClient, String queueName, ObjectMapper objectMapper,
      Consumer<T> consumer) {
    this.amazonSQSClient = amazonSQSClient;
    this.queueName = queueName;
    this.objectMapper = objectMapper;
    this.consumer = consumer;
  }

  @Scheduled(fixedDelay = 5000)
  public void consumeMessages() {
    try {
      String queueUrl = amazonSQSClient.getQueueUrl(queueName).getQueueUrl();

      ReceiveMessageResult receiveMessageResult = amazonSQSClient.receiveMessage(queueUrl);

      if (!receiveMessageResult.getMessages().isEmpty()) {
        com.amazonaws.services.sqs.model.Message message = receiveMessageResult.getMessages()
            .get(0);
        log.info("Read Message from queue: {}", message.getBody());

        T object = map(message.getBody());

        consumer.accept(object);

        amazonSQSClient.deleteMessage(queueUrl, message.getReceiptHandle());
      }

    } catch (Exception e) {
      log.error("Queue Exception Message: {}", e.getMessage());
    }
  }

  public T map(String msg) throws Exception {
    return objectMapper.readValue(msg, new TypeReference<T>() {
    });
  }
}
