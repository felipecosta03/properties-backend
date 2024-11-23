package com.uade.propertiesbackend.core.domain;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RentProcess {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  @ManyToOne(fetch = FetchType.EAGER)
  @Fetch(FetchMode.JOIN)
  private Property property;
  @Enumerated(EnumType.STRING)
  private RentProcessStatus status;
  private Long tenantId;
  private LocalDateTime dateCreated;
}
