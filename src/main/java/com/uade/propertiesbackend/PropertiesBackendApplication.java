package com.uade.propertiesbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class PropertiesBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(PropertiesBackendApplication.class, args);
  }

}
