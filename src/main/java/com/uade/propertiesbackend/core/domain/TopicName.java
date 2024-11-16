package com.uade.propertiesbackend.core.domain;

import lombok.Getter;

@Getter
public enum TopicName {
  CREATE_PROPERTY("create-property");


  private final String value;

  TopicName(String value) {
    this.value = value;
  }
}
