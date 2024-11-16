package com.uade.propertiesbackend.router.sqs.subscriber;

import com.amazonaws.services.sqs.AmazonSQS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uade.propertiesbackend.core.domain.PaymentEvent;
import com.uade.propertiesbackend.core.usecase.RentPaid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RentPaidSubscriber extends Subscriber<PaymentEvent> {

  public RentPaidSubscriber(AmazonSQS amazonSQSClient,
      @Value("aws.queue.rent-paid") String queueName,
      ObjectMapper objectMapper, RentPaid rentPaid) {
    super(amazonSQSClient, queueName, objectMapper, rentPaid);

  }
}
