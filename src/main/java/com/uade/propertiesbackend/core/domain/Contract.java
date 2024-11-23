package com.uade.propertiesbackend.core.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Builder
@Getter
public class Contract {

  @Id
  private String id;
  private Long rentProcessId;

}
