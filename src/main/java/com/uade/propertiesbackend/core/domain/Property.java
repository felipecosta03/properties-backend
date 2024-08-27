package com.uade.propertiesbackend.core.domain;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Property {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Integer beds;
  private Integer bathrooms;
  private String country;
  private String city;
  private String state;
  private Integer rooms;
  private Double surfaceCovered;
  private Double surfaceTotal;
  private String title;
  private String description;
  private Double latitude;
  private Double longitude;
  @ElementCollection
  private List<String> images;
  private Long userId;
  private String address;
  private Integer storeys;
  private Double price;
  private Integer garages;
  private PropertyType type;
  private LocalDateTime createdAt;
  private boolean active;
}
