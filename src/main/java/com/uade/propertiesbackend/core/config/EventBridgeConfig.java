package com.uade.propertiesbackend.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.eventbridge.EventBridgeClient;

@Configuration
public class EventBridgeConfig {


  @Value("${aws.access.key}")
  private String accessKey;

  @Value("${aws.secret.key}")
  private String secretKey;


  @Bean
  public EventBridgeClient eventBridgeClient() {
    final var credentials = AwsBasicCredentials.create(accessKey, secretKey);
    return EventBridgeClient.builder()
        .credentialsProvider(StaticCredentialsProvider.create(credentials)).region(Region.US_EAST_1)
        .build();
  }

}
