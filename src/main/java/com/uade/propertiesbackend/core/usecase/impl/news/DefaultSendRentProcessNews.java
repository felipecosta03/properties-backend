package com.uade.propertiesbackend.core.usecase.impl.news;

import com.uade.propertiesbackend.core.domain.RentProcess;
import com.uade.propertiesbackend.core.usecase.SendRentProcessNews;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DefaultSendRentProcessNews implements SendRentProcessNews {

  @Override
  public void accept(RentProcess rentProcess) {
    log.info("Sending news for rent process: {}", rentProcess);
  }
}
