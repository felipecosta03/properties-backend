package com.uade.propertiesbackend.core.config;

import com.uade.propertiesbackend.repository.PricePredictRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class SpringConfig {

  @Bean
  PricePredictRepository pricePredictRepository(
      @Value("${restclient.properties-ia.baseUrl}") String baseUrl) {
    RestClient client = RestClient.builder().baseUrl(baseUrl).build();
    HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(
        RestClientAdapter.create(client)).build();
    return factory.createClient(PricePredictRepository.class);
  }

}
