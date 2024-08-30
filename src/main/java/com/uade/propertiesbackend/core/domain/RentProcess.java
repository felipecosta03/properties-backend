package com.uade.propertiesbackend.core.domain;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RentProcess {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  @ManyToOne
  private Property property;
  private RentProcessStatus status;
  private Double priceMonth;
  private LocalDate start;
  private LocalDate end;
  private Long userId;
}
