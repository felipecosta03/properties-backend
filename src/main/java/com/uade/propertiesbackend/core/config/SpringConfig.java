package com.uade.propertiesbackend.core.config;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.uade.propertiesbackend.repository.PricePredictRepository;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class SpringConfig {

  private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
  private static final String TIME_ZONE = "UTC";

  @Bean
  PricePredictRepository pricePredictRepository(
      @Value("${restclient.properties-ia.baseUrl}") String baseUrl) {
    RestClient client = RestClient.builder().baseUrl(baseUrl).build();
    HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(
        RestClientAdapter.create(client)).build();
    return factory.createClient(PricePredictRepository.class);
  }


  @Bean
  public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
    sdf.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
    return builder -> builder.serializationInclusion(JsonInclude.Include.NON_NULL)
        .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE).dateFormat(sdf);
  }


}
