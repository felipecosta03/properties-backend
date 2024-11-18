package com.uade.propertiesbackend.core.usecase.impl;

import com.uade.propertiesbackend.core.domain.User;
import com.uade.propertiesbackend.core.domain.UserEvent;
import com.uade.propertiesbackend.core.usecase.UserCreated;
import com.uade.propertiesbackend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class DefaultUserCreated implements UserCreated {

  private final UserRepository userRepository;

  public DefaultUserCreated(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void accept(UserEvent userEvent) {
    log.info("User created: {}", userEvent);
    userRepository.save(
        User.builder().id(userEvent.getUserId()).username(userEvent.getUsername()).build());
  }
}
