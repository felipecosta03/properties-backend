package com.uade.propertiesbackend.core.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class MessageEvent {

  @JsonProperty("detail-type")
  private String detailType;
  private String detail;
}
