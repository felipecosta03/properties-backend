package com.uade.propertiesbackend.core.usecase.impl;

import com.uade.propertiesbackend.core.usecase.UserExists;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserExists implements UserExists {

  @Override
  public boolean test(Long userId) {
    return true;
  }
}
