package com.uade.propertiesbackend.core.usecase.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
  private final ObjectMapper mapper;

  public DefaultUserCreated(UserRepository userRepository, ObjectMapper mapper) {
    this.userRepository = userRepository;
    this.mapper = mapper;
  }

  public void accept(UserEvent userEvent) {
    log.info("User created: {}", userEvent);
    userRepository.save(
        User.builder().id(userEvent.getUserId()).username(userEvent.getUsername()).build());
  }

  @Override
  public void accept(String s) {
    try {
      this.accept(mapper.readValue(s, UserEvent.class));
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
